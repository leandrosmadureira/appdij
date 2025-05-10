package br.com.dij.v02.ui.remote.subsetor

import br.com.dij.v02.ui.remote.HttpRoutes
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.client.plugins.*

class SubSetorServiceImpl(
    private val clientHttp: HttpClient
) : SubSetorService {
    override suspend fun getPosts(): List<SubSetorResponse> {
        return try {
            clientHttp.get {
                url(HttpRoutes.ENDSUBSETOR)
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: RedirectResponseException) {
            //3.x.x  - Responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            //4.x.x  - Responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            //5.x.x  - Responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(subSetorRequest: SubSetorRequest): SubSetorResponse? {
        return try {
            val response =clientHttp.post{
                url(HttpRoutes.ENDSUBSETOR)
                contentType(ContentType.Application.Json)
                setBody(subSetorRequest)
            }
            response.body<SubSetorResponse>()
        }catch (e: RedirectResponseException){
            //3.x.x  - Responses
            println("Error: ${e.response.status.description}")
            null
        }catch (e: ClientRequestException){
            //4.x.x  - Responses
            println("Error: ${e.response.status.description}")
            null
        }catch (e: ServerResponseException){
            //5.x.x  - Responses
            println("Error: ${e.response.status.description}")
            null
        }catch (e: Exception){
            println("Error: ${e.message}")
            null
        }
    }
}