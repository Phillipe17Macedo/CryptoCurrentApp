package com.example.cryptocurrent.model

// Modelo principal que representa a resposta da API
data class CryptoResponse(
    val data: List<CryptoData>  // Lista de criptomoedas
)

// Modelo que representa cada criptomoeda
data class CryptoData(
    val id: Int,                  // ID da criptomoeda
    val name: String,             // Nome da criptomoeda (ex: Bitcoin)
    val symbol: String,           // Símbolo da criptomoeda (ex: BTC)
    val quote: Quote              // Cotações da criptomoeda
)

// Modelo que representa a cotações
data class Quote(
    val USD: Price                // Preço em USD
)

// Modelo que representa o preço
data class Price(
    val price: Double,             // Valor do preço
    val percent_change_24h: String
)