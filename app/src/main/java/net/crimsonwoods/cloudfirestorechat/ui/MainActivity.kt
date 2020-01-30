package net.crimsonwoods.cloudfirestorechat.ui

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import net.crimsonwoods.cloudfirestorechat.R
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId
import net.crimsonwoods.cloudfirestorechat.domain.GroupId
import net.crimsonwoods.cloudfirestorechat.domain.Message
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import net.crimsonwoods.cloudfirestorechat.domain.repository.FriendUserIdRepository
import net.crimsonwoods.cloudfirestorechat.domain.repository.MyUserIdRepository
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    internal val chatRoomId: ChatRoomId by lazy {
        ChatRoomId.from(
            MY_GROUP_ID,
            myUserIdRepository.get(),
            friendUserIdRepository.get()
        )
    }

    @Inject
    lateinit var chatRoomRepository: ChatRoomRepository
    @Inject
    lateinit var myUserIdRepository: MyUserIdRepository
    @Inject
    lateinit var friendUserIdRepository: FriendUserIdRepository
    @Inject
    lateinit var messagesAdapter: MessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messagesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()

                // Scrolling down to the bottom of messages automatically.
                if (messagesAdapter.itemCount > 0) {
                    messages.scrollToPosition(messagesAdapter.itemCount - 1)
                }
            }
        })
        messagesAdapter.autoDisposeOnDestroy(lifecycle)
        messages.adapter = messagesAdapter

        edit_message.addTextChangedListener {
            submit.isEnabled = it.isNullOrEmpty().not()
        }

        submit.apply {
            isEnabled = false

            setOnClickListener {
                if (!submit.isEnabled) return@setOnClickListener

                submit.isEnabled = false

                val message = Message.SelfMessage(
                    myUserIdRepository.get(),
                    edit_message.text.toString(),
                    System.currentTimeMillis()
                )
                chatRoomRepository.postMessage(chatRoomId, message)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        edit_message.text = null
                        submit.isEnabled = true
                        if (messagesAdapter.itemCount > 0) {
                            messages.scrollToPosition(messagesAdapter.itemCount - 1)
                        }
                    }
            }
        }
    }

    companion object {
        private val MY_GROUP_ID = GroupId("G001")
    }
}
