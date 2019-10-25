package com.ntconcepts.gcpdemo2.predict.utils.mlengine

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.InputStreamContent
import com.google.api.client.http.UriTemplate
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.discovery.Discovery
import com.google.api.services.discovery.model.JsonSchema
import com.google.api.services.discovery.model.RestMethod
import java.io.ByteArrayInputStream


object PredictionRequest {

    fun predict(projectId: String, modelId: String, versionId: String, content: String) {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val jsonFactory = JacksonFactory.getDefaultInstance()
        val discovery =
            Discovery.Builder(httpTransport, jsonFactory, null).setApplicationName("gcpdemo2predict").build()

        val api = discovery.apis().getRest("ml", "v1").execute()
        val methods = api.resources["projects"]?.getMethods()
        val method: RestMethod?
        if (methods != null) {
            method = methods["predict"]


            val param = JsonSchema()
            param.set(
                "name", String.format("projects/%s/models/%s/versions/%s", projectId, modelId, versionId)
            )

            val url = GenericUrl(UriTemplate.expand(api.baseUrl + method?.getPath(), param, true))
            println(url)

            val contentType = "application/json"

            val stream = InputStreamContent(contentType, ByteArrayInputStream(content.toByteArray(Charsets.UTF_8)))

            val credential = GoogleCredential.getApplicationDefault()
            val requestFactory = httpTransport.createRequestFactory(credential)
            val request = requestFactory.buildRequest(method?.getHttpMethod(), url, stream)

            val response = request.execute().parseAsString()
            println(response)
        }

    }
}