package com.example.josequal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.josequal.R
import com.example.josequal.model.MapItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MarkerInfoWindowAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker?): View? {
        val place = marker?.tag as? MapItem ?: return null

        val view = LayoutInflater.from(context).inflate(
            R.layout.marker_info_contents, null
        )
        view.findViewById<TextView>(
            R.id.text_view_title_main
        ).text = place.name

        view.findViewById<TextView>(
            R.id.text_view_details_main
        ).text = place.details

        view.findViewById<ImageView>(
            R.id.imageView_main
        ).setImageResource(place.image)

        return view
    }

    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }
}
