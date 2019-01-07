package com.bubblegumfellow.quest.usecase

import com.bubblegumfellow.quest.realm.Task
import io.realm.RealmResults

interface TaskUseCase {
    fun getTasks(): RealmResults<Task>
}