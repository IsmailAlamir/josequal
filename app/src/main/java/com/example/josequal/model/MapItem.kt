package com.example.josequal.model

import com.google.android.gms.maps.model.LatLng


data class MapItem(

    val name: String,
    val latLng: LatLng,
    val image: Int,
    val details: String

)