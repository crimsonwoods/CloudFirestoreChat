package net.crimsonwoods.cloudfirestorechat.domain.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoom
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import javax.inject.Inject

class FirestoreChatRoomRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : ChatRoomRepository {
    override fun get(id: ChatRoomId): Single<ChatRoom> {
        // TODO implement
        return Single.just(ChatRoom(id, listOf(), listOf()))
    }

    override fun updates(id: ChatRoomId): Observable<ChatRoom> {
        // TODO implement
        return Observable.just(ChatRoom(id, listOf(), listOf()))
    }
}