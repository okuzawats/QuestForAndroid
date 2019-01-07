package com.bubblegumfellow.quest.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bubblegumfellow.quest.R
import com.bubblegumfellow.quest.SwipeToDismissCallback
import com.bubblegumfellow.quest.contract.MainContract
import com.bubblegumfellow.quest.presenter.MainPresenter
import com.bubblegumfellow.quest.realm.Task
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainFragment: Fragment(), MainContract.View, MainViewHolder.ItemClickListener {

    // TODO：DI
    private val presenter: MainContract.Presenter = MainPresenter()

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

    override fun onItemClick(view: View, position: Int) {
        // do something here
    }
}

class MainAdapter(private val context: Context,
                  private val itemClickListener: MainViewHolder.ItemClickListener,
                  private val tasks: OrderedRealmCollection<Task>,
                  autoUpdate: Boolean): RealmRecyclerViewAdapter<Task, MainViewHolder>(tasks, autoUpdate) {
    private var recyclerView: RecyclerView? = null

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

    override fun getItemCount(): Int {
        return tasks.size
    }

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
        val primaryKeyToDelete = tasks[position].id

        // TODO：この処理はUse Caseに切り出す
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { r ->
            r.where(Task::class.java).equalTo("id", primaryKeyToDelete).findAll().deleteAllFromRealm()
        }
        realm.close()
    }
}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val titleTextView: TextView = view.titleTextView

    init {
        // do something here
    }
}
