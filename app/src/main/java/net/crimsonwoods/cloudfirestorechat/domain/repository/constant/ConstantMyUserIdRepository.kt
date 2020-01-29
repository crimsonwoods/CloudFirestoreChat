package net.crimsonwoods.cloudfirestorechat.domain.repository.constant

import net.crimsonwoods.cloudfirestorechat.domain.UserId
import net.crimsonwoods.cloudfirestorechat.domain.repository.MyUserIdRepository
import javax.inject.Inject

class ConstantMyUserIdRepository @Inject constructor() : MyUserIdRepository {
    override fun get(): UserId = UserId("U0001")
}