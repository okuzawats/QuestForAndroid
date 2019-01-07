package com.bubblegumfellow.quest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bubblegumfellow.quest.R
import com.bubblegumfellow.quest.contract.MainAdapterContract
import com.bubblegumfellow.quest.presenter.MainAdapterPresenter
import com.bubblegumfellow.quest.realm.Task
import com.bubblegumfellow.quest.usecase.impl.TaskUseCaseImpl
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class MainAdapter(private val context: Context,
                  private val itemClickListener: MainViewHolder.ItemClickListener,
                  private val tasks: OrderedRealmCollection<Task>,
                  autoUpdate: Boolean): RealmRecyclerViewAdapter<Task, MainViewHolder>(tasks, autoUpdate) {

    private var recyclerView: RecyclerView? = null

    // TODO：DI
    private var presenter: MainAdapterContract.Presenter = MainAdapterPresenter(TaskUseCaseImpl())

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.titleTextView.text = tasks[position].taskTitle
    }

    override fun getItemCount(): Int = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_main, parent, false)

        view.setOnClickListener { _ ->
            recyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return MainViewHolder(view)
    }

    fun removeAt(position: Int) {
        presenter.deleteTask(tasks[position].id)
    }
}
