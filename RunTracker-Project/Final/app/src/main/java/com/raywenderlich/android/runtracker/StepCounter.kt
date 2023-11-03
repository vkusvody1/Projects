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

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData

class StepCounter(private val activity: AppCompatActivity) : SensorEventListener {

  interface StepUpdater {

    fun onStepUpdated(steps: Int)
  }

  val liveSteps = MutableLiveData<Int>()

  private val sensorManager by lazy {
    activity.getSystemService(SENSOR_SERVICE) as SensorManager
  }

  private val stepCounterSensor: Sensor? by lazy {
    sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
  }

  private var initialSteps = -1

  fun setupStepCounter() {
    if (stepCounterSensor != null) {
      sensorManager.registerListener(this, stepCounterSensor, SENSOR_DELAY_FASTEST)
    }
  }

  override fun onSensorChanged(event: SensorEvent) {
    event.values.firstOrNull()?.toInt()?.let { newSteps ->
      if (initialSteps == -1) {
        initialSteps = newSteps
      }

      val currentSteps = newSteps - initialSteps

      liveSteps.value = currentSteps
    }
  }

  fun unloadStepCounter() {
    if (stepCounterSensor != null) {
      sensorManager.unregisterListener(this)
    }
  }

  override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) = Unit
}