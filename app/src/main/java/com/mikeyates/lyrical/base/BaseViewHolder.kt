package com.mikeyates.lyrical.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import butterknife.ButterKnife

/**
 * Created by jamesyates on 5/29/17.
 */

abstract class BaseViewHolder<T> protected constructor(view: View) : RecyclerView.ViewHolder(view) {
    protected var data: T? = null

    init {

        ButterKnife.bind(this, view)
    }

    abstract fun bind(data: T?)

    companion object {

        fun newInstance(parent: ViewGroup): BaseViewHolder<*>? {
            val e = Exception("You need to override this method in the subclass!")
            return null
        }
    }

}