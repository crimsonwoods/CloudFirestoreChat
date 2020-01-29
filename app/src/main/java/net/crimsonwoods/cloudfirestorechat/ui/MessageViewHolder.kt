package net.crimsonwoods.cloudfirestorechat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import net.crimsonwoods.cloudfirestorechat.R

sealed class MessageViewHolder(
    parent: ViewGroup,
    @LayoutRes layout: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layout, parent, false)
) {
    val userIcon: ImageView by lazy { itemView.findViewById<ImageView>(R.id.user_icon) }
    val userName: TextView by lazy { itemView.findViewById<TextView>(R.id.user_name) }
    val userMessage: TextView by lazy { itemView.findViewById<TextView>(R.id.user_message) }

    class SelfMessageViewHolder(parent: ViewGroup) : MessageViewHolder(parent, R.layout.comment_item_self)

    class OtherMessageViewHolder(parent: ViewGroup) : MessageViewHolder(parent, R.layout.comment_item_others)
}