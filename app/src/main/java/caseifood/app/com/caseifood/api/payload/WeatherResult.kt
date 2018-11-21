package caseifood.app.com.caseifood.api.payload

import com.google.gson.annotations.SerializedName

data class WeatherResult(@SerializedName("weather") val weatherDescription: List<Weather>,
                         @SerializedName("main") val weatherValues: WeatherValues)