package com.civitatis.pruebadesarrolloandroid.presentation.fragments.detail_fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.civitatis.pruebadesarrolloandroid.databinding.FragmentDetailJobBinding
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse
import com.civitatis.pruebadesarrolloandroid.utlis.Logger
import com.civitatis.pruebadesarrolloandroid.utlis.loadUrl
import com.google.gson.Gson


class DetailJobFragment : Fragment() , Logger {

    override val nameClass: String get() = "--->"+javaClass.simpleName
    private lateinit var binding: FragmentDetailJobBinding
    private var json : String=""
    private var data : MyDataResponse?=null
    private val args: DetailJobFragmentArgs by navArgs()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailJobBinding.inflate(inflater,container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundleData()
        initComponents()
    }

    private fun getBundleData() {
        val bundle= this.arguments
        if(bundle!=null) {
            json = args.dataJob
            data= Gson().fromJson(json, MyDataResponse::class.java)
        }else{
            //Handle Error
            return
        }
    }

    private fun initComponents() {
        if(data!=null) {
            with(binding) {
                tvTitleJob.text= data?.title
                tvCompanyName.text= data?.company
                tvLocation.text= data?.location
                tvDate.text=data?.created_at
                tvUrlJob.text=data?.company_url
                tvType.text=data?.type
                tvDetailJob.text= Html.fromHtml(data?.description,Html.FROM_HTML_MODE_LEGACY)
                imgPhoto.loadUrl(data?.company_logo)

            }
        }
    }



}