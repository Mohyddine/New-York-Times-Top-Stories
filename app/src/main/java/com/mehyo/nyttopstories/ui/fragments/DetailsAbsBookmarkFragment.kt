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
import com.mehyo.nyttopstories.R
import com.mehyo.nyttopstories.databinding.FragmentDetailsAbsBookmarkBinding
import com.mehyo.nyttopstories.model.Result
import com.mehyo.nyttopstories.ui.dbvm.DatabaseViewModel


class DetailsAbsBookmarkFragment : Fragment() {
    private lateinit var dbViewModel: DatabaseViewModel
    private val args by navArgs<DetailsAbsBookmarkFragmentArgs>()
    private var _binding: FragmentDetailsAbsBookmarkBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentDetailsAbsBookmarkBinding.inflate(inflater, container, false)
        //Initialize ViewModel
        dbViewModel= ViewModelProvider(this).get(DatabaseViewModel::class.java)
        //calling setting data to views
        setViews()
        //Handeling delete bookmark click and navigating back to the list
        binding.fab.setOnClickListener {
            deleteDataFromDatabase()
            findNavController().navigate(R.id.action_detailsAbsBookmarkFragment_to_viewPager2Fragment)
        }

        return binding.root
    }
    //setting data to views
    fun setViews(){
        binding.imageViewDetails.load(args.currentResult2.multimedia_big)
        binding.tvArticleAbstract.text=args.currentResult2.abstract_article
        binding.tvWebLink.text=args.currentResult2.short_url
    }
    //deleting current result From database using ViewModel
    private fun deleteDataFromDatabase() {
        var id=args.currentResult2.id
        var image_big=args.currentResult2.multimedia_big
        var image_small=args.currentResult2.multimedia_small
        var title=args.currentResult2.title
        var abs=args.currentResult2.abstract_article
        var link=args.currentResult2.short_url
        var date=args.currentResult2.published_date
        var result= Result(id,title,abs,date,image_big,image_small,link)
        //add user to DB
        dbViewModel.deleteResult(result)
        Toast.makeText(requireContext(),"Successfully Deleted from Bookmark!", Toast.LENGTH_LONG).show()
    }

    //clear binding after closing the fragment
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}