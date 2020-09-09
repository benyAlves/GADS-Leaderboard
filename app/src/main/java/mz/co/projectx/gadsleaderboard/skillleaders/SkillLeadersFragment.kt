package mz.co.projectx.gadsleaderboard.skillleaders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mz.co.projectx.gadsleaderboard.R
import mz.co.projectx.gadsleaderboard.data.DataRepository
import mz.co.projectx.gadsleaderboard.data.Result
import mz.co.projectx.gadsleaderboard.data.getNetworkService
import mz.co.projectx.gadsleaderboard.model.SkillLeader

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SkillLeadersFragment : Fragment() {

    private lateinit var recyclerViewLearningLeaders: RecyclerView
    private lateinit var textViewError: TextView
    private lateinit var progressCircular: ProgressBar

    private val viewModel by viewModels<SkillLeadersViewModel> {
        SkillLeadersViewModel.SkillLeadersViewModelFactory(
            DataRepository(getNetworkService())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_skill_leaders, container, false)

        textViewError = view.findViewById(R.id.textViewError)
        progressCircular = view.findViewById(R.id.progressCircular)
        recyclerViewLearningLeaders = view.findViewById(R.id.recyclerViewLearningLeaders)
        recyclerViewLearningLeaders.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        return view
    }

    private fun showLoader() {
        recyclerViewLearningLeaders.visibility = View.GONE
        progressCircular.visibility = View.VISIBLE
        textViewError.visibility = View.GONE
    }

    private fun showData(data: List<SkillLeader>) {
        progressCircular.visibility = View.GONE
        textViewError.visibility = View.GONE
        recyclerViewLearningLeaders.visibility = View.VISIBLE
        recyclerViewLearningLeaders.adapter = SkillLeadersAdapter(data)
    }

    private fun showError() {
        recyclerViewLearningLeaders.visibility = View.GONE
        progressCircular.visibility = View.GONE
        textViewError.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.learners.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    showLoader()
                }
                is Result.Success -> {
                    showData(it.data)
                }
                is Result.Error -> {
                    showError()
                }
            }

        })
    }
}