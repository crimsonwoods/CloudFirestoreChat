package net.crimsonwoods.cloudfirestorechat.di

import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import net.crimsonwoods.cloudfirestorechat.ui.MainActivity

@Subcomponent(modules = [
    AndroidInjectionModule::class,
    MainActivityModule::class
])
interface MainActivityComponent: AndroidInjector<MainActivity> {
    val activity: MainActivity

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity> {
        override fun create(@BindsInstance instance: MainActivity): MainActivityComponent
    }
}