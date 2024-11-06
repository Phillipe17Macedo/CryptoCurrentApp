package com.example.cryptocurrent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrent.model.Crypto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {
    private val _cryptoList = MutableStateFlow<List<Crypto>>(emptyList())
    val cryptoList: StateFlow<List<Crypto>> = _cryptoList

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchCryptoData()
    }

    private fun fetchCryptoData() {
        viewModelScope.launch {
            // Simulando chamada de API
            _cryptoList.value = listOf(
                Crypto(id = 1, name = "Bitcoin", price = "40000", change = "2.5"),
                Crypto(id = 2, name = "Ethereum", price = "3000", change = "-1.2")
            )
            _isLoading.value = false
        }
    }
}