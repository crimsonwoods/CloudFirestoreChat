package net.crimsonwoods.cloudfirestorechat.domain.repository.firestore

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import net.crimsonwoods.cloudfirestorechat.domain.User
import net.crimsonwoods.cloudfirestorechat.domain.UserIcon
import net.crimsonwoods.cloudfirestorechat.domain.UserId
import net.crimsonwoods.cloudfirestorechat.domain.repository.UserRepository
import javax.inject.Inject

class FirestoreUserRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {
    override fun get(id: UserId): Single<User> {
        return Single.create<User> { emitter ->
            firestore.document("users/$id")
                .get()
                .apply {
                    addOnSuccessListener { user ->
                        val name = user["name"] as String
                        val icon = user["icon"] as String
                        emitter.onSuccess(User(id, name, UserIcon.from(icon)))
                    }
                    addOnFailureListener {
                        emitter.onError(it)
                    }
                }
        }
    }
}