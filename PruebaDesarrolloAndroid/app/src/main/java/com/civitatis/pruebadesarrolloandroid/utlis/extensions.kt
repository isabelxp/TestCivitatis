package com.civitatis.pruebadesarrolloandroid.utlis

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.civitatis.pruebadesarrolloandroid.R
import de.hdodenhof.circleimageview.CircleImageView


fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return   LayoutInflater
            .from(context)
            .inflate(R.layout.item_job, this, false)
}

fun RecyclerView.ViewHolder.showToast(message: String){
    itemView.context.showToast(message)
}

fun Context.showToast(text: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, text, length).show()
}

fun CircleImageView.loadUrl(url: String?) {
    Glide.with(this)
        .applyDefaultRequestOptions(
            RequestOptions()
                .error(R.drawable.ic_baseline_work_24)
        )
        .load(url)
        .into(this)
}

fun CircleImageView.loadUrl(uri: Uri) {
    Glide.with(this)
        .applyDefaultRequestOptions(
            RequestOptions()
                .error(R.drawable.ic_baseline_work_24)
        )
        .load(uri)
        .into(this)
}

