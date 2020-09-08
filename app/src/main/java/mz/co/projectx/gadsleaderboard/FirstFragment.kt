package mz.co.projectx.gadsleaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_first.*
import mz.co.projectx.gadsleaderboard.learningleaders.LearningLeadersFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        tabLayout = view.findViewById(R.id.tabLayout)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentAdapter = FragmentAdapter(this)
        pager.adapter = fragmentAdapter

        TabLayoutMediator(tabLayout, pager, TabLayoutMediator.TabConfigurationStrategy(){ tab: TabLayout.Tab, position: Int ->
            when(position){
                0 -> tab.text = "Learning Leaders"
                else -> tab.text = "Skill IQ Leaders"
            }
        }).attach()
    }
}