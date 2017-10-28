package ga.nk2ishere.dev.fcm

/**
* Created by nk2 on 16-Sep-17.
*/

import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class Sender(private val serverKey: String) {
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                        .header("Authorization", String.format("key=%s", this@Sender.serverKey))
                        .header("content-type", "application/json")
                        .build()
                it.proceed(request)
            }
            .build()

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://fcm.googleapis.com/fcm/")
                                        .client(httpClient)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()

    fun send(message: Message, callback: Callback<JsonObject>) {
        retrofit.create(FCM::class.java)
                .send(message)
                .enqueue(callback)
    }

    private interface FCM {
        @POST("send") fun send(@Body to: Message): Call<JsonObject>
    }
}
