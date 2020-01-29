package net.crimsonwoods.cloudfirestorechat.domain.repository

import io.reactivex.rxjava3.core.Single
import net.crimsonwoods.cloudfirestorechat.domain.User
import net.crimsonwoods.cloudfirestorechat.domain.UserId

interface UserRepository {
    fun get(id: UserId): Single<User>
}