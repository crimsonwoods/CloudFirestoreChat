package net.crimsonwoods.cloudfirestorechat.domain

/**
 * グループID
 */
data class GroupId(private val rawValue: String) : CharSequence by rawValue {
    init {
        require(PATTERN.matches(rawValue)) { "Invalid format value of GroupId: $rawValue" }
    }

    override fun toString(): String {
        return rawValue
    }

    companion object {
        private val PATTERN = Regex("""G[0-9]{3}""")
    }
}