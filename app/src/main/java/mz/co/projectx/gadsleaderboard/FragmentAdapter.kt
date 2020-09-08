package mz.co.projectx.gadsleaderboard

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import mz.co.projectx.gadsleaderboard.learningleaders.LearningLeadersFragment

class FragmentAdapter(val fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
       return 2
    }



    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> LearningLeadersFragment()
            else -> SecondFragment()
        }
    }


}