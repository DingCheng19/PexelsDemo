package com.unifa.pexelsdemo.data.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class BasicAuthInterceptor : Interceptor {
    private var token: String = "563492ad6f91700001000001e8757ea256eb4828b63419058b1f17f8"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header("Authorization", token).build()
        return chain.proceed(authenticatedRequest)
    }
}