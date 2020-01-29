package net.crimsonwoods.cloudfirestorechat

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.crimsonwoods.cloudfirestorechat.di.DaggerAppComponent

class MyApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}