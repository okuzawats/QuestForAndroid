package com.bubblegumfellow.quest

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class QuestApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Realm
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().schemaVersion(0).build())
    }

    override fun onTerminate() {
        Realm.getDefaultInstance().close()
        super.onTerminate()
    }
}