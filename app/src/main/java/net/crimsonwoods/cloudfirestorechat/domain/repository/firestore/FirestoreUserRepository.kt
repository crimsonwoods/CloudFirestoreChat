package net.crimsonwoods.cloudfirestorechat.domain.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Single
import net.crimsonwoods.cloudfirestorechat.domain.User
import net.crimsonwoods.cloudfirestorechat.domain.UserId
import net.crimsonwoods.cloudfirestorechat.domain.repository.UserRepository
import javax.inject.Inject

class FirestoreUserRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {
    override fun get(id: UserId): Single<User> {
        // TODO implement
        return Single.just(User(id, "$id"))
    }
}