package com.example.recyclerview.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview.MainActivity
import com.example.recyclerview.databinding.FragmentDashboardBinding
import com.example.recyclerview.ui.dashboard.movies.epoxy.MoviesController

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val controller: MoviesController by lazy {
        MoviesController(::adapterOnClick)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.recyclerView.apply {
            setController(controller)
            itemAnimator = null
        }

        dashboardViewModel.fetchPages({ list ->
            controller.submitList(list)
        }, { error ->
            Log.e("melo", "error: ${error}")
        })

        registerObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterObservers()
        _binding = null
    }

    private fun adapterOnClick(name: String) {
        Log.d(DashboardFragment::class.java.simpleName, "name: $name")

        (activity as MainActivity).openDetailsFragment()
    }

    private fun registerObservers() {
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it
        })
    }

    private fun unregisterObservers() {
        dashboardViewModel.text.removeObservers(viewLifecycleOwner)
    }

}