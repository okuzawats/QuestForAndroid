package com.bubblegumfellow.quest

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeToDismissCallback(context: Context)
    : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_sweep_black_24dp)
    private val deleteIconIntrinsicWidth = deleteIcon?.intrinsicWidth
    private val deleteIconIntrinsicHeight = deleteIcon?.intrinsicHeight

    private val checkIcon = ContextCompat.getDrawable(context, R.drawable.ic_check_black_24dp)
    private val checkIconIntrinsicWidth = checkIcon?.intrinsicWidth
    private val checkIconIntrinsicHeight = checkIcon?.intrinsicHeight

    private val background = ColorDrawable()
    private val leftBackgroundColor = Color.parseColor("#f44336")
    private val rightBackgroundColor = Color.parseColor("#25AA71")
    private val clearPaint = Paint().apply {
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

        val itemView = viewHolder.itemView ?: return

        val isCanceled = dX == 0f && !isCurrentlyActive
        if (isCanceled) {
            clearCanvas(canvas, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        // 背景色の描画
        val isLeftDirection = dX < 0
        if (isLeftDirection) {
            background.color = leftBackgroundColor
            background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
        } else {
            background.color = rightBackgroundColor
            background.setBounds(itemView.left, itemView.top, itemView.left + dX.toInt(), itemView.bottom)
        }
        background.draw(canvas)

        val itemHeight = itemView.bottom - itemView.top
        if (deleteIcon != null && deleteIconIntrinsicWidth != null && deleteIconIntrinsicHeight != null
                && checkIcon != null && checkIconIntrinsicWidth != null && checkIconIntrinsicHeight != null) {
            if (isLeftDirection) {
                val iconTop = itemView.top + (itemHeight - deleteIconIntrinsicHeight) / 2
                val iconMargin = (itemHeight - deleteIconIntrinsicHeight) / 2
                val iconLeft = itemView.right- iconMargin - deleteIconIntrinsicWidth
                val iconRight = itemView.right - iconMargin
                val iconBottom = iconTop + deleteIconIntrinsicHeight

                deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                deleteIcon.draw(canvas)
            } else {
                val iconTop = itemView.top + (itemHeight - checkIconIntrinsicHeight) / 2
                val iconMargin = (itemHeight - checkIconIntrinsicHeight) / 2
                val iconLeft = itemView.left + iconMargin
                val iconRight = itemView.left + iconMargin + checkIconIntrinsicWidth
                val iconBottom = iconTop + checkIconIntrinsicHeight

                checkIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                checkIcon.draw(canvas)
            }
        }

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(canvas: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        canvas?.drawRect(left, top, right, bottom, clearPaint)
    }
}