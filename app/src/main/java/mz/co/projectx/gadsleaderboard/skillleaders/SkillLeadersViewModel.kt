package mz.co.projectx.gadsleaderboard.skillleaders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import mz.co.projectx.gadsleaderboard.data.DataRepository
import mz.co.projectx.gadsleaderboard.data.Result

class SkillLeadersViewModel(private val repository: DataRepository) : ViewModel() {


    val learners = liveData {

        emit(Result.Loading)

        try {
            val result = repository.getSkillLeaders()
            emit(Result.Success(result))
        } catch (exception: Exception) {
            Log.e(TAG, exception.message, exception)
            emit(Result.Error(exception))
        }
    }

    @Suppress("UNCHECKED_CAST")
    class SkillLeadersViewModelFactory(
        private val repository: DataRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            (SkillLeadersViewModel(
                repository
            ) as T)
    }

    companion object {
        private const val TAG = "SkillLeadersViewModel"
    }
}