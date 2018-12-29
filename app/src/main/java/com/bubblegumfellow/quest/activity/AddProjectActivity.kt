package com.bubblegumfellow.quest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bubblegumfellow.quest.R
import com.bubblegumfellow.quest.fragment.AddProjectFragment
import kotlinx.android.synthetic.main.activity_add_project.*

class AddProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.container, AddProjectFragment.getInstance())
                commit()
            }
        }
    }
}
