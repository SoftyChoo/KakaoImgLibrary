package com.example.kakaoimglibrary.ui.bookmark

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kakaoimglibrary.viewmodel.bookmark.BookmarkViewModel
import com.example.kakaoimglibrary.viewmodel.bookmark.BookmarkViewModelFactory
import com.example.kakaoimglibrary.databinding.FragmentBookmarkBinding
import com.example.kakaoimglibrary.main.BookmarkState
import com.example.kakaoimglibrary.main.EntryType
import com.example.kakaoimglibrary.main.SharedViewModel
import com.example.kakaoimglibrary.model.BookmarkModel
import com.example.kakaoimglibrary.utils.loadBookmarkData
import com.example.kakaoimglibrary.utils.saveBookmarkData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookmarkFragment : Fragment() {
    companion object {
        fun newInstance() = BookmarkFragment()
        val PREFERENCES_KEY = "preference_key_name"
    }

    private lateinit var _binding: FragmentBookmarkBinding
    private val binding get() = _binding

    private val viewModel: BookmarkViewModel by viewModels { BookmarkViewModelFactory() }
    private val activityViewModel: SharedViewModel by activityViewModels()

    private val listAdapter: BookMarkListAdapter by lazy {
        BookMarkListAdapter(
            onBookmarkChecked = { item, position ->
                removeBookmarkItem(item, position)
                modifyToSearchTab(item, EntryType.EDIT.name)
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

        context?.let { context ->
            loadBookmarkData(context, PREFERENCES_KEY).let { // SharedPreference 데이터 로드
                viewModel.loadBookmarkModel(it)
            }
        }
    }

    private fun addBookmarkItem(bookmarkModel: BookmarkModel) {
        viewModel.addBookmarkModel(bookmarkModel)
    }

    private fun removeBookmarkItem(bookmarkModel: BookmarkModel, position: Int?) {
        viewModel.removeBookmarkItem(bookmarkModel, position)
    }

    private fun modifyToSearchTab(item: BookmarkModel, name: String) {
        activityViewModel.updateSearchState(item, name)
    }

    private fun initModel() {
        viewModel.list.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
            context?.let { context -> saveBookmarkData(context, PREFERENCES_KEY, it) } // SharedPreference 데이터 저장
        })
        activityViewModel.bookmarkState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is BookmarkState.AddBookmark -> addBookmarkItem(state.bookmarkModel)
                is BookmarkState.RemoveBookmark -> removeBookmarkItem(state.bookmarkModel, null)
            }
        })
    }
}