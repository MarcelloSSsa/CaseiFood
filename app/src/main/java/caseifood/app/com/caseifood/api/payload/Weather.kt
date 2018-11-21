package caseifood.app.com.caseifood.api.payload

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("main") val description: String = "")