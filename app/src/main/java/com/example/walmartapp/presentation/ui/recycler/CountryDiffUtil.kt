package com.example.walmartapp.presentation.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.walmartapp.data.local.models.Country

class CountryDiffUtil(private val oldList: List<Country>, private val newList: List<Country>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name === newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition].capital == newList[newPosition].capital
                && oldList[oldPosition].region == newList[newPosition].region
                && oldList[oldPosition].code == newList[newPosition].code
    }

}