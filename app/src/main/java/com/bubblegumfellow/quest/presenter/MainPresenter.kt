package com.bubblegumfellow.quest.presenter

import com.bubblegumfellow.quest.contract.MainContract
import com.bubblegumfellow.quest.realm.Task
import io.realm.Realm
import io.realm.RealmResults

class MainPresenter: MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun getTasks(): RealmResults<Task> {
        // TODO：この処理はUse Caseに切り出す
        val realm = Realm.getDefaultInstance()
        val tasks = realm.where(Task::class.java).findAll().sort("created")

        return tasks
    }
}