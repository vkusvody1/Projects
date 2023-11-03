/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.runtracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.raywenderlich.android.runtracker.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

  private lateinit var map: GoogleMap
  private lateinit var binding: ActivityMapsBinding

  private val presenter = MapPresenter(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)

    binding = ActivityMapsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    val mapFragment = supportFragmentManager
        .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)

    binding.btnStartStop.setOnClickListener {
      if (binding.btnStartStop.text == getString(R.string.start_label)) {
        startTracking()
        binding.btnStartStop.setText(R.string.stop_label)
      } else {
        stopTracking()
        binding.btnStartStop.setText(R.string.start_label)
      }
    }

    presenter.onViewCreated()
  }

  /**
   * Manipulates the map once available.
   * This callback is triggered when the map is ready to be used.
   * This is where we can add markers or lines, add listeners or move the camera. In this case,
   * we just add a marker near Sydney, Australia.
   * If Google Play services is not installed on the device, the user will be prompted to install
   * it inside the SupportMapFragment. This method will only be triggered once the user has
   * installed Google Play services and returned to the app.
   */
  override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap

    presenter.ui.observe(this) { ui ->
      updateUi(ui)
    }

    presenter.onMapLoaded()
    map.uiSettings.isZoomControlsEnabled = true
  }

  private fun startTracking() {
    binding.container.txtPace.text = ""
    binding.container.txtDistance.text = ""
    binding.container.txtTime.base = SystemClock.elapsedRealtime()
    binding.container.txtTime.start()
    map.clear()

    presenter.startTracking()
  }

  private fun stopTracking() {
    presenter.stopTracking()
    binding.container.txtTime.stop()
  }

  @SuppressLint("MissingPermission")
  private fun updateUi(ui: Ui) {
    if (ui.currentLocation != null && ui.currentLocation != map.cameraPosition.target) {
      map.isMyLocationEnabled = true
      map.animateCamera(CameraUpdateFactory.newLatLngZoom(ui.currentLocation, 14f))
    }
    binding.container.txtDistance.text = ui.formattedDistance
    binding.container.txtPace.text = ui.formattedPace
    drawRoute(ui.userPath)
  }

  private fun drawRoute(locations: List<LatLng>) {
    val polylineOptions = PolylineOptions()

    map.clear()

    val points = polylineOptions.points
    points.addAll(locations)

    map.addPolyline(polylineOptions)
  }
}