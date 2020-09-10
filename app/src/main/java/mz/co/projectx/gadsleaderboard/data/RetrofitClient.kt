package mz.co.projectx.gadsleaderboard.data

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import mz.co.projectx.gadsleaderboard.model.LearnLeader
import mz.co.projectx.gadsleaderboard.model.SkillLeader
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

private val serviceList: RetrofitClient by lazy {
    val baseUrl = "https://gadsapi.herokuapp.com"
    getClient(baseUrl).create(RetrofitClient::class.java)

}

private val serviceForm: RetrofitClient by lazy {
    val baseUrl = "https://docs.google.com/forms/d/e/"
    getClient(baseUrl).create(RetrofitClient::class.java)
}

private fun getClient(baseUrl: String): Retrofit {

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun getNetworkService() = serviceList

fun getNetworkServiceForm() = serviceForm


interface RetrofitClient {

    @GET("/api/hours")
    suspend fun getLearningLeaders(): List<LearnLeader>

    @GET("/api/skilliq")
    suspend fun getSkillLeaders(): List<SkillLeader>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    suspend fun submitForm(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") email: String,
        @Field("entry.284483984") link: String
    )
}