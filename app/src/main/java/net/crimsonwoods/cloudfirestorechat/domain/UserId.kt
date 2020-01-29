package net.crimsonwoods.cloudfirestorechat.domain

/**
 * ユーザーID
 */
data class UserId(private val rawValue: String) : CharSequence by rawValue {
    init {
        require(PATTERN.matches(rawValue)) { "Invalid format value of UserId: $rawValue" }
    }

    override fun toString(): String {
        return rawValue
    }

    operator fun compareTo(other: UserId): Int {
        return when {
            this === other -> 0
            else -> rawValue.compareTo(other.rawValue)
        }
    }

    companion object {
        private val PATTERN = Regex("""U[0-9]{4}""")
    }
}