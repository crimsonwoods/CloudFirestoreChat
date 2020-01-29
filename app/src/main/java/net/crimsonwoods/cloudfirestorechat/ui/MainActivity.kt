package net.crimsonwoods.cloudfirestorechat.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import net.crimsonwoods.cloudfirestorechat.R
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId
import net.crimsonwoods.cloudfirestorechat.domain.GroupId
import net.crimsonwoods.cloudfirestorechat.domain.UserId
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var chatRoomRepository: ChatRoomRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chatRoomRepository.updates(CHATROOM_ID).subscribe {
            // TODO update messages
        }.autoDisposeOnDestroy(lifecycle)
    }

    companion object {
        private val MY_USER_ID = UserId("U0001")
        private val MY_FRIEND_ID = UserId("U0002")
        private val MY_GROUP_ID = GroupId("G001")
        private val CHATROOM_ID = ChatRoomId.from(MY_GROUP_ID, MY_USER_ID, MY_FRIEND_ID)
    }
}
