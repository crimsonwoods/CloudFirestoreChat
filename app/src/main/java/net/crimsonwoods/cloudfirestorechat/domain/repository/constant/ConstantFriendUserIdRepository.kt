package net.crimsonwoods.cloudfirestorechat.domain.repository.constant

import net.crimsonwoods.cloudfirestorechat.domain.UserId
import net.crimsonwoods.cloudfirestorechat.domain.repository.FriendUserIdRepository
import javax.inject.Inject

class ConstantFriendUserIdRepository @Inject constructor() : FriendUserIdRepository {
    override fun get(): UserId = UserId("U0002")
}