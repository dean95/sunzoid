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

package com.raywenderlich.android.di

import com.raywenderlich.android.BuildConfig
import com.raywenderlich.android.data.network.client.WeatherApiClient
import com.raywenderlich.android.data.network.mapper.ApiMapper
import com.raywenderlich.android.data.network.mapper.ApiMapperImpl
import com.raywenderlich.android.domain.repository.WeatherRepository
import com.raywenderlich.android.data.WeatherRepositoryImpl
import com.raywenderlich.android.data.db.ForecastDatabase
import com.raywenderlich.android.data.db.mapper.DbMapper
import com.raywenderlich.android.data.db.mapper.DbMapperImpl
import com.raywenderlich.android.ui.home.mapper.HomeViewStateMapper
import com.raywenderlich.android.ui.home.mapper.HomeViewStateMapperImpl
import com.raywenderlich.android.util.image_loader.ImageLoader
import com.raywenderlich.android.util.image_loader.ImageLoaderImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL_NAME = "BASE_URL"
private const val BASE_URL = "https://www.metaweather.com/api/"
private const val MAIN_DISPATCHER = "main_dispatcher"
private const val BACKGROUND_DISPATCHER = "background_dispatcher"

val applicationModule = module {
  single(named(BASE_URL_NAME)) {
    BASE_URL
  }

  single {
    HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  single {
    val client = OkHttpClient().newBuilder()

    if (BuildConfig.DEBUG) {
      client.addInterceptor(get<HttpLoggingInterceptor>())
    }

    client.build()
  }

  single {
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
  }

  single {
    Retrofit.Builder()
        .baseUrl(get<String>(named(BASE_URL_NAME)))
        .addConverterFactory(MoshiConverterFactory.create(get()))
        .client(get())
        .build()
  }

  single {
    get<Retrofit>().create(WeatherApiClient::class.java)
  }

  single<ApiMapper> {
    ApiMapperImpl()
  }

  single<WeatherRepository> {
    WeatherRepositoryImpl(
        get(),
        get(),
        get(named(BACKGROUND_DISPATCHER)),
        get(),
        get()
    )
  }

  single<ImageLoader> { ImageLoaderImpl(get()) }

  single { Picasso.get() }

  single<HomeViewStateMapper> { HomeViewStateMapperImpl() }

  single(named(MAIN_DISPATCHER)) { Dispatchers.Main }

  single(named(BACKGROUND_DISPATCHER)) { Dispatchers.IO }

  single { ForecastDatabase.create(androidContext()) }

  single { get<ForecastDatabase>().forecastDao() }

  single<DbMapper> { DbMapperImpl() }
}