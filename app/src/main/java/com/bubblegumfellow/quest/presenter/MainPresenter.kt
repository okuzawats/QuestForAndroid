package com.bubblegumfellow.quest.presenter

import com.bubblegumfellow.quest.contract.MainContract
import com.bubblegumfellow.quest.realm.Task
import com.bubblegumfellow.quest.usecase.TaskUseCase
import io.realm.Realm
import io.realm.RealmResults

class MainPresenter(private val taskUseCase: TaskUseCase): MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun getTasks(): RealmResults<Task> = taskUseCase.getTasks()
}