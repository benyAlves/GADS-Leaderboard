package mz.co.projectx.gadsleaderboard.learningleaders

import android.util.Log
import androidx.lifecycle.*
import mz.co.projectx.gadsleaderboard.data.DataRepository
import mz.co.projectx.gadsleaderboard.data.Result

class LearningLeadersViewModel(private val repository: DataRepository) : ViewModel() {


    val learners = liveData {

        emit(Result.Loading)

        try {
            val result = repository.getLearningLeaders()
            emit(Result.Success(result))
        } catch (exception: Exception) {
            Log.e(TAG, exception.message, exception)
            emit(Result.Error(exception))
        }
   }

    @Suppress("UNCHECKED_CAST")
    class LearningLeadersViewModelFactory(
        private val repository: DataRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            (LearningLeadersViewModel(
                repository
            ) as T)
    }

    companion object {
        private const val TAG = "LearningLeadersViewMode"
    }
}