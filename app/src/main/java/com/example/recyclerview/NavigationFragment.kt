package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recyclerview.databinding.FragmentNavigationBinding
import com.example.recyclerview.ui.dashboard.DashboardFragment
import com.example.recyclerview.ui.details.DetailsFragment
import com.example.recyclerview.ui.home.HomeFragment
import com.example.recyclerview.ui.notifications.NotificationsFragment

class NavigationFragment : Fragment() {

    private var _binding: FragmentNavigationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)

        binding.navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    openFragment(HomeFragment())
                }

                R.id.navigation_dashboard -> {
                    openFragment(DashboardFragment())
                }

                R.id.navigation_notifications -> {
                    openFragment(NotificationsFragment())
                }
            }
            true
        }

        openFragment(HomeFragment())

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openFragment(fragment: Fragment) {
        Log.d(NavigationFragment::class.java.simpleName, "fragment: $fragment")

        childFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, fragment, "MAIN")
            .commit()
    }
}