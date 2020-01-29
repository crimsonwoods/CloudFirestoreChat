package net.crimsonwoods.cloudfirestorechat.domain.repository

import net.crimsonwoods.cloudfirestorechat.domain.UserId

interface MyUserIdRepository {
    fun get(): UserId
}