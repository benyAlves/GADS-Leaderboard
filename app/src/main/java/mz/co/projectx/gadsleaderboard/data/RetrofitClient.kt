package mz.co.projectx.gadsleaderboard.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import mz.co.projectx.gadsleaderboard.model.LearnLeader
import mz.co.projectx.gadsleaderboard.model.SkillLeader
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

private val service: RetrofitClient by lazy {

    val baseUrl = "https://gadsapi.herokuapp.com"

    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    retrofit.create(RetrofitClient::class.java)
}

fun getNetworkService() = service


interface RetrofitClient {

    @GET("/api/hours")
    suspend fun getLearningLeaders(): List<LearnLeader>

    @GET("/api/skilliq")
    suspend fun getSkillLeaders(): List<SkillLeader>
}