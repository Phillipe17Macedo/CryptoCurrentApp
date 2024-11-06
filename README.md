
# CryptoCurrent

CryptoCurrent é um aplicativo Android desenvolvido em Kotlin usando Jetpack Compose, Retrofit e Coil. O aplicativo se conecta à API do CoinMarketCap para exibir uma lista de criptomoedas, incluindo informações como nome, preço e variação nas últimas 24 horas.

## Funcionalidades

- Exibição de uma lista de criptomoedas com detalhes como nome, preço e variação de 24 horas.
- Atualização das imagens das criptomoedas diretamente da web usando Coil.
- Carregamento dinâmico de dados da API do CoinMarketCap.

## Pré-requisitos

- Android Studio com versão mínima de SDK 24.
- Chave de API do CoinMarketCap.
- Conexão com a internet para carregar dados e imagens.

## Configuração do Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/Phillipe17Macedo/CryptoCurrentApp.git
   cd CryptoCurrentApp
   ```

2. **Adicione a chave da API**: 
   Insira sua chave de API do CoinMarketCap em `RetrofitInstance.kt`:

   ```kotlin
   private const val API_KEY = "YOUR_API_KEY"
   ```

3. **Instale as dependências**: 
   Assegure-se de que as dependências de Coil e Retrofit estão presentes no arquivo `build.gradle.kts`:

   ```kotlin
   dependencies {
       implementation("io.coil-kt:coil-compose:2.1.0")
       implementation("com.squareup.retrofit2:retrofit:2.9.0")
       implementation("com.squareup.retrofit2:converter-gson:2.9.0")
       implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
   }
   ```

4. **Sincronize o projeto** em Android Studio.

## Estrutura do Projeto

O projeto segue a estrutura de pastas abaixo:

```plaintext
CryptoCurrent
├── model
│   ├── CryptoData.kt           # Dados das criptomoedas
│   └── CryptoResponse.kt       # Modelo de resposta da API
├── network
│   └── RetrofitInstance.kt     # Instância Retrofit para API do CoinMarketCap
├── ui.theme
│   └── CryptoCurrentTheme.kt   # Tema do aplicativo
└── MainActivity.kt             # Tela principal e interface de exibição
```

## Tecnologias Usadas

- **Kotlin**: Linguagem principal do projeto.
- **Jetpack Compose**: Para a construção da interface.
- **Retrofit**: Para chamadas de rede e consumo da API REST.
- **Coil**: Para carregamento de imagens.

## Como Executar

1. Conecte um dispositivo físico ou um emulador Android.
2. No Android Studio, clique em "Run" para instalar e iniciar o aplicativo.

## Prévia da Interface

O aplicativo exibe uma lista de criptomoedas com nome, preço e variação de 24 horas. Para cada criptomoeda, é exibida uma imagem carregada do CoinMarketCap.

## Melhorias Futuras

- Adicionar recursos de pesquisa e filtragem de criptomoedas.
- Exibir gráficos de preços históricos.
- Adicionar atualizações em tempo real usando WebSocket.

## Licença

Este projeto é de código aberto e está licenciado sob a [MIT License](LICENSE).
