package com.civitatis.pruebadesarrolloandroid.presentation.fragments.listJob_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.civitatis.pruebadesarrolloandroid.databinding.FragmentJobListBinding
import com.civitatis.pruebadesarrolloandroid.domain.Resource
import com.civitatis.pruebadesarrolloandroid.domain.entity.MyDataResponse
import com.civitatis.pruebadesarrolloandroid.presentation.fragments.listJob_fragment.adapter.JobAdapter
import com.civitatis.pruebadesarrolloandroid.utlis.Consts
import com.civitatis.pruebadesarrolloandroid.utlis.Logger
import com.google.gson.Gson


class JobListFragment : Fragment(), Logger,
    JobAdapter.OnItemClickListener {

    override val nameClass: String get() = "--->"+javaClass.simpleName
    private lateinit var binding: FragmentJobListBinding
    private val initAdapter =
        JobAdapter(
            emptyList(),
            this
        )

    private var itemList: MutableList<MyDataResponse> = arrayListOf()
    private lateinit var viewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        with(binding){
            recycler.adapter = initAdapter

            swipeRefresh.setOnRefreshListener {
                if(swipeRefresh.isRefreshing)
                    swipeRefresh.isRefreshing=false
                viewModel.getData()
            }
        }

        viewModel.myDataLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showProgress(true)
                }
                is Resource.Success -> {
                    showProgress(false)
                    logD(resource.data?.size.toString())
                    updateDataList(resource.data ?: arrayListOf())
                }
                is Resource.Error -> {
                    showProgress(false)
                }
            }
        })
        viewModel.getData()
    }

    private fun updateDataList(list: ArrayList<MyDataResponse>) {
        this.itemList = list
        initAdapter.listItems = itemList
    }

    private fun showProgress(control: Boolean) {
        binding.progress.visibility= (if (control) View.VISIBLE else View.GONE)
    }

    override fun onClickItem(item: MyDataResponse, TAG: String) {
        logD(Gson().toJson(item))
        when(TAG){
            Consts.Adapters.ALL->{
                val args = Bundle()
                args.putString(Consts.Arg.ITEM_DATA, Gson().toJson(item))

                val action =
                    JobListFragmentDirections.actionJobListToDetailJob(Gson().toJson(item))
                    Navigation.findNavController(requireView())
                    .navigate(action)
            }
        }
    }
}