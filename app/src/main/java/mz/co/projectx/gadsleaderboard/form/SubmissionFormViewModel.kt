package mz.co.projectx.gadsleaderboard.form

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import mz.co.projectx.gadsleaderboard.data.DataRepository

class SubmissionFormViewModel(private val repository: DataRepository) : ViewModel() {

    private val _formSubmitted = MutableLiveData<Boolean>()
    val formSubmitted: LiveData<Boolean> = _formSubmitted

    fun saveForm(
        firstName: String,
        lastName: String,
        email: String,
        link: String
    ) {
        viewModelScope.launch {
            try {
                repository.submitForm(firstName, lastName, email, link)
                _formSubmitted.value = true
            } catch (exception: Exception) {
                _formSubmitted.value = false
                Log.e(TAG, exception.message, exception)
            }
        }

    }

    @Suppress("UNCHECKED_CAST")
    class SubmissionFormViewModelFactory(
        private val repository: DataRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            (SubmissionFormViewModel(
                repository
            ) as T)
    }

    companion object {
        private const val TAG = "LearningLeadersViewMode"
    }

}
