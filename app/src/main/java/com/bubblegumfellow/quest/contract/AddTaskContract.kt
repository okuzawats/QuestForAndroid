package com.bubblegumfellow.quest.contract

import androidx.lifecycle.LifecycleObserver

interface AddTaskContract {
    interface Presenter: LifecycleObserver {
        fun setView(view: View)
        fun addTask(taskTitle: String)
    }

    interface View
}