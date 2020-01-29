package net.crimsonwoods.cloudfirestorechat.domain.repository.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import net.crimsonwoods.cloudfirestorechat.domain.*
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import net.crimsonwoods.cloudfirestorechat.domain.repository.MyUserIdRepository
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class FirestoreChatRoomRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val myUserIdRepository: MyUserIdRepository
) : ChatRoomRepository {
    private val myUserId by lazy { myUserIdRepository.get() }

    override fun updates(id: ChatRoomId): Observable<ChatRoom> {
        return Observable.create { emitter ->
            firestore.document("rooms/$id")
                .get()
                .addOnSuccessListener {
                    if (it == null || !it.exists()) {
                        // TODO ChatRoomが削除された？
                        return@addOnSuccessListener
                    }

                    val members = (it["members"] as? List<*>)?.map { userId ->
                        UserId(userId as String)
                    } ?: listOf()

                    firestore.collection("rooms/$id/messages")
                        .orderBy("time_stamp")
                        .addSnapshotListener { messages, e ->
                            if (e != null) {
                                emitter.onError(e)
                                return@addSnapshotListener
                            }

                            if (messages == null) {
                                return@addSnapshotListener
                            }

                            emitter.onNext(ChatRoom(id, members, messages.documents.map { message ->
                                val userId = UserId(message["user_id"] as String)
                                val messageText = message["message"] as String
                                val timeStamp = message["time_stamp"] as Long
                                if (userId == myUserId) {
                                    Message.SelfMessage(userId, messageText, timeStamp)
                                } else {
                                    Message.OtherMessage(userId, messageText, timeStamp)
                                }
                            }))
                        }
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    override fun postMessage(id: ChatRoomId, message: Message): Completable {
        return Completable.create { emitter ->
            firestore.collection("rooms/$id/messages")
                .add(mapOf(
                    "user_id" to message.ownerUserId.toString(),
                    "message" to message.message,
                    "time_stamp" to message.timeStamp
                ))
                .apply {
                    addOnCompleteListener {
                        if (it.isSuccessful) {
                            emitter.onComplete()
                        } else {
                            it.exception?.run { emitter.onError(this) }
                        }
                    }
                }
        }
    }
}