package mz.co.projectx.gadsleaderboard.data

import mz.co.projectx.gadsleaderboard.model.LearnLeader
import mz.co.projectx.gadsleaderboard.model.SkillLeader

class DataRepository(private val retrofitClient: RetrofitClient): DataService {

    override suspend fun getLearningLeaders(): List<LearnLeader> {
        return retrofitClient.getLearningLeaders()
    }

    override suspend fun getSkillLeaders(): List<SkillLeader> {
        return retrofitClient.getSkillLeaders()
    }

    override suspend fun submitForm(
        firstName: String,
        lastName: String,
        email: String,
        link: String
    ) {
        retrofitClient.submitForm(firstName, lastName, email, link)
    }

}