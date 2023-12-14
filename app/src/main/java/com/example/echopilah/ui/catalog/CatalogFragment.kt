package com.example.echopilah.ui.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.echopilah.R
import com.example.echopilah.data.local.entity.Catalog
import com.example.echopilah.databinding.FragmentCatalogBinding
import kotlin.math.min


class CatalogFragment : Fragment() {

    private lateinit var rvCatalog: RecyclerView
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<Catalog>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        val rootView = binding.root


        rvCatalog = binding.rvCatalog
        rvCatalog.setHasFixedSize(true)

        list.addAll(getListCatalog())
        showRecyclerView()

        return rootView
    }

    private fun getListCatalog(): Collection<Catalog> {
        val dataImg1 = resources.obtainTypedArray(R.array.data_image1)
        val dataImg2 = resources.obtainTypedArray(R.array.data_image2)
        val dataName = resources.getStringArray(R.array.data_title)
        val dataDesc = resources.getStringArray(R.array.data_description)

        val listCatalog = ArrayList<Catalog>()

        for (i in dataName.indices) {
            val article = Catalog(dataImg1.getResourceId(i, 0),dataImg2.getResourceId(i,0), dataName[i], dataDesc[i])
            listCatalog.add(article)
        }

        return listCatalog
    }

    private fun showRecyclerView() {
        val spanCount = 2 // Number of columns
        rvCatalog.layoutManager = GridLayoutManager(requireContext(), spanCount)
        val listArticle = listCatalogAdapter(list)
        rvCatalog.adapter = listArticle
    }


}