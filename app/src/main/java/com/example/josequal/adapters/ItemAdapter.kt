package com.example.josequal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.josequal.R
import com.example.josequal.model.MapItem
import com.google.android.gms.maps.GoogleMap


class ItemAdapter(private val items: List<MapItem>?, private val googleMap: GoogleMap) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var onItemClickListener: ((position: Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        onItemClickListener = listener
    }



    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item, parent, false)
        context = parent.context


        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.getOrNull(position)?.let { item ->
            holder.titleTxt.text = item.name
            holder.image.setImageResource(item.image)


            val place = items[position]

            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(position)
            }


        }
    }


    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.textView_title_vh)
        val image: ImageView = itemView.findViewById(R.id.imageView2_vh)
    }
}
