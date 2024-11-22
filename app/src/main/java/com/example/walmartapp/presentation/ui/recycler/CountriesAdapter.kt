package com.example.walmartapp.presentation.ui.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.walmartapp.data.local.models.Country
import com.example.walmartapp.R
import com.example.walmartapp.databinding.ItemCountryBinding

class CountriesAdapter : RecyclerView.Adapter<CountryViewHolder>() {

   private val countries = mutableListOf<Country>()
    init {
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    fun setCountries(newCountries: List<Country>){
        Log.d("MKDKDL", "setCountries")

        val diffCallback = CountryDiffUtil(countries, newCountries)
        val diffCountries = DiffUtil.calculateDiff(diffCallback)

        countries.clear()
        countries.addAll(newCountries)

        diffCountries.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }
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