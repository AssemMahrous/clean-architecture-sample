package net.mobiquity.core.platform

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import net.mobiquity.R
import net.mobiquity.core.di.Injectable
import net.mobiquity.core.utils.ErrorType
import net.mobiquity.core.utils.EventObserver
import net.mobiquity.core.utils.Status
import net.mobiquity.core.utils.showAlert

abstract class BaseFragment<MBaseViewModel : BaseViewModel> : Fragment(), Injectable {

    private val progressBar: View?
        get() = (view?.findViewById(R.id.progress_bar_loading)
            ?: activity?.findViewById(R.id.progress_bar_loading))

    private val noInternetConnection: View?
        get() = (view?.findViewById(R.id.no_internet_connection)
            ?: activity?.findViewById(R.id.no_internet_connection))

    private lateinit var viewModel: MBaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getBaseViewModel()
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
        when (error.errorType) {
            ErrorType.NetworkError -> {/*pass*/
            }
            ErrorType.NoInternetConnection -> showNoInternetConnection()
            else -> {
                val errorMessage: String =
                    if (error.error != null && error.error.isNotEmpty()) error.error
                    else if (error.errorRes != null) getString(error.errorRes)
                    else getString(R.string.generic_error)
                activity!!.showAlert(errorMessage, R.color.colorAccent)
            }
        }

    }

    open fun showNoInternetConnection() {
        // pass
        noInternetConnection?.visibility = View.VISIBLE
    }

    open fun showSuccess(message: String) {
        activity!!.showAlert(message, R.color.success_message_color)
    }

    open fun hideLoading() {
        progressBar?.visibility = View.GONE
    }

    open fun showLoading() {
        progressBar?.visibility = View.VISIBLE
    }

    abstract fun getBaseViewModel(): MBaseViewModel
}