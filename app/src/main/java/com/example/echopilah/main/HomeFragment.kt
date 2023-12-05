package com.example.echopilah.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.echopilah.R
import com.example.echopilah.databinding.FragmentHomeBinding
import com.example.echopilah.detail.catalog.CatalogActivity
import com.example.echopilah.detail.home.DetailNonOrganic
import com.example.echopilah.detail.home.DetailOrganic
import com.example.echopilah.info.CatalogFragment


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuAction()
    }

    private fun menuAction() {
        binding.apply {
            btnOrganic.setOnClickListener{
            Intent(requireContext(), DetailOrganic::class.java).also {
                startActivity(it)
                }
            }

            btnNon0rganic.setOnClickListener{
                Intent(requireContext(), DetailNonOrganic::class.java).also {
                    startActivity(it)
                }
            }

        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }



}