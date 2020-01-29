package net.crimsonwoods.cloudfirestorechat.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.crimsonwoods.cloudfirestorechat.R
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId
import net.crimsonwoods.cloudfirestorechat.domain.GroupId
import net.crimsonwoods.cloudfirestorechat.domain.UserId
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    internal val chatRoomId: ChatRoomId = CHAT_ROOM_ID

    @Inject
    lateinit var messagesAdapter: MessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messagesAdapter.autoDisposeOnDestroy(lifecycle)
        messages.adapter = messagesAdapter

        submit.setOnClickListener {
            if (!submit.isEnabled) return@setOnClickListener

            submit.isEnabled = false
            submit.postDelayed({ submit.isEnabled = true }, 300L)

            // TODO submit message

            edit_message.text = null
        }
    }

    companion object {
        private val MY_USER_ID = UserId("U0001")
        private val MY_FRIEND_ID = UserId("U0002")
        private val MY_GROUP_ID = GroupId("G001")
        private val CHAT_ROOM_ID = ChatRoomId.from(MY_GROUP_ID, MY_USER_ID, MY_FRIEND_ID)
    }
}
