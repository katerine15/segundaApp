package com.adso.segunadaapp.superherodosapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superherores: List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val superHeroName: String,
    @SerializedName("image") val superHeroImage: SuperHeroImageResponse
)
data class SuperHeroImageResponse(@SerializedName("url") val url:String)