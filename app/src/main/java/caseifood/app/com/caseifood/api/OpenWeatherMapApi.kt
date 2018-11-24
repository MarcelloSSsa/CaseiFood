package caseifood.app.com.caseifood.api

import caseifood.app.com.caseifood.api.payload.WeatherResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("/data/2.5/weather?appid=a67ef818d8c8e3945f7eee5f541c47e5")
    fun getCurrentWeatherByLocation(@Query("lat") latitude: Double,
                                    @Query("lon") longitude: Double): Call<WeatherResult>

}