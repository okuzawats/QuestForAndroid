package com.bubblegumfellow.quest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bubblegumfellow.quest.R
import com.bubblegumfellow.quest.presenter.AddTaskPresenter
import kotlinx.android.synthetic.main.fragment_add_project.*
import org.koin.android.ext.android.inject

class AddTaskFragment : Fragment() {

    // FIXME：AddTaskContract.Presenterを使いたいが、Koinのチュートリアルに従って一旦こうしておく
    val presenter: AddTaskPresenter by inject()

    companion object {
        fun getInstance(): AddTaskFragment {
            return AddTaskFragment()
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
            presenter.addTask(taskTitle)
            activity?.finish()
        }
    }
}