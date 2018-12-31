package com.bubblegumfellow.quest.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.bubblegumfellow.quest.R
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemDragListener
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnListScrollListener
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_main.view.*
import java.util.*

class MainFragment: Fragment(), MainViewHolder.ItemClickListener {

    private val onItemSwipeListener = object : OnItemSwipeListener<String> {
        override fun onItemSwiped(position: Int, direction: OnItemSwipeListener.SwipeDirection, item: String): Boolean {
            return false
        }
    }

    private val onItemDragListener = object : OnItemDragListener<String> {
        override fun onItemDragged(previousPosition: Int, newPosition: Int, item: String) {
            // NOP
        }

        override fun onItemDropped(initialPosition: Int, finalPosition: Int, item: String) {
            // NOP
        }
    }

    private val onListScrollListener = object : OnListScrollListener {
        override fun onListScrolled(scrollDirection: OnListScrollListener.ScrollDirection, distance: Int) {
            // NOP
        }
    }

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

        // TODO：Reamlからデータを読み込む
        val items = mutableListOf<String>().apply {
            add("hoge")
            add("fuga")
            add("piyo")
        }

        recyclerView.apply {
            adapter = MainAdapter(context, this@MainFragment, items)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onItemClick(view: View, position: Int) {
        // do something here
    }
}

class MainAdapter(private val context: Context,
                  private val itemClickListener: MainViewHolder.ItemClickListener,
                  private val items: List<String>): RecyclerView.Adapter<MainViewHolder>() {
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
        holder.titleTextView.text = items.get(position)
    }

    override fun getItemCount(): Int {
        return items.size
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
}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val titleTextView = view.titleTextView

    init {
        // do something here
    }
}
