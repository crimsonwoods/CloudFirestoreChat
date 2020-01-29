package net.crimsonwoods.cloudfirestorechat.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import net.crimsonwoods.cloudfirestorechat.domain.ChatRoomId
import net.crimsonwoods.cloudfirestorechat.domain.Message
import net.crimsonwoods.cloudfirestorechat.domain.isSelf
import net.crimsonwoods.cloudfirestorechat.domain.repository.ChatRoomRepository
import javax.inject.Inject

class MessagesAdapter @Inject constructor(
    chatRoomId: ChatRoomId,
    chatRoomRepository: ChatRoomRepository
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
            VIEWTYPE_SELF -> MessageViewHolder.SelfMessageViewHolder(parent.context)
            VIEWTYPE_OTHER -> MessageViewHolder.OtherMessageViewHolder(parent.context)
            else -> { throw NotImplementedError() }
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        // TODO implement
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

    companion object {
        private const val VIEWTYPE_SELF: Int = 0
        private const val VIEWTYPE_OTHER: Int = 1
    }
}