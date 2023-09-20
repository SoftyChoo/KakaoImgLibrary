package com.example.kakaoimglibrary.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.kakaoimglibrary.R
import com.example.kakaoimglibrary.databinding.BookmarkImageItemBinding

class BookMarkListAdapter(
    private val onBookmarkChecked: (BookmarkModel, Int) -> Unit
) : ListAdapter<BookmarkModel, BookMarkListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<BookmarkModel>() {
        override fun areItemsTheSame(
            oldItem: BookmarkModel,
            newItem: BookmarkModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BookmarkModel,
            newItem: BookmarkModel
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BookmarkImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onBookmarkChecked
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: BookmarkImageItemBinding,
        private val onBookmarkChecked: (BookmarkModel, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkModel) = with(binding) {
            tvTitle.text = item.title
            tvTime.text = item.datetime
            btnBookmark.isSelected = item.isBookmark
            Glide.with(itemView).load(item.url)
                .placeholder(R.drawable.test_cat) // 이미지를 로딩하기 전
                .error(R.drawable.baseline_error_outline_24) // 이미지를 불러오지 못했을 때
                .into(ivItem)

            btnBookmark.setOnClickListener { isSelected ->
                onBookmarkChecked(
                    item.copy(
                        isBookmark = !item.isBookmark
                    ),
                    adapterPosition
                )
            }

        }
    }
}