package net.crimsonwoods.cloudfirestorechat.domain

/**
 * チャットルームID
 */
data class ChatRoomId(private val rawValue: String) : CharSequence by rawValue {
    init {
        require(PATTERN.matches(rawValue)) { "Invalid format value of ChatRoomId: $rawValue" }
    }

    override fun toString(): String {
        return rawValue
    }

    companion object {
        /**
         * [GroupId]と[UserId]を結合したものを[ChatRoomId]とする
         */
        private val PATTERN = Regex("""G[0-9]{3}-U[0-9]{4}-U[0-9]{4}""")

        /**
         * [ChatRoomId]を生成する
         */
        fun from(groupId: GroupId, ownUserId: UserId, friendUserId: UserId): ChatRoomId {
            return if (ownUserId > friendUserId) {
                ChatRoomId("$groupId-$friendUserId-$ownUserId")
            } else {
                ChatRoomId("$groupId-$ownUserId-$friendUserId")
            }
        }
    }
}