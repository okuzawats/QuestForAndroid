package com.bubblegumfellow.quest.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bubblegumfellow.quest.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainFragment: Fragment(), MainViewHolder.ItemClickListener {

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

        val context = context ?: return

        val items = mutableListOf<String>().apply {
            add("hoge")
            add("fuga")
            add("piyo")
            add("hoge")
            add("fuga")
            add("piyo")
            add("hoge")
            add("fuga")
            add("piyo")
            add("hoge")
            add("fuga")
            add("piyo")
            add("hoge")
            add("fuga")
            add("piyo")
            add("hoge")
            add("fuga")
            add("piyo")
            add("hoge")
            add("fuga")
            add("piyo")
        }

        recyclerView.apply {
            adapter = MainAdapter(context, this@MainFragment, items)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        // スワイプでRecyclerViewの項目を削除する
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, to: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewHolder.let {
                    recyclerView.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
                }
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onItemClick(view: View, position: Int) {
        // do something here
    }
}

class MainAdapter(private val context: Context,
                  private val itemClickListener: MainViewHolder.ItemClickListener,
                  private val itemList: List<String>): RecyclerView.Adapter<MainViewHolder>() {

    private var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_main, parent, false)

        view.setOnClickListener { v ->
            recyclerView?.let {
                itemClickListener.onItemClick(v, it.getChildAdapterPosition(v))
            }
        }

        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.titleTextView.text = itemList.get(position)
    }
}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val titleTextView = view.titleTextView

    init {

    }
}
