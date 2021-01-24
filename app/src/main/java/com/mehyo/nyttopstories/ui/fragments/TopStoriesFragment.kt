package com.mehyo.nyttopstories.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mehyo.nyttopstories.NYTModel
import com.mehyo.nyttopstories.ui.adapter.TopStoriesListAdapter
import com.mehyo.nyttopstories.databinding.FragmentTopStoriesBinding
import com.mehyo.nyttopstories.ui.vm.NYTViewModel

class TopStoriesFragment : Fragment() {
    lateinit var nytViewModel: NYTViewModel
    lateinit var myAdapter: TopStoriesListAdapter
    private var _binding: FragmentTopStoriesBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTopStoriesBinding.inflate(inflater, container, false)
        //calling Initialize RecyclerView
        initRecyclerView()
        //calling Initialize List of data
        createData()



        return binding.root
    }

    //Initializing RecyclerView
    fun initRecyclerView() {
        binding.RecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            myAdapter = TopStoriesListAdapter()
            adapter = myAdapter

            val decoration = DividerItemDecoration(context, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }
    }
    //Initializing List of data inside RecyclerView using ViewModel
    fun createData(){
        nytViewModel= ViewModelProvider(this).get(NYTViewModel::class.java)
        nytViewModel.getRecyclerListDataObserver().observe(viewLifecycleOwner, Observer<NYTModel>
        {
            if (it != null) {
                myAdapter.apply {
                setData(it.results)
                notifyDataSetChanged()}
            } else {
                Toast.makeText(context, "Error in getting the data from the API", Toast.LENGTH_SHORT).show()
            }

        })
        nytViewModel.getResults()
    }


    //clear binding after closing the fragment
        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
    }
