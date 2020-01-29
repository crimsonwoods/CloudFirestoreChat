package net.crimsonwoods.cloudfirestorechat.di

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import net.crimsonwoods.cloudfirestorechat.ui.MainActivity

@Module(subcomponents = [
    MainActivityComponent::class
])
abstract class AppBindingModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindMainActivityComponent(factory: MainActivityComponent.Factory): AndroidInjector.Factory<*>
}