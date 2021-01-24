package com.mehyo.nyttopstories.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mehyo.nyttopstories.R
import com.mehyo.nyttopstories.databinding.ItemGridBinding
import com.mehyo.nyttopstories.model.Result
import com.mehyo.nyttopstories.ui.fragments.ViewPagerFragmentDirections

class BookmarksGridAdapter(): RecyclerView.Adapter<BookmarksGridAdapter.GridViewHolder>() {
    private var topStoriesGrid= emptyList<Result>()

    //Fun to Initialize List of data inside RecyclerView
    fun setData(result: List<Result>){
        this.topStoriesGrid=result
        notifyDataSetChanged()
    }

    //custom ViewHolder class
    class GridViewHolder(binding: ItemGridBinding):RecyclerView.ViewHolder(binding.root) {
        val tvTitle=binding.tvTitle
        val tvTime=binding.tvTime
        val imageView=binding.imageView

        //binding data to views
        fun bind(data: Result){
            tvTitle.text=data.title
            tvTime.text=data.published_date
            imageView.load(data.multimedia_small){
                crossfade(true)
                placeholder(R.drawable.ic_image)
            }
            tvTitle.setOnClickListener {
                val action:NavDirections= ViewPagerFragmentDirections
                        .actionViewPager2FragmentToDetailsAbsBookmarkFragment(data)
                it.findNavController().navigate(action)
            }
        }
    }

    //inflating the item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder( ItemGridBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    //binding data to views in specific position
    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(topStoriesGrid[position])
    }

    //list size to get recyclerview size
    override fun getItemCount(): Int =topStoriesGrid.size
}
