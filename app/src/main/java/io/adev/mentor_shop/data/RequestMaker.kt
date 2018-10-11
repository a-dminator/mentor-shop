package io.adev.mentor_shop.data

import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.HttpURLConnection
import java.net.URL

interface RequestMaker {
    fun make(url: String): String
}

class OkHttpRequestMaker : RequestMaker {

    private val client = OkHttpClient()

    override fun make(url: String) = run {

        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request)
            .execute()

        response.body()!!.string()
    }

}

class DefaultRequestMaker : RequestMaker {

    override fun make(url: String) =
        (URL(url).openConnection() as HttpURLConnection).inputStream.reader().readText()

}