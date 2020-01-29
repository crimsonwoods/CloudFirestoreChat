package net.crimsonwoods.cloudfirestorechat.domain.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoom
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId

interface ChatRoomRepository {
    fun get(id: ChatRoomId): Single<ChatRoom>

    fun updates(id: ChatRoomId): Observable<ChatRoom>
}