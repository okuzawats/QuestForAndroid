package com.bubblegumfellow.quest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bubblegumfellow.quest.R

class AddProjectFragment: Fragment() {

    companion object {
        fun getInstance(): AddProjectFragment {
            return AddProjectFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add_project, container, false)
    }
}