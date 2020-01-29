package net.crimsonwoods.cloudfirestorechat.domain

data class ChatRoom(
    val id: ChatRoomId,
    val members: List<UserId>,
    val messages: List<Message>
)