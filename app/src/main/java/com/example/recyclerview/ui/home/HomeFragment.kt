package com.example.recyclerview.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.MainActivity
import com.example.recyclerview.databinding.FragmentHomeBinding
import com.example.recyclerview.ui.details.DetailsFragment

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val adapter = NamesAdapter { name -> adapterOnClick(name) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvNames.layoutManager = LinearLayoutManager(context)
        binding.rvNames.adapter = adapter

        registerObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObservers()
        _binding = null
    }

    private fun adapterOnClick(name: String) {
        Log.d(DetailsFragment::class.java.simpleName, "name: $name")

        (activity as MainActivity).openDetailsFragment()
    }

    private fun registerObservers() {
        homeViewModel.names.observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })
    }

    private fun unregisterObservers() {
        homeViewModel.names.removeObservers(viewLifecycleOwner)
        homeViewModel.text.removeObservers(viewLifecycleOwner)
    }
}