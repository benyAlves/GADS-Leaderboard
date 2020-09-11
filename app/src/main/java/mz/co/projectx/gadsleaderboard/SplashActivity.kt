package mz.co.projectx.gadsleaderboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

}