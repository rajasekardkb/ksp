package com.ksp.kspm.base.adapter

import android.content.Context


abstract class BaseSelectedRecyclerViewAdapter<T>(context: Context, objectsList: MutableList<T>) : BaseRecyclerViewAdapter<T>(context, objectsList){
    private var selectedItems: MutableList<T> = ArrayList()

    open fun toggleSelection(position: Int) {
        val item = getItem(position)
        toggleSelection(item)
    }

    open fun toggleSelection(item: T) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }
        notifyDataSetChanged()
    }

    open fun removeSelection(item: T) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        }
        notifyDataSetChanged()
    }

    open fun selectItem(position: Int) {
        val item = getItem(position)
        selectItem(item)
    }

    open fun selectItem(item: T) {
        if (selectedItems.contains(item)) {
            return
        }
        selectedItems.add(item)
        notifyDataSetChanged()
    }

    open fun selectAllItem() {
        selectedItems.clear()
        selectedItems.addAll(getList()!!)
        notifyDataSetChanged()
    }

    open fun getSelectedItems(): Collection<T>? {
        return selectedItems
    }

    public open fun isItemSelected(position: Int): Boolean {
        return !selectedItems.isEmpty() && selectedItems.contains(getItem(position))
    }

    open fun clearSelection() {
        selectedItems.clear()
        notifyDataSetChanged()
    }
}