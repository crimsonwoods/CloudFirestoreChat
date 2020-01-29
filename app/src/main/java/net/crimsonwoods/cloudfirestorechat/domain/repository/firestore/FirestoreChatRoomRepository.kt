package net.crimsonwoods.cloudfirestorechat.domain.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import net.crimsonwoods.cloudfirestorechat.domain.*
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import javax.inject.Inject

class FirestoreChatRoomRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : ChatRoomRepository {
    override fun updates(id: ChatRoomId): Observable<ChatRoom> {
        // TODO implement
        return Observable.just(ChatRoom(id, listOf(
            User(UserId("U0001"), "0001"),
            User(UserId("U0002"), "0002")
        ), listOf(
            Message.OtherMessage(UserId("U0002"), "A", 0L),
            Message.OtherMessage(UserId("U0002"), "B", 1L),
            Message.SelfMessage(UserId("U0001"), "テスト", 2L)
        )))
    }

    override fun postMessage(id: ChatRoomId, message: Message): Completable {
        return Completable.complete()
    }
}