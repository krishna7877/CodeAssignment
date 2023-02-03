package com.demo.codeassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.codeassignment.common.Utility
import com.demo.codeassignment.data.models.PhotosList
import com.demo.codeassignment.databinding.ListItemBinding

class MainListAdapter():  PagingDataAdapter<PhotosList, MainListAdapter.ViewHolder>(PhotosComparator) {
    var mEventListener: EventListnear? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photoslist = getItem(position)!!
        holder.view.tvName.text = photoslist.author
     //   holder.view.tvDescriptions.text = holder.itemView.context.resources.getString(R.string.test)
        holder.view.tvDescriptions.text = photoslist.author
        Utility.setImageUrl(holder.view.imgPhotos,photoslist.downloadUrl!!)
        holder.itemView.setOnClickListener{
            if (mEventListener != null) {
                photoslist.author?.let { it1 -> mEventListener!!.onItemClicked(it1,
                    photoslist.downloadUrl!!
                ) };
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(val view: ListItemBinding): RecyclerView.ViewHolder(view.root) {

    }

    object PhotosComparator: DiffUtil.ItemCallback<PhotosList>() {
        override fun areItemsTheSame(oldItem: PhotosList, newItem: PhotosList): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotosList, newItem: PhotosList): Boolean {
            return oldItem == newItem
        }
    }

    interface EventListnear {
        fun onItemClicked(title: String,url: String)
    }
}