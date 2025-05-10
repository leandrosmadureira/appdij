package br.com.dij.v02.ui.remote.subsetor

import br.com.dij.v02.ui.remote.subsetor.SubSetorResponse
import br.com.dij.v02.ui.remote.subsetor.SubSetorServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface SubSetorService {
    suspend fun getPosts() : List<SubSetorResponse>

    suspend fun createPost(subSetorRequest: SubSetorRequest) : SubSetorResponse?

    companion object {
        fun create(): SubSetorService {
            return SubSetorServiceImpl(
                clientHttp = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                        })
                    }
                },
            )
        }
    }
}