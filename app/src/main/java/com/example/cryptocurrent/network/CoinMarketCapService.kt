package com.example.cryptocurrent.network

import com.example.cryptocurrent.model.CryptoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoinMarketCapService {
    @Headers("X-CMC_PRO_API_KEY: 3f640653-8b8f-4bf3-acad-5d5b20c6e1dc") // Substitua YOUR_API_KEY pela sua chave de API
    @GET("v1/cryptocurrency/listings/latest")
    fun getCryptocurrencies(
        @Query("start") start: Int = 1,
        @Query("limit") limit: Int = 10
    ): Call<CryptoResponse>
}