package com.example.walmartapp.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartapp.R
import com.example.walmartapp.di.ManualDIModule
import com.example.walmartapp.presentation.ui.recycler.CountriesAdapter
import com.example.walmartapp.presentation.ui.recycler.SimpleCountriesAdapter
import com.example.walmartapp.presentation.viewModel.CountriesViewModel

class CountryActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var recycler: RecyclerView
    private val recyclerAdapter by lazy {
        CountriesAdapter()
    }
    private val viewModel: CountriesViewModel by viewModels {
        CountriesViewModel.CountriesViewModelFactory(ManualDIModule.countriesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        init()
        showProgressBar()
        setupRecyclerView()
        setUpObservers()
    }

    private fun init() {
        recycler = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun setUpObservers() {
        viewModel.countries
            .observe(this) { countryList ->
                hideProgressBar()
                recyclerAdapter.submitList(countryList)
            }
        viewModel.countriesError
            .observe(this) {
                hideProgressBar()
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
    }

    private fun setupRecyclerView() {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = recyclerAdapter
        recycler.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}