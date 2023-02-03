package com.demo.codeassignment.common


import android.widget.ImageView
import com.demo.codeassignment.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object Utility {

    fun setImageUrl(imageView: ImageView, url: String) {
        val context = imageView.context
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        if (!url.isNullOrEmpty() ) {
            var strs = url
            Glide.with(context).load(strs).apply(options).into(imageView)
        }
        else {
             Glide.with(context).load(R.drawable.ic_launcher_background).into(imageView)
        }

    }
}