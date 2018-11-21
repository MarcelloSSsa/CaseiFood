package caseifood.app.com.caseifood.api

import caseifood.app.com.caseifood.api.payload.WeatherResult
import retrofit2.Call
import retrofit2.http.GET

interface OpenWeatherMapApi {

    @GET("/data/2.5/weather/city?lat={lat}&lon={lon}")
    fun getCurrentWeatherByLocation(latitude: Double,
                             longitude: Double): Call<WeatherResult>

}