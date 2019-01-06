package com.bubblegumfellow.quest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bubblegumfellow.quest.R
import com.bubblegumfellow.quest.realm.Task
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_add_project.*

class AddProjectFragment : Fragment() {

    companion object {
        fun getInstance(): AddProjectFragment {
            return AddProjectFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addProjectButton.setOnClickListener { _ ->
            val taskTitle = taskTitleEditText.text.toString()

            // TODO：この処理はUseCaseに切り出す
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction { r ->
                val task = Task(taskTitle = taskTitle)
                r.copyToRealmOrUpdate(task)
            }
            realm.close()

            activity?.finish()
        }
    }
}