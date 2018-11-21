package caseifood.app.com.caseifood.api.payload

import com.google.gson.annotations.SerializedName

data class WeatherValues(
    @SerializedName("temp") val id: Double = 0.0)