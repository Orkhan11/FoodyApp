package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentMainBinding
import com.example.foodapp.ui.adapter.MainAdapter
import com.example.foodapp.ui.adapter.SubAdapter
import com.example.foodapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        viewModel.personsList.observe(viewLifecycleOwner) {
            getAllFood(it[0].category)
            val subAdapter = SubAdapter(requireContext(), it)
            binding.rv2.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rv2.adapter = subAdapter
            subAdapter.setClickListener(onClicked)
        }

        addMenuProviderForSearchView()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
    }

    private val onClicked = object : SubAdapter.OnItemClickListener {
        override fun onClicked(categoryName: String) {
            getAllFood(categoryName)
        }

    }

    fun getAllFood(categoryName: String) {
        viewModel.personsList.observe(viewLifecycleOwner) { foods ->
            val list = foods.filter {
                it.category == categoryName
            }
            //Search
//            foods.flatMap {
//                it.name.toList()
//            }.filter {
//                it =='a'
//            }

            val adapter = MainAdapter(requireContext(), list)
            binding.rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            binding.rv.adapter = adapter
        }
    }


    private fun addMenuProviderForSearchView() {
        val menuProvider = object : MenuProvider {


            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)

                val item = menu.findItem(R.id.app_bar_search)
                val searchView = item.actionView as SearchView
               // searchView.isIconified = false
                searchView.setOnQueryTextListener(this@MainFragment)


            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }
        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.searchFood(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.searchFood(newText)
        return true

    }


}