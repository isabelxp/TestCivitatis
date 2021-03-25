package com.civitatis.pruebadesarrolloandroid.presentation.fragments.listJob_fragment.adapter

import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.civitatis.pruebadesarrolloandroid.R
import com.civitatis.pruebadesarrolloandroid.databinding.ItemJobBinding
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse
import com.civitatis.pruebadesarrolloandroid.utlis.Consts
import com.civitatis.pruebadesarrolloandroid.utlis.inflate
import com.civitatis.pruebadesarrolloandroid.utlis.loadUrl
import kotlin.properties.Delegates


class JobAdapter(listItems: List<MyDataResponse> = emptyList(), private val listener: OnItemClickListener):
    RecyclerView.Adapter<JobAdapter.ViewHolder>() {


   var listItems: List<MyDataResponse> by Delegates.observable(listItems){ _, _, _ ->
        notifyDataSetChanged()
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.item_job), listener)


    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    interface OnItemClickListener {
        fun onClickItem(item: MyDataResponse, TAG: String)
    }

    class ViewHolder(private val view: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(view){

        private val binding = ItemJobBinding.bind(view)

        fun bind (item: MyDataResponse){
            with(binding){
                tvTitleJob.text= item.title
                tvCompanyName.text = item.company
                tvLocation.text = item.location
                tvType.text = item.type
                tvDate.text = item.created_at
                imgPhoto.loadUrl(item.company_logo)

                containerMain.setOnClickListener{
                    listener.onClickItem(item, Consts.Adapters.ALL)
                }
            }
        }
    }
}