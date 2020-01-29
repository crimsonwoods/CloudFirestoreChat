package net.crimsonwoods.cloudfirestorechat.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import net.crimsonwoods.cloudfirestorechat.R
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId
import net.crimsonwoods.cloudfirestorechat.domain.GroupId
import net.crimsonwoods.cloudfirestorechat.domain.Message
import net.crimsonwoods.cloudfirestorechat.domain.UserId
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    internal val chatRoomId: ChatRoomId = CHAT_ROOM_ID

    @Inject
    lateinit var messagesAdapter: MessagesAdapter
    @Inject
    lateinit var chatRoomRepository: ChatRoomRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messagesAdapter.autoDisposeOnDestroy(lifecycle)
        messages.adapter = messagesAdapter

        submit.setOnClickListener {
            if (!submit.isEnabled) return@setOnClickListener

            submit.isEnabled = false

            chatRoomRepository.postMessage(chatRoomId, Message.SelfMessage(MY_USER_ID, edit_message.text.toString(), System.currentTimeMillis()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    edit_message.text = null
                    submit.isEnabled = true
                }
        }
    }

    companion object {
        private val MY_USER_ID = UserId("U0001")
        private val MY_FRIEND_ID = UserId("U0002")
        private val MY_GROUP_ID = GroupId("G001")
        private val CHAT_ROOM_ID = ChatRoomId.from(MY_GROUP_ID, MY_USER_ID, MY_FRIEND_ID)
    }
}
