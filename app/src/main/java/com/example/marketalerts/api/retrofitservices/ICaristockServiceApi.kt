package com.example.marketalerts.api.retrofitservices

import com.example.marketalerts.api.retrofitmodel.InstrumentQuote
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ICaristockServiceApi {

    @GET("v1/yf/ticker?symbol=AAPL")
    fun getContentRecommend(
        @Query("symbol ") symbol : String
    ): Observable<InstrumentQuote>



    companion object {
        fun create(): ICaristockServiceApi {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val builder = Retrofit.Builder()
                .baseUrl("https://caristocks-repository-api-faavf.ondigitalocean.app/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())


            val client =
                OkHttpClient.Builder().addInterceptor { chain ->
                    val request = chain.request()
                    val newReq = request.newBuilder()
                        .build()
                    chain.proceed(newReq)
                }.addInterceptor(logging).build()


            builder.client(client)
            return builder.build()
                .create<ICaristockServiceApi>(ICaristockServiceApi::class.java)
        }
    }
}