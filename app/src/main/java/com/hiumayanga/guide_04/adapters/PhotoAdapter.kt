package com.hiumayanga.guide_04.adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hiumayanga.guide_04.R
import com.hiumayanga.guide_04.models.Photo
import com.squareup.picasso.Picasso
import retrofit2.Callback

class PhotoAdapter(
    private val photoList : List<Photo>,
    val context : Callback<List<Photo>>,
    private val onItemClicked: (position:Int)->Unit
):RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {


    class ViewHolder(
        ItemView: View,
        private val onItemIsClicked:(position:Int)->Unit
    ):RecyclerView.ViewHolder(ItemView),View.OnClickListener{

        val imgView = itemView.findViewById<ImageView>(R.id.imageview)
        val textTitle =itemView.findViewById<TextView>(R.id.textView_title)
        val textUrl=itemView.findViewById<TextView>(R.id.textview_url)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position =adapterPosition
            onItemIsClicked(position)
        }

    }

    //create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_view_design,parent,false)
        return ViewHolder(view, onItemClicked)
    }

    //binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel=photoList[position]

        Picasso.get()
            .load(itemViewModel.thumbnailUrl)
            .into(holder.imgView)
        holder.textTitle.text=itemViewModel.title
        holder.textUrl.text=itemViewModel.url
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}