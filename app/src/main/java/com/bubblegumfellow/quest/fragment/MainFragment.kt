package com.bubblegumfellow.quest.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bubblegumfellow.quest.R
import com.bubblegumfellow.quest.adapter.MainAdapter
import com.bubblegumfellow.quest.adapter.MainViewHolder
import com.bubblegumfellow.quest.adapter.SwipeToDismissCallback
import com.bubblegumfellow.quest.contract.MainContract
import com.bubblegumfellow.quest.presenter.MainPresenter
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject

class MainFragment: Fragment(), MainContract.View, MainViewHolder.ItemClickListener {

    // MainContract.Presenterを使いたいが、Koinのチュートリアルに従ってこうしておく
    val presenter: MainPresenter by inject()

    companion object {
        fun getInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)

        val context = context ?: return

        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager(context).orientation)
        recyclerView.apply {
            adapter = MainAdapter(context, this@MainFragment, presenter.getTasks(), true)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)
        }

        val swipeHandler = object : SwipeToDismissCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as MainAdapter
                viewHolder.let {
                    adapter.removeAt(it.adapterPosition)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(presenter)
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(presenter)
    }

    override fun onItemClick(view: View, position: Int) {
        // do something here
    }
}
