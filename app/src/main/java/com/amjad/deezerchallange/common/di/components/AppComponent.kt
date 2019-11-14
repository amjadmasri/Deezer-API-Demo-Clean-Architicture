package com.amjad.deezerchallange.common.di.components

import com.amjad.deezerchallange.common.DeezerApplication
import com.amjad.deezerchallange.common.di.builders.ActivityBuilderModule
import com.amjad.deezerchallange.common.di.builders.FragmentBuilderModule
import com.amjad.deezerchallange.common.di.modules.AppModule
import com.amjad.deezerchallange.common.di.modules.ViewModelModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<DeezerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DeezerApplication): Builder

        fun build(): AppComponent
    }

    override fun inject(app: DeezerApplication)
}