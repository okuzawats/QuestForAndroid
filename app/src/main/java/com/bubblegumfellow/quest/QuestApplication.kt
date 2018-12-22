package com.bubblegumfellow.quest

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

class QuestApplication: Application() {
    override fun onCreate() {
        super.onCreate()

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