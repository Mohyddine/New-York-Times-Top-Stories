package com.mehyo.nyttopstories.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.mehyo.nyttopstories.databinding.FragmentDetailsAbsBinding
import com.mehyo.nyttopstories.model.Result
import com.mehyo.nyttopstories.ui.dbvm.DatabaseViewModel

class DetailsAbsFragment : Fragment() {
    private lateinit var dbViewModel: DatabaseViewModel
    private val args by navArgs<DetailsAbsFragmentArgs>()
    private var _binding: FragmentDetailsAbsBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentDetailsAbsBinding.inflate(inflater, container, false)

        //Initialize ViewModel
        dbViewModel= ViewModelProvider(this).get(DatabaseViewModel::class.java)
        //calling setting data to views
        setViews()
        //Handeling add bookmark click
        binding.fab.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }
    //setting data to views
    fun setViews(){
            binding.imageViewDetails.load(args.currentResult.multimedia[0].url)
            binding.tvArticleAbstract.text=args.currentResult.abstract_article
            binding.tvWebLink.text=args.currentResult.short_url

    }
    //inserting current result to database using ViewModel
    private fun insertDataToDatabase() {
        var image_big=args.currentResult.multimedia[0].url
        var image_small=args.currentResult.multimedia[1].url
        var title=args.currentResult.title
        var abs=args.currentResult.abstract_article
        var link=args.currentResult.short_url
        var date=args.currentResult.published_date
        var result=Result(0,title,abs,date,image_big,image_small,link)
            //add user to DB
            dbViewModel.addResult(result)
            Toast.makeText(requireContext(),"Successfully added to Bookmark!", Toast.LENGTH_LONG).show()
    }

    //clear binding after closing the fragment
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}