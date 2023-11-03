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

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng

class MapPresenter(private val activity: AppCompatActivity) {

  val ui = MutableLiveData(Ui.EMPTY)

  private val locationProvider = LocationProvider(activity)

  private val stepCounter = StepCounter(activity)

  private val permissionsManager = PermissionsManager(activity, locationProvider, stepCounter)

  fun onViewCreated() {

    locationProvider.liveLocations.observe(activity) { locations ->
      val current = ui.value
      ui.value = current?.copy(userPath = locations)
    }

    locationProvider.liveLocation.observe(activity) { currentLocation ->
      val current = ui.value
      ui.value = current?.copy(currentLocation = currentLocation)
    }

    locationProvider.liveDistance.observe(activity) { distance ->
      val current = ui.value
      val formattedDistance = activity.getString(R.string.distance_value, distance)
      ui.value = current?.copy(formattedDistance = formattedDistance)
    }

    stepCounter.liveSteps.observe(activity) { steps ->
      val current = ui.value
      ui.value = current?.copy(formattedPace = "$steps")
    }
  }

  fun onMapLoaded() {
    permissionsManager.requestUserLocation()
  }

  fun startTracking() {
    permissionsManager.requestActivityRecognition()
    locationProvider.trackUser()

    val currentUi = ui.value
    ui.value = currentUi?.copy(
        formattedPace = Ui.EMPTY.formattedPace,
        formattedDistance = Ui.EMPTY.formattedDistance
    )
  }

  fun stopTracking() {
    locationProvider.stopTracking()
    stepCounter.unloadStepCounter()
  }
}

data class Ui(
    val formattedPace: String,
    val formattedDistance: String,
    val currentLocation: LatLng?,
    val userPath: List<LatLng>
) {

  companion object {

    val EMPTY = Ui(
        formattedPace = "",
        formattedDistance = "",
        currentLocation = null,
        userPath = emptyList()
    )
  }
}