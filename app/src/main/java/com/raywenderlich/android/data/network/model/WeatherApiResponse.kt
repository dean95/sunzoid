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

package com.raywenderlich.android.data.network.model

import com.squareup.moshi.Json

data class ApiLocation(
    val title: String,
    @Json(name = "location_type") val locationType: String,
    @Json(name = "woeid") val id: Int,
    @Json(name = "latt_long") val latitudeLongitude: String
)

data class ApiLocationDetails(
    @Json(name = "consolidated_weather") val forecasts: List<ApiForecast>,
    val time: String,
    @Json(name = "sun_rise") val sunrise: String,
    @Json(name = "sun_set") val sunset: String,
    val title: String,
    @Json(name = "woeid") val id: Int
)

data class ApiForecast(
    val id: Long,
    @Json(name = "weather_state_name") val weatherState: String,
    @Json(name = "weather_state_abbr") val weatherStateAbbreviation: String,
    @Json(name = "wind_direction_compass") val windDirection: String,
    @Json(name = "applicable_date") val date: String,
    @Json(name = "min_temp") val minTemp: Double,
    @Json(name = "max_temp") val maxTemp: Double,
    @Json(name = "the_temp") val temp: Double,
    @Json(name = "wind_speed") val windSpeed: Double,
    @Json(name = "air_pressure") val airPressure: Double,
    @Json(name = "humidity") val humidity: Double,
    @Json(name = "visibility") val visibility: Double,
    @Json(name = "predictability") val predictability: Int
)
