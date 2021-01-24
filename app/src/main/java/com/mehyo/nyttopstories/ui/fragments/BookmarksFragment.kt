package com.mehyo.nyttopstories.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mehyo.nyttopstories.NYTModel
import com.mehyo.nyttopstories.databinding.FragmentBookmarksBinding
import com.mehyo.nyttopstories.model.Result
import com.mehyo.nyttopstories.ui.adapter.BookmarksGridAdapter
import com.mehyo.nyttopstories.ui.dbvm.DatabaseViewModel
import com.mehyo.nyttopstories.ui.vm.NYTViewModel

class BookmarksFragment : Fragment() {
    lateinit var dbViewModel: DatabaseViewModel
    lateinit var myAdapter: BookmarksGridAdapter
    private var _binding: FragmentBookmarksBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentBookmarksBinding.inflate(inflater, container, false)

        //calling Initialize RecyclerView
        initRecyclerView()
        //calling Initialize List of data
        createData()

        return binding.root
    }
    //Initializing RecyclerView
    fun initRecyclerView() {
        binding.RecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            myAdapter = BookmarksGridAdapter()
            adapter = myAdapter
        }
    }
    //Initializing List of data inside RecyclerView using ViewModel
    fun createData(){
        dbViewModel=ViewModelProvider(this).get(DatabaseViewModel::class.java)
        dbViewModel.readAllData.observe(viewLifecycleOwner, Observer {result->
            myAdapter.setData(result)
        })
        dbViewModel.readAllData
    }

    //clear binding after closing the fragment
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}