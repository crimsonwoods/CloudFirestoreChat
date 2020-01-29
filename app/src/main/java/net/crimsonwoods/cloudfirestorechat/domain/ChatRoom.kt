package net.crimsonwoods.cloudfirestorechat.domain

data class ChatRoom(
    val id: ChatRoomId,
    val members: List<User>,
    val messages: List<Message>
)