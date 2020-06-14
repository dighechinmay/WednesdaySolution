package com.chinmay.wednesdayitunesapp.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chinmay.itunesappwednesday.R
import com.chinmay.itunesappwednesday.databinding.TrackItemBinding
import com.chinmay.itunesappwednesday.model.ItunesDataModel
import com.chinmay.itunesappwednesday.ui.post.ItunesCardViewModel


class ItunesListAdapter: RecyclerView.Adapter<ItunesListAdapter.ViewHolder>() {
    private lateinit var postList:List<ItunesDataModel.Results>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesListAdapter.ViewHolder {
        val binding: TrackItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.track_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItunesListAdapter.ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<ItunesDataModel.Results>){
        this.postList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: TrackItemBinding):RecyclerView.ViewHolder(binding.root){

        private val viewModel = ItunesCardViewModel()
        fun bind(result:ItunesDataModel.Results){
            viewModel.bind(result)
            binding.viewModel = viewModel
        }
    }
}