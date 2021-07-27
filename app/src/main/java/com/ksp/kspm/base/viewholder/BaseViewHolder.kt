package com.ksp.kspm.base.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ksp.kspm.base.listener.OnSingleClickListener
import timber.log.Timber


abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    OnSingleClickListener {

    private lateinit var viewHolderClickListener: ViewHolderClickListener

    interface ViewHolderClickListener {
        fun onViewHolderViewClicked(view: View?, position: Int)
    }

    fun setViewClickListener(holderClickObserver: ViewHolderClickListener) {
        this.viewHolderClickListener = holderClickObserver
    }

    override fun onSingleClick(v: View) {
        Timber.d("Single click")
        viewHolderClickListener.onViewHolderViewClicked(v, adapterPosition)
    }
}