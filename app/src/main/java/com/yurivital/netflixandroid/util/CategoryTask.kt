package com.yurivital.netflixandroid.util

import android.util.Log
import java.io.IOException
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask {

    fun execute(url: String) {
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
           try {
               val requestUrl = URL(url)
               val urlConnection = requestUrl.openConnection() as HttpsURLConnection
               urlConnection.readTimeout = 2000
               urlConnection.connectTimeout = 2000

               val statusCode: Int = urlConnection.responseCode
               if(statusCode > 400) {
                    throw IOException("Erro na comunicação com o servidor!")
               }

               val stream = urlConnection.inputStream
               val jsonAsString: String = stream.bufferedReader().use { it.readText() }
               Log.i("teste", jsonAsString)

           } catch (e: IOException) {
                Log.e("teste", e.message ?: "Erro desconhecido", e)
           }
        }
    }
}