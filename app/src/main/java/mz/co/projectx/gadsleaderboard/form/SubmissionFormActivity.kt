package mz.co.projectx.gadsleaderboard.form

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import coil.load
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.activity_submission_form.*
import kotlinx.android.synthetic.main.content_form.*
import kotlinx.coroutines.*
import mz.co.projectx.gadsleaderboard.R
import mz.co.projectx.gadsleaderboard.data.DataRepository
import mz.co.projectx.gadsleaderboard.data.getNetworkServiceForm
import java.util.concurrent.TimeUnit


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
            showConfirmDialog()
        }

        imageViewUpButton.setOnClickListener { finish() }
        viewModel.formSubmitted.observe(this, Observer {
            when {
                it -> {
                    Log.d(TAG, "submitted")
                    val drawable = ResourcesCompat.getDrawable(resources, R.drawable.success, null)
                    val message = "Submission Successful"
                    showResultDialog(message, drawable!!)
                }
                else -> {
                    Log.d(TAG, "not submitted")
                    val drawable = ResourcesCompat.getDrawable(resources, R.drawable.error, null)
                    val message = "Submission not Successful"
                    showResultDialog(message, drawable!!)
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                delay(TimeUnit.SECONDS.toMillis(2))
                withContext(Dispatchers.Main) {
                    finish()
                }
            }
        })
    }


    private fun showConfirmDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val confirmDialog: View = layoutInflater.inflate(R.layout.alert_dialog_confirm, null)
        builder.setView(confirmDialog)
        builder.setCancelable(false)
        val buttonConfirm = confirmDialog.findViewById<LinearLayout>(R.id.buttonConfirm)
        val buttonCancel = confirmDialog.findViewById<ImageView>(R.id.imageViewCancel)

        val dialog: AlertDialog = builder.create()

        buttonCancel.setOnClickListener { dialog.dismiss() }
        buttonConfirm.setOnClickListener {

            val firstName = textInputFirstName.text.toString()
            val lastName = textInputLastName.text.toString()
            val email = textInputEmail.text.toString()
            val link = textInputLink.text.toString()

            if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && link.isNotBlank()) {
                viewModel.saveForm(
                    textInputFirstName.text.toString(),
                    textInputLastName.text.toString(),
                    textInputEmail.text.toString(),
                    textInputLink.text.toString()
                )
            } else {
                dialog.dismiss()
                val drawable = ResourcesCompat.getDrawable(resources, R.drawable.error, null)
                val message = "Submission not Successful"
                showResultDialog(message, drawable!!)
            }
        }

        dialog.show()
    }

    private fun showResultDialog(message: String, drawable: Drawable) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val submissionResult: View = layoutInflater.inflate(R.layout.submission_result, null)
        builder.setView(submissionResult)
        val imageView = submissionResult.findViewById<ImageView>(R.id.imageViewResult)
        val textViewMessage = submissionResult.findViewById<MaterialTextView>(R.id.textViewMessage)


        textViewMessage.text = message
        imageView.load(drawable)

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }

    companion object {
        private const val TAG = "SubmissionFormActivity"
    }
}