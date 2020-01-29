package net.crimsonwoods.cloudfirestorechat.ui

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import net.crimsonwoods.cloudfirestorechat.domain.*
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import net.crimsonwoods.cloudfirestorechat.domain.repository.UserRepository
import javax.inject.Inject

class MessagesAdapter @Inject constructor(
    chatRoomId: ChatRoomId,
    chatRoomRepository: ChatRoomRepository,
    private val userRepository: UserRepository
) : RecyclerView.Adapter<MessageViewHolder>(), Disposable {
    private var messages: List<Message> = listOf()
    private val disposable: Disposable = chatRoomRepository
        .updates(chatRoomId)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            messages = it.messages.toList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return when(viewType) {
            VIEWTYPE_SELF -> MessageViewHolder.SelfMessageViewHolder(parent)
            VIEWTYPE_OTHER -> MessageViewHolder.OtherMessageViewHolder(parent)
            else -> { throw NotImplementedError() }
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        when(holder) {
            is MessageViewHolder.SelfMessageViewHolder -> {
                check(message is Message.SelfMessage)

                userRepository.get(message.ownerUserId).subscribe { user: User ->
                    holder.userIcon.setUserIcon(user.icon)
                    holder.userName.text = user.name
                }
                holder.userMessage.text = message.message
            }
            is MessageViewHolder.OtherMessageViewHolder -> {
                check(message is Message.OtherMessage)

                userRepository.get(message.ownerUserId).subscribe { user: User ->
                    holder.userIcon.setUserIcon(user.icon)
                    holder.userName.text = user.name
                }
                holder.userMessage.text = message.message
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSelf) {
            VIEWTYPE_SELF
        } else {
            VIEWTYPE_OTHER
        }
    }

    override fun dispose() {
        disposable.dispose()
    }

    override fun isDisposed(): Boolean {
        return disposable.isDisposed
    }

    private fun ImageView.setUserIcon(icon: UserIcon) {
        when(icon) {
            is UserIcon.Loadable -> {
                // TODO load image from URI
                setImageResource(icon.resId)
            }
            is UserIcon.None -> {
                setImageResource(icon.resId)
            }
        }
    }

    companion object {
        private const val VIEWTYPE_SELF: Int = 0
        private const val VIEWTYPE_OTHER: Int = 1
    }
}