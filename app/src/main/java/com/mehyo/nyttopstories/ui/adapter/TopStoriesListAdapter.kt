package com.mehyo.nyttopstories.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.mehyo.nyttopstories.R
import com.mehyo.nyttopstories.Results
import com.mehyo.nyttopstories.databinding.ItemListBinding
import com.mehyo.nyttopstories.ui.fragments.ViewPagerFragmentDirections
import okio.IOException
import java.lang.Exception
import java.lang.NullPointerException

class TopStoriesListAdapter(): RecyclerView.Adapter<TopStoriesListAdapter.ListViewHolder>() {
    private var topStoriesList= ArrayList<Results>()

    //Fun to Initialize List of data inside RecyclerView
    fun setData(list:ArrayList<Results>){
        this.topStoriesList.addAll(list)
        notifyDataSetChanged()
    }

    //custom ViewHolder class
    class ListViewHolder(binding: ItemListBinding):RecyclerView.ViewHolder(binding.root) {
        val tvTitle=binding.tvTitle
        val tvTime=binding.tvTime
        val imageView=binding.imageView


        //binding data to views
        fun bind(data: Results,itemView:View){
            tvTitle.text=data.title
            tvTime.text=data.published_date
            try{
                imageView.load(data.multimedia[1].url){
                    crossfade(true)
                    placeholder(R.drawable.ic_image)
                    transformations(RoundedCornersTransformation(10f))
                }
            }catch (e:NullPointerException){
                Log.d(this.javaClass.name,"error")
                imageView.load(R.drawable.ic_image)
            }
            itemView.setOnClickListener {
                val action:NavDirections= ViewPagerFragmentDirections
                        .actionViewPager2FragmentToDetailsAbsFragment(data)
                it.findNavController().navigate(action)
            }
        }
    }

    //inflating the item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder( ItemListBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    //binding data to views in specific position
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(topStoriesList[position],holder.itemView)
    }

    //list size to get recyclerview size
    override fun getItemCount(): Int =topStoriesList.size
}
