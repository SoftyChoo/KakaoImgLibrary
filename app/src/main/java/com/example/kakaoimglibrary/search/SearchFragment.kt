package com.example.kakaoimglibrary.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kakaoimglibrary.R
import com.example.kakaoimglibrary.databinding.FragmentSearchBinding
import java.util.concurrent.atomic.AtomicLong

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var _binding: FragmentSearchBinding
    private val binding get() = _binding

    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory(AtomicLong(1L)) }

    private val listAdapter by lazy {
        SearchListAdapter(
            onBookmarkChecked = { item, position ->
                if (item.isBookmark) {
                    // TODO : 북마크에 추가
                } else {
                    // TODO : 북마크에서 삭제
                }
                viewModel.modifySearchItem(item, position)
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
    }

    private fun initModel() = with(viewModel) { // ViewModel의 Livedata 변동 시 List 갱신
        list.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}