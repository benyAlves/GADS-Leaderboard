package mz.co.projectx.gadsleaderboard.data

import mz.co.projectx.gadsleaderboard.model.LearnLeader
import java.lang.Exception

class DataRepository(private val retrofitClient: RetrofitClient): DataService{

    override suspend fun getLearningLeaders(): List<LearnLeader> {
        return  retrofitClient.getLearningLeaders()
    }

}