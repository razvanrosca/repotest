package com.example.gitapp.services

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ServiceFactory {

    var authToken: String? = null
    private const val BASE_URL = "https://api.github.com/"

    fun <T> createRetrofitService(clazz: Class<T>, username: String = "", password: String = ""): T {

        initAuth(username, password)

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(httpLoggingInterceptor())

        httpClient.addInterceptor(AuthenticationInterceptor)

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()

        return retrofit.create(clazz)
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()


        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    object AuthenticationInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()

            val builder = original.newBuilder()
                .header("Authorization", authToken)

            val request = builder.build()
            return chain.proceed(request)
        }
    }

    private fun initAuth(username: String, password: String) {
        if (!username.isEmpty() && !password.isEmpty()) {
            authToken = Credentials.basic(username, password)
        }
    }

}