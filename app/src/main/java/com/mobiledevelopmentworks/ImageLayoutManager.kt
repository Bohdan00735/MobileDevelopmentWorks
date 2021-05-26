package com.mobiledevelopmentworks

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class ImageLayoutManager(private  val context : Context) : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {

        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.MATCH_PARENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {

        if (recycler != null) {
            detachAndScrapAttachedViews(recycler)
        }
        if (state?.itemCount!! <= 0) return
        //fillBottom(recycler, state.itemCount)
    }



    override fun canScrollVertically(): Boolean {
        return true
    }
}