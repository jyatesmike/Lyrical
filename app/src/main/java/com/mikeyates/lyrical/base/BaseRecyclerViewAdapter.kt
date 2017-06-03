package com.mikeyates.lyrical.base

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.*

/**
 * Created by jamesyates on 5/29/17.
 */

abstract class BaseRecyclerViewAdapter<T, V : BaseViewHolder<T>> : RecyclerView.Adapter<V> {

    protected var _data: ArrayList<T>? = ArrayList()

    constructor() {}

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V

    @CallSuper
    override fun onBindViewHolder(viewHolder: V, position: Int) {
        viewHolder.bind(_data!![position])
    }

    override fun getItemCount(): Int {
        return if (_data != null) _data!!.size else 0
    }

    constructor(data: ArrayList<T>) {
        this._data = data
    }

    var data: ArrayList<T>?
        get() = _data
        set(data) {
            _data = data
            notifyDataSetChanged()
        }
}
