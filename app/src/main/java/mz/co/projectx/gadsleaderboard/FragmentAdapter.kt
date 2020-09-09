package mz.co.projectx.gadsleaderboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import mz.co.projectx.gadsleaderboard.learningleaders.LearningLeadersFragment
import mz.co.projectx.gadsleaderboard.skillleaders.SkillLeadersFragment

class FragmentAdapter(val fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LearningLeadersFragment()
            else -> SkillLeadersFragment()
        }
    }


}