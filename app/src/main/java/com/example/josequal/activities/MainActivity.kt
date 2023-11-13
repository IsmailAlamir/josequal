package com.example.josequal.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.josequal.R
import com.example.josequal.adapters.ItemAdapter
import com.example.josequal.adapters.MarkerInfoWindowAdapter
import com.example.josequal.model.MapItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.data.kml.KmlLayer


class MainActivity : AppCompatActivity() {
    private var places: List<MapItem>?=null
    private lateinit var layer: KmlLayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment

        places = listOf(
            MapItem(
                name = "Luminus Technology",
                latLng = LatLng(31.897932,35.8713946),
                image = R.drawable.ltuc,
                details = "Luminus Technology University College"
            ),
            MapItem(
                name = "TennisPro Jordan",
                latLng = LatLng(31.910973,35.8838908),
                image = R.drawable.tennis,
                details = "Tennis, Padel & Fitness Center in Amman"
            ),
            MapItem(
                name = "Al-Zaytoonah University",
                latLng = LatLng(31.8462373,35.9020633),
                image = R.drawable.alzaytoonah,
                details = "Al-Zaytoonah Private University of Jordan (henceforth, Al-Zaytoonah)"
            ),
            MapItem(
                name = "Middle East University",
                latLng = LatLng(31.808568,35.9236969),
                image = R.drawable.meu,
                details = "Middle East University MEU"
            ),
            MapItem(
                name = "Isra University",
                latLng = LatLng(31.7970286,35.9384071),
                image = R.drawable.isra,
                details = "Al-Isra University is a private university in Amman, Jordan"
            ),
            MapItem(
                name = "University of Petra",
                latLng = LatLng(31.8960128,35.8915998),
                image = R.drawable.petra,
                details = "Petra University (UOP), a university where students really enjoy"
            )
        )
        mapFragment?.getMapAsync { googleMap ->
        layer = KmlLayer(googleMap, R.raw.kmllayer, applicationContext)
        layer?.addLayerToMap()}


        mapFragment?.getMapAsync { googleMap ->
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                places!!.forEach { bounds.include(it.latLng) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))

                val rv: RecyclerView = findViewById(R.id.recyclerView)
                rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                val itemAdapter = ItemAdapter(places, googleMap)
                rv.adapter = itemAdapter


                itemAdapter.setOnItemClickListener { position ->
                    val clickedItem = places?.getOrNull(position)
                    clickedItem?.let {
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(it.latLng))
                        Log.i("test", position.toString())

                    }
                }
            }

            addMarkers(googleMap)
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))




            googleMap.setOnMarkerClickListener { marker ->
                val mapItem = marker.tag as? MapItem
                mapItem?.let {
                    val dialog = AlertDialog.Builder(applicationContext)
                        .setTitle(mapItem.name)
                        .setMessage(mapItem.details)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()

                    dialog.show()
                }
                true
            }

        }







    }


    private fun addMarkers(googleMap: GoogleMap) {
        places?.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
            )
            marker.tag = place
        }
    }




    }


