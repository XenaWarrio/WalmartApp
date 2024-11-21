package com.example.walmartapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartapp.databinding.FragmentCountriesBinding
import com.example.walmartapp.presentation.ui.recycler.CountriesAdapter
import com.example.walmartapp.presentation.viewModel.CountriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountriesViewModel by viewModel()
    private val adapter = CountriesAdapter()

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

        viewModel.getCountries()
    }

    private fun setUpUI() {
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.recycler.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.countries.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                hideEmptyListView()
                showRecycler()
                adapter.setCountries(it)
            } else {
                hideRecycler()
                showEmptyListView()
            }
        }
        viewModel.countriesError.observe(viewLifecycleOwner) {
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
        _binding = null
    }
}