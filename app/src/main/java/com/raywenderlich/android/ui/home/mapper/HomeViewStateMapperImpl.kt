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

package com.raywenderlich.android.ui.home.mapper

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.raywenderlich.android.domain.model.Forecast
import com.raywenderlich.android.domain.model.Location
import com.raywenderlich.android.domain.model.LocationDetails
import com.raywenderlich.android.ui.home.ForecastViewState
import com.raywenderlich.android.ui.home.LocationViewState
import com.raywenderlich.android.util.extensions.round
import kotlin.math.roundToInt

private const val IMAGE_BASE_URL = "https://www.metaweather.com//static/img/weather/png/64/"
private const val KM_IN_MILE = 1.61
private const val ONE_DECIMAL = 1
private const val TWO_DECIMALS = 2

class HomeViewStateMapperImpl : HomeViewStateMapper {

  override fun mapLocationDetailsToViewState(locationDetails: LocationDetails): List<ForecastViewState> {
    return locationDetails.forecasts.map { mapForecastToViewState(it) }
  }

  override fun mapLocationsToViewState(locations: List<Location>): List<LocationViewState> {
    return locations.map { LocationViewState(it.id, it.title) }
  }

  private fun mapForecastToViewState(forecast: Forecast): ForecastViewState {
    return with(forecast) {
      ForecastViewState(
          state,
          windDirection,
          applySpanToLabel("Date", date),
          applySpanToLabel("${forecast.minTemp.round(ONE_DECIMAL)} — ${forecast.maxTemp.round(ONE_DECIMAL)}", "°C"),
          "${temp.round(ONE_DECIMAL)}°C",
          applySpanToLabel("Wind speed", "${windSpeed.times(KM_IN_MILE).round(TWO_DECIMALS)} km/h"),
          applySpanToLabel("Pressure", "${pressure.round(ONE_DECIMAL)} mbar"),
          applySpanToLabel("Humidity", "${humidity.roundToInt()}%"),
          applySpanToLabel("Visibility", "${visibility.times(KM_IN_MILE).round(ONE_DECIMAL)} km"),
          applySpanToLabel("Predictability", "${predictability}%"),
          "$IMAGE_BASE_URL${weatherStateAbbreviation}.png"
      )
    }
  }

  private fun applySpanToLabel(label: String, value: String): SpannableString {
    val spannable = SpannableString("$label $value")

    spannable.setSpan(
        StyleSpan(BOLD),
        0,
        label.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    spannable.setSpan(
        ForegroundColorSpan(Color.WHITE),
        0,
        label.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return spannable
  }
}