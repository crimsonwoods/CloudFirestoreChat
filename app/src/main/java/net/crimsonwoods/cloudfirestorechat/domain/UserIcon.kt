package net.crimsonwoods.cloudfirestorechat.domain

import android.net.Uri
import androidx.annotation.DrawableRes
import net.crimsonwoods.cloudfirestorechat.R

sealed class UserIcon {
    /**
     * アイコンリソースのID
     * URIを指定して読み込むタイプの[UserIcon]ではアイコン画像が読み込めなかった場合のフォールバック先として使われる
     */
    @get:DrawableRes
    abstract val resId: Int

    /**
     * [Uri]で表現される動的な読み込みが可能なユーザーアイコン
     */
    data class Loadable(val uri: Uri): UserIcon() {
        override val resId: Int = R.drawable.ic_face_24px_rounded
    }

    /**
     * ユーザーアイコンが未設定の場合に使われるデフォルトのユーザーアイコン
     */
    object None : UserIcon() {
        override val resId: Int = R.drawable.ic_face_24px_rounded
    }

    companion object {
        fun from(uri: String?): UserIcon {
            return when (uri) {
                null -> None
                else -> {
                    val parsed = Uri.parse(uri)
                    when(parsed.scheme) {
                        "http", "https" -> Loadable(parsed)
                        else -> None
                    }
                }
            }
        }
    }
}