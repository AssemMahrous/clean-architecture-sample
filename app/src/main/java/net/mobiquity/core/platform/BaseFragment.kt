package net.mobiquity.core.platform

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import net.mobiquity.R
import net.mobiquity.core.di.Injectable
import net.mobiquity.core.utils.EventObserver
import net.mobiquity.core.utils.Status
import net.mobiquity.core.utils.showAlert

abstract class BaseFragment<MBaseViewModel : BaseViewModel>
    : Fragment(), Injectable {

    private lateinit var viewModel: MBaseViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onResume() {
        super.onResume()
        viewModel = getBaseViewModel()
        viewModelFactory = getBaseViewModelFactory()
        viewModel.status.observe(this, EventObserver {
            when (it) {
                is Status.Error -> {
                    hideLoading()
                    showError(it)
                }
                is Status.Loading -> {
                    if (it.show) showLoading()
                    else hideLoading()
                }
            }
        })
    }

    open fun showError(error: Status.Error) {
        val errorMessage: String =
            if (error.error != null && error.error.isNotEmpty()) error.error
            else if (error.errorRes != null) getString(error.errorRes)
            else getString(R.string.generic_error)
        activity!!.showAlert(errorMessage, R.color.colorAccent)
    }

    open fun showSuccess(message: String) {
        activity!!.showAlert(message, R.color.success_message_color)
    }

    abstract fun getBaseViewModel(): MBaseViewModel

    abstract fun getBaseViewModelFactory(): ViewModelFactory

    open fun hideLoading() {
        if (activity != null) {
            val progressBar = activity!!.findViewById<ProgressBar>(R.id.progress_circular)
            if (progressBar != null)
                progressBar.visibility = View.GONE
        }
    }

    open fun showLoading() {
        if (activity != null) {
            val progressBar = activity!!.findViewById<ProgressBar>(R.id.progress_circular)
            if (progressBar != null)
                progressBar.visibility = View.VISIBLE
        }
    }
}