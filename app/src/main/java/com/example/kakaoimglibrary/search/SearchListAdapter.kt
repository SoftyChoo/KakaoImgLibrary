package com.example.kakaoimglibrary.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kakaoimglibrary.R
import com.example.kakaoimglibrary.databinding.ImageItemBinding
import com.example.kakaoimglibrary.model.ImageSearchModel

class SearchListAdapter(
    private val onBookmarkChecked: (ImageSearchModel.Documents, Int) -> Unit
) : ListAdapter<ImageSearchModel.Documents, SearchListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<ImageSearchModel.Documents>() {
        override fun areItemsTheSame(
            oldItem: ImageSearchModel.Documents,
            newItem: ImageSearchModel.Documents
        ): Boolean {
            return oldItem.thumbnail_url == newItem.thumbnail_url
        }

        override fun areContentsTheSame(
            oldItem: ImageSearchModel.Documents,
            newItem: ImageSearchModel.Documents
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
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
        private val onBookmarkChecked: (ImageSearchModel.Documents, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImageSearchModel.Documents) = with(binding) {
            tvTitle.text = item.display_sitename
            tvTime.text = item.datetime
            Glide.with(itemView).load(item.image_url)
                .placeholder(R.drawable.test_cat) // 이미지를 로딩하기 전
                .error(R.drawable.baseline_error_outline_24) // 이미지를 불러오지 못했을 때
                .into(ivItem)


//            btnBookmark.isSelected = item.isBookmark
//            Glide.with(itemView).load(item.url)
//                .placeholder(R.drawable.test_cat) // 이미지를 로딩하기 전
//                .error(R.drawable.baseline_error_outline_24) // 이미지를 불러오지 못했을 때
//                .into(ivItem)
//
//            btnBookmark.setOnClickListener { isSelected ->
//                onBookmarkChecked(
//                    item.copy(
//                        isBookmark = !item.isBookmark
//                    ),
//                    adapterPosition
//                )
//            }
        }
    }

}