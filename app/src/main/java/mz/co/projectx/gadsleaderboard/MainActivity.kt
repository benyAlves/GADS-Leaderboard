package mz.co.projectx.gadsleaderboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_first.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = FragmentAdapter(this)
        pager.adapter = fragmentAdapter

        TabLayoutMediator(
            tabLayout,
            pager,
            TabLayoutMediator.TabConfigurationStrategy() { tab: TabLayout.Tab, position: Int ->
                when (position) {
                    0 -> tab.text = "Learning Leaders"
                    else -> tab.text = "Skill IQ Leaders"
                }
            }).attach()

    }

}