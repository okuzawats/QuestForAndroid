package com.bubblegumfellow.quest

import android.app.Application
import com.bubblegumfellow.quest.presenter.MainPresenter
import com.bubblegumfellow.quest.usecase.TaskUseCase
import com.bubblegumfellow.quest.usecase.impl.TaskUseCaseImpl
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import timber.log.Timber

class QuestApplication: Application() {

    val appModule = module {
        single<TaskUseCase> { TaskUseCaseImpl() }
        factory { MainPresenter(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        // Koin
        startKoin(this, listOf(appModule))

        // Realm
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().schemaVersion(0).build())

        // Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun onTerminate() {
        Realm.getDefaultInstance().close()
        super.onTerminate()
    }
}