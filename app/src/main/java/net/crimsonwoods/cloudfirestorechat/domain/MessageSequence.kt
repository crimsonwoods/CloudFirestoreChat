package net.crimsonwoods.cloudfirestorechat.domain

/**
 * [Message]を直列に並べたデータ構造
 */
class MessageSequence(vararg messages: Message): Sequence<Message> {
    private val sequence = messages.asSequence().sortedBy { it.timeStamp }

    override fun iterator(): Iterator<Message> {
        return sequence.iterator()
    }
}