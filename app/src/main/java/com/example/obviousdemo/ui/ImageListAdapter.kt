package com.example.obviousdemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.obviousdemo.R
import com.example.obviousdemo.databinding.ImageItemRowLayoutBinding
import com.example.obviousdemo.model.ResponseItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageListAdapter(val items: List<ResponseItem?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClick: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ListingItemViewHolder(
            ImageItemRowLayoutBinding.inflate(
                inflater,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listingItemViewHolder = holder as? ListingItemViewHolder
        listingItemViewHolder?.bind(items?.get(position))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class ListingItemViewHolder(val binding: ImageItemRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ResponseItem?) {
            binding?.imageItem = model
            binding.executePendingBindings()
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }
    }
}

@BindingAdapter("bind:imageUrl")
public fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.color.cardview_light_background)
        .fit()
        .centerCrop()
        .into(view, object : Callback {
            override fun onSuccess() {
                // Log.d(TAG, "success")
            }

            override fun onError(e: Exception?) {
            }
        })
}