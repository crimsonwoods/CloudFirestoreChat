package net.crimsonwoods.cloudfirestorechat.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.disposables.Disposable

fun Lifecycle.runOnDestroy(runnable: () -> Unit) {
    addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            runnable()
        }
    })
}

fun Disposable.autoDisposeOnDestroy(lifecycle: Lifecycle) {
    lifecycle.runOnDestroy { dispose() }
}

val LifecycleOwner.autoDispose: Disposable.() -> Unit
    get() = { autoDisposeOnDestroy(lifecycle) }