package com.adso.segunadaapp.superherodosapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperHeroImageDetailResponse,
    @SerializedName("biography") val biography:Biography,
    @SerializedName("appearance") val appearance: Aperance,
    @SerializedName("connections") val connections: Connections,
    @SerializedName("work") val work: Work
)


data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class SuperHeroImageDetailResponse(@SerializedName("url") val url: String)

data class Biography(
    @SerializedName("full-name") val fullname: String,
    @SerializedName("publisher") val publisher: String
)

data class Aperance(
    @SerializedName("gender") val gender: String,
    @SerializedName("race") val race:String
)

data class Connections(
    @SerializedName("relatives") val relatives:String
)

data class Work(
    @SerializedName("occupation") val occupation:String
)
