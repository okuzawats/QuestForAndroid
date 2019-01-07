package com.bubblegumfellow.quest.presenter

import com.bubblegumfellow.quest.contract.MainAdapterContract
import com.bubblegumfellow.quest.usecase.TaskUseCase

class MainAdapterPresenter(private val taskUseCase: TaskUseCase): MainAdapterContract.Presenter {

    private lateinit var view: MainAdapterContract.View

    override fun setView(view: MainAdapterContract.View) {
        this.view = view
    }

    override fun deleteTask(id: String) {
        taskUseCase.deleteTask(id)
    }
}