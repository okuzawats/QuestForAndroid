package com.bubblegumfellow.quest.usecase.impl

import com.bubblegumfellow.quest.realm.Task
import com.bubblegumfellow.quest.usecase.TaskUseCase
import io.realm.Realm
import io.realm.RealmResults

class TaskUseCaseImpl: TaskUseCase {
    override fun getTasks(): RealmResults<Task> {
        val realm = Realm.getDefaultInstance()
        val tasks = realm.where(Task::class.java).findAll().sort("created")

        return tasks
    }
}