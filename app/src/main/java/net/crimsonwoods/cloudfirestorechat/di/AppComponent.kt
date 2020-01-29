package net.crimsonwoods.cloudfirestorechat.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import net.crimsonwoods.cloudfirestorechat.MyApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class
])
interface AppComponent : AndroidInjector<MyApplication> {
    val application: MyApplication

    @Component.Factory
    interface Factory : AndroidInjector.Factory<MyApplication> {
        override fun create(@BindsInstance app: MyApplication): AppComponent
    }
}