package kr.pe.paran.library_app.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

object NetworkClient {

    fun client() = HttpClient(Android) {

        expectSuccess = true            // 2xx가 아닌 경우 예외 발생

        val TIME_OUT = 6_000

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("KtorClient", ":::>$message")
                }
            }
            level = LogLevel.ALL
        }

        install(JsonFeature) {

            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                classDiscriminator = "type"
                encodeDefaults = true
            })
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    }

    suspend inline fun <reified T : Any> post(urlString: String, data: Any, request: Int = 0): NetworkStatus {
        return client().use { client ->
            try {
                val response = client.post<T>(urlString = urlString) {
                    body = data
                }
                NetworkStatus.SUCCESS(data = response, request = request)
            } catch (e: Exception) {
                NetworkStatus.FAILURE(message = e.message ?: "", request = request)
            }
        }
    }

    suspend inline fun <reified T : Any> put(urlString: String, data: Any, request: Int = 0): NetworkStatus {
        return client().use { client ->
            try {
                val response = client.put<T>(urlString = urlString) {
                    body = data
                }
                NetworkStatus.SUCCESS(data = response, request = request)
            } catch (e: Exception) {
                NetworkStatus.FAILURE(message = e.message ?: "", request = request)
            }
        }
    }

    suspend inline fun <reified T : Any> get(urlString: String, data: Any, request: Int = 0): NetworkStatus {
        return client().use { client ->
            try {
                val response = client.get<T>(urlString = "$urlString/$data")
                NetworkStatus.SUCCESS(data = response, request = request)
            } catch (e: Exception) {
                NetworkStatus.FAILURE(message = e.message.toString(), request = request)
            }
        }
    }
}
