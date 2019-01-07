package com.bubblegumfellow.quest.usecase.impl

import com.bubblegumfellow.quest.realm.Task
import com.bubblegumfellow.quest.usecase.TaskUseCase
import io.realm.Realm
import io.realm.RealmResults

class TaskUseCaseImpl: TaskUseCase {
    override fun getTasks(): RealmResults<Task> {
        return Realm.getDefaultInstance().where(Task::class.java).findAll().sort("created")
    }

    override fun deleteTask(id: String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { r ->
            r.where(Task::class.java).equalTo("id", id).findAll().deleteAllFromRealm()
        }
        realm.close()
    }
}