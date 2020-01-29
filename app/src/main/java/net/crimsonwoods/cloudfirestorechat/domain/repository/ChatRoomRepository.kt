package net.crimsonwoods.cloudfirestorechat.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoom
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId
import net.crimsonwoods.cloudfirestorechat.domain.Message

interface ChatRoomRepository {
    fun updates(id: ChatRoomId): Observable<ChatRoom>

    fun postMessage(id: ChatRoomId, message: Message): Completable
}