package com.example.walmartapp.presentation.ui

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartapp.databinding.FragmentCountriesBinding
import com.example.walmartapp.di.ManualDIModule
import com.example.walmartapp.presentation.ui.recycler.CountriesAdapter
import com.example.walmartapp.presentation.viewModel.CountriesViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountriesViewModel by activityViewModels {
        CountriesViewModel.CountriesViewModelFactory(ManualDIModule.countriesRepository)
    }
    private val adapter = CountriesAdapter()
    private var recyclerViewState: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setUpObservers()
    }

    private fun setUpUI() {
        binding.recycler.adapter = adapter
        recyclerViewState?.let {
            binding.recycler.layoutManager?.onRestoreInstanceState(it)
        }
    }

    private fun setUpObservers() {
        Log.d("MKDKDL", "viewModel $viewModel")
        viewModel.countries
            .distinctUntilChanged()
            .observe(viewLifecycleOwner) {
                Log.d("MKDKDL", "observer")

                if (it.isNotEmpty()) {
                hideEmptyListView()
                showRecycler()
                adapter.setCountries(it)
            } else {
                hideRecycler()
                showEmptyListView()
            }
        }
        viewModel.countriesError
            .distinctUntilChanged()
            .observe(viewLifecycleOwner) {
            showEmptyListView()
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun hideRecycler() {
        binding.recycler.visibility = View.INVISIBLE
    }

    private fun showEmptyListView() {
        binding.emptyList.visibility = View.VISIBLE
    }

    private fun showRecycler() {
        binding.recycler.visibility = View.VISIBLE
    }

    private fun hideEmptyListView() {
        binding.emptyList.visibility = View.INVISIBLE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        recyclerViewState = binding.recycler.layoutManager?.onSaveInstanceState()
        _binding = null
    }
}