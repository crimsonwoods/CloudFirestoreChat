package net.crimsonwoods.cloudfirestorechat.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import net.crimsonwoods.cloudfirestorechat.domain.repository.UserRepository
import net.crimsonwoods.cloudfirestorechat.domain.repository.firestore.FirestoreChatRoomRepository
import net.crimsonwoods.cloudfirestorechat.domain.repository.firestore.FirestoreUserRepository
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun providesFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideChatRoomRepository(impl: FirestoreChatRoomRepository): ChatRoomRepository = impl

    @Singleton
    @Provides
    fun provideUserRepository(impl: FirestoreUserRepository): UserRepository = impl
}