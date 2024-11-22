package com.example.walmartapp.presentation.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.walmartapp.data.local.models.Country
import com.example.walmartapp.R
import com.example.walmartapp.databinding.ItemCountryBinding

class SimpleCountriesAdapter(private var dataList: List<Country>) : RecyclerView.Adapter<SimpleCountriesAdapter.CountryViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun submitList(dataList: List<Country>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class CountryViewHolder(private val binding: ItemCountryBinding) : ViewHolder(binding.root) {
        fun bind(country: Country){
            with(binding){
                countryTitle.text = root.context.getString(R.string.info, country.name, country.region)
                capital.text = country.capital
                code.text = country.code
            }
        }
    }
}

