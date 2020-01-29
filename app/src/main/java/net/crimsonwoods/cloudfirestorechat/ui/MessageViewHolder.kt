package net.crimsonwoods.cloudfirestorechat.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import net.crimsonwoods.cloudfirestorechat.R

sealed class MessageViewHolder(
    context: Context,
    @LayoutRes layout: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(layout, null, false)
) {
    val userIcon: ImageView by lazy { itemView.findViewById<ImageView>(R.id.user_icon) }
    val userName: TextView by lazy { itemView.findViewById<TextView>(R.id.user_name) }
    val userMessage: TextView by lazy { itemView.findViewById<TextView>(R.id.user_message) }

    class SelfMessageViewHolder(context: Context) : MessageViewHolder(context, R.layout.comment_item_self)

    class OtherMessageViewHolder(context: Context) : MessageViewHolder(context, R.layout.comment_item_others)
}