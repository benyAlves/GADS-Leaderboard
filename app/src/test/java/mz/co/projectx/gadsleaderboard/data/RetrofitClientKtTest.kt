package mz.co.projectx.gadsleaderboard.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class RetrofitClientKtTest {

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var mockWebServer = MockWebServer()
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        mockWebServer.start()

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        retrofitClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RetrofitClient::class.java)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        mockWebServer.shutdown()
    }

    @Test
    fun getLearnersLeadersTest() = testDispatcher.runBlockingTest {
        //TODO improve this call
        retrofitClient.getLearningLeaders()

    }
}