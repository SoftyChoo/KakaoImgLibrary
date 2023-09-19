package com.example.kakaoimglibrary.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kakaoimglibrary.R
import com.example.kakaoimglibrary.databinding.ImageItemBinding

class SearchListAdapter(
    private val onBookmarkChecked: (SearchModel, Int) -> Unit
) : ListAdapter<SearchModel, SearchListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<SearchModel>() {
        override fun areItemsTheSame(
            oldItem: SearchModel,
            newItem: SearchModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchModel,
            newItem: SearchModel
        ): Boolean {
            return oldItem == newItem
        }
    }
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {// item 생성
        return ViewHolder(
            ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onBookmarkChecked
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // binding 해줌
        val item = getItem(position) // ListAdapter의 메소드 getItem
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: ImageItemBinding,
        private val onBookmarkChecked: (SearchModel, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchModel) = with(binding) {
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