package net.crimsonwoods.cloudfirestorechat.domain

import android.net.Uri
import androidx.annotation.DrawableRes

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
        override val resId: Int = android.R.drawable.sym_def_app_icon
    }

    /**
     * ユーザーアイコンが未設定の場合に使われるデフォルトのユーザーアイコン
     */
    object None : UserIcon() {
        override val resId: Int = android.R.drawable.sym_def_app_icon
    }
}