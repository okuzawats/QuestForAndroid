package com.bubblegumfellow.quest.usecase

import com.bubblegumfellow.quest.realm.Task
import io.realm.RealmResults

interface TaskUseCase {
    fun addTask(taskTitle: String)
    fun getTasks(): RealmResults<Task>
    fun deleteTask(id: String)
}