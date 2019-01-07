package com.bubblegumfellow.quest.contract

interface MainAdapterContract {
    interface Presenter {
        fun setView(view: View)
        fun deleteTask(id: String)
    }

    interface View
}