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
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import kotlin.math.roundToInt

@SuppressLint("MissingPermission")
class LocationProvider(private val activity: AppCompatActivity) {

  private val client by lazy { LocationServices.getFusedLocationProviderClient(activity) }

  private val locations = mutableListOf<LatLng>()
  private var distance = 0

  val liveLocations = MutableLiveData<List<LatLng>>()
  val liveDistance = MutableLiveData<Int>()
  val liveLocation = MutableLiveData<LatLng>()

  private val locationCallback = object : LocationCallback() {
    override fun onLocationResult(result: LocationResult) {
      val currentLocation = result.lastLocation
      val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)

      val lastLocation = locations.lastOrNull()

      if (lastLocation != null) {
        distance += SphericalUtil.computeDistanceBetween(lastLocation, latLng).roundToInt()
        liveDistance.value = distance
      }

      locations.add(latLng)
      liveLocations.value = locations
    }
  }

  fun getUserLocation() {
    client.lastLocation.addOnSuccessListener { location ->
      val latLng = LatLng(location.latitude, location.longitude)
      locations.add(latLng)
      liveLocation.value = latLng
    }
  }

  fun trackUser() {
    val locationRequest = LocationRequest.create()
    locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    locationRequest.interval = 5000
    client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
  }

  fun stopTracking() {
    client.removeLocationUpdates(locationCallback)
    locations.clear()
    distance = 0
  }
}