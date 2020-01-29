package net.crimsonwoods.cloudfirestorechat.domain.repository

import net.crimsonwoods.cloudfirestorechat.domain.UserId

interface FriendUserIdRepository {
    fun get(): UserId
}