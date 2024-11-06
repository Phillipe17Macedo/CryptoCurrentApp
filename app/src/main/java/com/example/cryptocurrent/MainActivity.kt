package com.example.cryptocurrent

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
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
    var isLoading by remember { mutableStateOf(true) }

    // Chamada à API para obter a lista de criptomoedas
    LaunchedEffect(Unit) {
        fetchCryptocurrencies { cryptos ->
            cryptoList = cryptos
            isLoading = false
        }
    }

    Column(modifier = modifier.fillMaxSize().background(Color(0xFF3D3D3D))) {
        Text(
            text = "Tabela de Criptomoedas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF84B026),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF84B026))
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(cryptoList) { crypto ->
                    CryptoItem(crypto = crypto)
                }
            }
        }
    }
}

@Composable
fun CryptoItem(crypto: CryptoData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF217373))
            .padding(16.dp)
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = "https://s2.coinmarketcap.com/static/img/coins/64x64/${crypto.id}.png"),
            contentDescription = null,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = crypto.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Preço: U$${crypto.quote.USD.price}",
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = "Variação (24h): ${crypto.quote.USD.percent_change_24h}%",
                fontSize = 16.sp,
                color = Color.White
            )
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
