package com.bubblegumfellow.quest.contract

import androidx.lifecycle.LifecycleObserver
import com.bubblegumfellow.quest.realm.Task
import io.realm.RealmResults

interface MainContract {
    interface Presenter: LifecycleObserver {
        fun setView(view: View)
        fun getTasks(): RealmResults<Task>
    }

    interface View
}