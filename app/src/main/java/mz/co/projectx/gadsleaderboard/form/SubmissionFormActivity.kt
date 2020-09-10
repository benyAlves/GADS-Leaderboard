package mz.co.projectx.gadsleaderboard.form

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.content_form.*
import mz.co.projectx.gadsleaderboard.R
import mz.co.projectx.gadsleaderboard.data.DataRepository
import mz.co.projectx.gadsleaderboard.data.getNetworkServiceForm

class SubmissionFormActivity : AppCompatActivity() {

    private val viewModel by viewModels<SubmissionFormViewModel> {
        SubmissionFormViewModel.SubmissionFormViewModelFactory(
            DataRepository(getNetworkServiceForm())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission_form)

        buttonSubmitForm.setOnClickListener {
            viewModel.saveForm(
                textInputFirstName.text.toString(),
                textInputLastName.text.toString(),
                textInputEmail.text.toString(),
                textInputLink.text.toString()
            )
        }

        viewModel.formSubmitted.observe(this, Observer {
            when {
                it -> {
                    Log.d(TAG, "submitted")
                }
                else -> {
                    Log.d(TAG, "not submitted")
                }
            }
        })
    }

    companion object {
        private const val TAG = "SubmissionFormActivity"
    }
}