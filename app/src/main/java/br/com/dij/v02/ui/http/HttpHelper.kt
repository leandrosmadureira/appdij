package br.com.dij.v02.ui.http

import br.com.dij.v02.ui.remote.subsetor.SubSetorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.emptyFlow

class HttpHelper {
    suspend fun getSubSetor(url: String,cod: String?): Flow<MutableList<SubSetorResponse>> {
        val client = HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
            install(HttpCache)
        }
        try {
            println("$url/api/listarsubsetor")
            var endpont = "$url/api/listarsubsetor"
            if(cod != null){
                endpont = "$url/api/listarsubsetor?cd_sub_setor=$cod"
            }
            val subsetors =
                client.get(endpont).body<MutableList<SubSetorResponse>>()
            return MutableStateFlow(subsetors)
        } catch (e: RedirectResponseException) {
            //3.x.x  - Responses
            println("Error: ${e.response.status.description}")
            //null
        } catch (e: ClientRequestException) {
            //4.x.x  - Responses
            println("Error: ${e.response.status.description}")
            //null
        } catch (e: ServerResponseException) {
            //5.x.x  - Responses
            println("Error: ${e.response.status.description}")
            //null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            //null
        }
        return MutableStateFlow(client.get("$url").body<MutableList<SubSetorResponse>>())
    }
}