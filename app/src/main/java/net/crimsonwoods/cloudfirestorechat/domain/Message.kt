package net.crimsonwoods.cloudfirestorechat.domain

sealed class Message {
    abstract val ownerUserId: UserId
    abstract val message: String
    abstract val timeStamp: Long

    data class SelfMessage(
        override val ownerUserId: UserId,
        override val message: String,
        override val timeStamp: Long
    ) : Message()

    data class OtherMessage(
        override val ownerUserId: UserId,
        override val message: String,
        override val timeStamp: Long
    ) : Message()
}

val Message.isSelf: Boolean get() = this is Message.SelfMessage
val Message.isNotSelf: Boolean get() = this !is Message.SelfMessage
val Message.isOther: Boolean get() = this is Message.OtherMessage
val Message.isNotOther: Boolean get() = this !is Message.OtherMessage