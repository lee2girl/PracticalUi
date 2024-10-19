package com.million.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.million.ui.R
import com.million.ui.beans.MainBean

class MainAdapter(var mData: ArrayList<MainBean>,val itemClick:OnItemClick) : Adapter<MainAdapter.MainViewHolder>() {

    private var oldData = ArrayList<MainBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_adapter_main, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val curBean = mData[position]
        holder.title.text = curBean.title
        holder.title.setOnClickListener { itemClick.itemClick(position = position) }
    }

    fun updateData(data: ArrayList<MainBean>) {
        val diffCallback = DiffCallBack(mData, data)
        val result = DiffUtil.calculateDiff(diffCallback)
        oldData = mData
        mData = data
        result.dispatchUpdatesTo(this)
    }

    class MainViewHolder(itemView: View) : ViewHolder(itemView) {
        val mainItemRoot: FrameLayout
        val title: TextView

        init {
            mainItemRoot = itemView.findViewById(R.id.mainItemRoot)
            title = itemView.findViewById(R.id.mainTitle)
        }
    }

    class DiffCallBack(private var oldData: List<MainBean>, private var newData: List<MainBean>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] === newData[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            //== 等价 equals
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

    }

    fun interface OnItemClick {
        fun itemClick(position: Int)
    }

}