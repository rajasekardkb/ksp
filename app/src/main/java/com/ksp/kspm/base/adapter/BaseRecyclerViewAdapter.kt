package com.ksp.kspm.base.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.ksp.kspm.base.viewholder.BaseViewHolder


abstract class BaseRecyclerViewAdapter<T>(val ctx: Context, private var list: MutableList<T>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = BaseRecyclerViewAdapter::class.java.simpleName
    protected var inflater: LayoutInflater = LayoutInflater.from(ctx)
    var viewHolderClickListener: BaseViewHolder.ViewHolderClickListener? = null

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    open fun getCount(): Int {
        return list!!.size
    }

    open fun getItem(position: Int): T {
        return list!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    open fun updateList(newData: MutableList<T>) {
        list = newData
        notifyDataSetChanged()
    }

    open fun add(item: T) {
        list!!.add(item)
        notifyDataSetChanged()
    }

    open fun addAt(position: Int, item: T) {
        list!!.add(position, item)
        notifyItemChanged(position)
    }

    open fun replace(position: Int, item: T){
        list!!.set(position, item)
        notifyItemChanged(position)
    }

    open fun addTop(items: List<T>?) {
        list!!.addAll(0, items!!)
        notifyDataSetChanged()
    }

    open fun addAtLast(items: List<T>?) {
        list!!.addAll(items!!)
        notifyDataSetChanged()
    }


    open fun getList(): List<T>? {
        return list
    }

    open fun remove(item: T) {
        list!!.remove(item)
        notifyDataSetChanged()
    }

    open fun remove(position: Int) {
        list!!.removeAt(position)
        notifyItemRemoved(position)
    }
}