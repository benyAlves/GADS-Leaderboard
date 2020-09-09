package mz.co.projectx.gadsleaderboard.skillleaders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mz.co.projectx.gadsleaderboard.R
import mz.co.projectx.gadsleaderboard.model.SkillLeader


class SkillLeadersAdapter(private val leaders: List<SkillLeader>) :
    RecyclerView.Adapter<SkillLeadersAdapter.ViewHolder>() {


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
        val skillLeader = leaders[position]
        holder.textViewHours?.text = "${skillLeader.score} Skill IQ Score, ${skillLeader.country}"
        holder.textViewName?.text = skillLeader.name
        holder.imageViewAvatar?.load(skillLeader.badgeUrl) {
            crossfade(true)
        }
    }
}