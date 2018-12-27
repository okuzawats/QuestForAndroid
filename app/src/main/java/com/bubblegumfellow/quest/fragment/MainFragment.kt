package com.bubblegumfellow.quest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bubblegumfellow.quest.R
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemDragListener
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnListScrollListener
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainFragment: Fragment() {

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

        // TODO：Realmからデータを読み込む
        val items = listOf("hogehogehogehogehogehogehogehogehogehogehogehogehoge",
                "fugafugafugafugafugafugafugafugafugafugafugafugafugafuga",
                "piyopiyopiyopiyopiyopiyopiyopiyopiyopiyopiyopiyopiyopiyo")

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MyAdapter(items)
            orientation = DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener
            dragListener = onItemDragListener
            scrollListener = onListScrollListener
        }
    }
}

class MyAdapter(dataSet: List<String> = mutableListOf()): DragDropSwipeAdapter<String, MyAdapter.ViewHolder>(dataSet) {

    class ViewHolder(itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.titleTextView
        val dragIcon: ImageView = itemView.dragIconImageView
    }

    override fun getViewHolder(itemView: View) = MyAdapter.ViewHolder(itemView)

    override fun onBindViewHolder(item: String, viewHolder: MyAdapter.ViewHolder, position: Int) {
        viewHolder.titleTextView.text = item
    }

    override fun getViewToTouchToStartDraggingItem(item: String, viewHolder: MyAdapter.ViewHolder, position: Int): View? {
        return viewHolder.dragIcon
    }
}