package net.crimsonwoods.cloudfirestorechat.di

import dagger.Module
import dagger.Provides
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId
import net.crimsonwoods.cloudfirestorechat.ui.MainActivity

@Module
class MainActivityModule {
    @Provides
    fun provideChatRoomId(activity: MainActivity): ChatRoomId = activity.chatRoomId
}