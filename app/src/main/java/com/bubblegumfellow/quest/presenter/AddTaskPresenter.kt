package com.bubblegumfellow.quest.presenter

import com.bubblegumfellow.quest.contract.AddTaskContract
import com.bubblegumfellow.quest.usecase.TaskUseCase

class AddTaskPresenter(private val taskUseCase: TaskUseCase): AddTaskContract.Presenter {

    private lateinit var view: AddTaskContract.View

    override fun setView(view: AddTaskContract.View) {
        this.view = view
    }

    override fun addTask(taskTitle: String) {
        taskUseCase.addTask(taskTitle)
    }
}