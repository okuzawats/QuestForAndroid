package com.bubblegumfellow.quest.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Task(@PrimaryKey open var id: Int = 1,
                @Required open var text: String = ""): RealmObject()