package com.bubblegumfellow.quest.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Task(@PrimaryKey open var id: String = UUID.randomUUID().toString(),
                @Required open var taskTitle: String = "",
                @Required open var created: Date = Date()): RealmObject()