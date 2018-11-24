package caseifood.app.com.caseifood.api

import caseifood.app.com.caseifood.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.GsonBuilder



object ApiModule {

    val BASE_URL = "http://api.openweathermap.org/"
    val restAdapter: Retrofit

    init {
        val gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .create()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        restAdapter = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    fun getApiInstance(): OpenWeatherMapApi = restAdapter.create(OpenWeatherMapApi::class.java)
}