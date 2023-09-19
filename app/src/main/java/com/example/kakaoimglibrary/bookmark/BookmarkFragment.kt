package com.example.kakaoimglibrary.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kakaoimglibrary.R
import com.example.kakaoimglibrary.databinding.FragmentBookmarkBinding


class BookmarkFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarkFragment()
    }

    private lateinit var _binding: FragmentBookmarkBinding
    private val binding get() = _binding

    private val viewModel: BookmarkViewModel by viewModels { BookmarkViewModelFactory() }

    private val listAdapter: BookMarkListAdapter by lazy {
        BookMarkListAdapter(
            onBookmarkChecked = { item, position ->
                viewModel.removeBookmarkItem(item, position)

            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarkBinding.inflate(layoutInflater)
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

    private fun initModel() = with(viewModel) {
        viewModel.list.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }

}