package mz.co.projectx.gadsleaderboard.data

import androidx.lifecycle.LiveData
import mz.co.projectx.gadsleaderboard.model.LearnLeader

interface DataService {
    suspend fun getLearningLeaders(): List<LearnLeader>
}