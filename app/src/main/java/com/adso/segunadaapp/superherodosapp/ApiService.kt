package com.adso.segunadaapp.superherodosapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/1029541304709110/search/{name}")
    suspend fun getSuperheores(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    @GET("/api/1029541304709110/{id}")
    suspend fun getSuperHeroDetail(@Path("id") superHeroId: String ): Response<SuperHeroDetailResponse>
}