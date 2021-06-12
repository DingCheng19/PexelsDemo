package com.unifa.pexelsdemo.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unifa.pexelsdemo.R
import com.unifa.pexelsdemo.data.model.PlPhoto
import com.unifa.pexelsdemo.data.model.SearchImageResult

class PhotosRecyclerViewAdapter(private val context:Context,private val dataSet: ArrayList<PlPhoto>,private val iimageClick:IImageClick) :
    RecyclerView.Adapter<PhotosRecyclerViewAdapter.ViewHolder>(){

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val photo:ImageView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.name)
            photo = view.findViewById(R.id.image)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_item_photo, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].photographer
        Glide.with(context).load(dataSet[position].src.original).placeholder(R.drawable.ic_image_placeholder).into(viewHolder.photo)
        viewHolder.photo.setOnClickListener(View.OnClickListener {
            iimageClick.onImageClickCallBack(dataSet[position].src.original)
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}