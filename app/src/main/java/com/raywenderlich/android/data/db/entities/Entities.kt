/*
 * Copyright (c) 2020 Razeware LLC
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_details_table")
data class DbLocationDetails(
    val time: String,
    val sunrise: String,
    val sunset: String,
    val title: String,
    @PrimaryKey val id: Int
) {
  companion object {
    val EMPTY = DbLocationDetails("", "", "", "", -1)
  }
}

@Entity(tableName = "forecasts_table")
data class DbForecast(
    @PrimaryKey val id: Long,
    val state: String,
    val windDirection: String,
    val date: String,
    val minTemp: Double,
    val maxTemp: Double,
    val temp: Double,
    val windSpeed: Double,
    val pressure: Double,
    val humidity: Double,
    val visibility: Double,
    val predictability: Int,
    val weatherStateAbbreviation: String
)