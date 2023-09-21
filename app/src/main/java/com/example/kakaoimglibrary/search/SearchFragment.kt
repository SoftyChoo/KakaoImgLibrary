package com.example.kakaoimglibrary.search

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isInvisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaoimglibrary.databinding.FragmentSearchBinding
import com.example.kakaoimglibrary.main.EntryType
import com.example.kakaoimglibrary.main.SearchState
import com.example.kakaoimglibrary.main.SharedViewModel
import com.example.kakaoimglibrary.model.SearchModel

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var _binding: FragmentSearchBinding
    private val binding get() = _binding


    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory() }

    private val activityViewModel: SharedViewModel by activityViewModels()

    private val listAdapter by lazy {
        SearchListAdapter(
            onBookmarkChecked = { item, position ->
                modifySearchItem(item, position)
                if (item.isBookmark) {
                    addToBookmarkTab(item, EntryType.ADD.name)
                } else {
                    removeToBookmarkTab(item, EntryType.REMOVE.name)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initModel()
    }

    private fun initView() = with(binding) {
        recyclerView.adapter = listAdapter

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchItems(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        // infinite Scroll
        recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val lastItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount-1

                    if(lastItemPosition == itemTotalCount){
                        Toast.makeText(context,"최하단 도착",Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.VISIBLE
                        val handler = Handler()
                        handler.postDelayed({
                            progressBar.visibility = View.GONE
                        }, 2000)
//
//                        viewModel.doSearch( )

                    }
//
                }
            }
        )
    }

    private fun initModel() = with(viewModel) { // ViewModel의 Livedata 변동 시 List 갱신
        list.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
        activityViewModel.searchState.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is SearchState.modifySearch -> modifySearchItem(state.searchModel, null)
            }
        })
    }

    private fun modifySearchItem(item: SearchModel, position: Int?) {
        viewModel.modifyList(item, position)
    }

    private fun removeToBookmarkTab(item: SearchModel, name: String) {
        activityViewModel.updateBookmarkState(item,name)
    }

    private fun addToBookmarkTab(item: SearchModel, name: String) {
        activityViewModel.updateBookmarkState(item,name)
    }








    override fun onDestroyView() {
        super.onDestroyView()
    }

}