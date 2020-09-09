package mz.co.projectx.gadsleaderboard.data

import mz.co.projectx.gadsleaderboard.model.LearnLeader
import mz.co.projectx.gadsleaderboard.model.SkillLeader

interface DataService {
    suspend fun getLearningLeaders(): List<LearnLeader>
    suspend fun getSkillLeaders(): List<SkillLeader>
}