package mz.co.projectx.gadsleaderboard.learningleaders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mz.co.projectx.gadsleaderboard.R
import mz.co.projectx.gadsleaderboard.model.LearnLeader


class LearningLeadersAdapter(private val leaders: List<LearnLeader>): RecyclerView.Adapter<LearningLeadersAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewName: TextView? = null
        var textViewHours: TextView? = null
        var imageViewAvatar: ImageView? = null

        init {
            textViewName = itemView.findViewById(R.id.textViewName)
            textViewHours = itemView.findViewById(R.id.textViewHours)
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_leader_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return leaders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val learnLeader = leaders[position]
        holder.textViewHours?.text = "${learnLeader.hours} learning hours, ${learnLeader.country}"
        holder.textViewName?.text = learnLeader.name
        holder.imageViewAvatar?.load(learnLeader.badgeUrl){
            crossfade(true)
        }
    }
}