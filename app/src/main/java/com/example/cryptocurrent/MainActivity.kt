package com.example.cryptocurrent

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptocurrent.model.CryptoData
import com.example.cryptocurrent.model.CryptoResponse
import com.example.cryptocurrent.network.RetrofitInstance
import com.example.cryptocurrent.ui.theme.CryptoCurrentTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false) // Configura layout de borda a borda
        setContent {
            CryptoCurrentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CryptoListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CryptoListScreen(modifier: Modifier = Modifier) {
    var cryptoList by remember { mutableStateOf<List<CryptoData>>(emptyList()) }

    // Chamada à API para obter a lista de criptomoedas
    LaunchedEffect(Unit) {
        fetchCryptocurrencies { cryptos ->
            cryptoList = cryptos
        }
    }

    LazyColumn(modifier = modifier) {
        items(cryptoList) { crypto ->
            Text(text = "${crypto.name}: $${crypto.quote.USD.price}")
        }
    }
}

// Função para buscar criptomoedas usando Retrofit
fun fetchCryptocurrencies(onResult: (List<CryptoData>) -> Unit) {
    RetrofitInstance.api.getCryptocurrencies().enqueue(object : Callback<CryptoResponse> {
        override fun onResponse(call: Call<CryptoResponse>, response: Response<CryptoResponse>) {
            if (response.isSuccessful) {
                onResult(response.body()?.data ?: emptyList())
            } else {
                Log.e("MainActivity", "Erro: ${response.errorBody()}")
                onResult(emptyList())
            }
        }

        override fun onFailure(call: Call<CryptoResponse>, t: Throwable) {
            Log.e("MainActivity", "Falha na conexão: ${t.message}")
            onResult(emptyList())
        }
    })
}

@Preview(showBackground = true)
@Composable
fun CryptoListPreview() {
    CryptoCurrentTheme {
        CryptoListScreen()
    }
}
