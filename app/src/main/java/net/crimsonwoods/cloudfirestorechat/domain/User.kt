package net.crimsonwoods.cloudfirestorechat.domain

data class User(
    val id: UserId,
    val name: String,
    val icon: UserIcon = UserIcon.None
)