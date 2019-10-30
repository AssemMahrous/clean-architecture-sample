package net.mobiquity.core.platform


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import net.mobiquity.R
import net.mobiquity.core.utils.EventObserver
import net.mobiquity.core.utils.Status
import net.mobiquity.core.utils.showAlert
import javax.inject.Inject

abstract class BaseActivity<MBaseViewModel : BaseViewModel>
    : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var viewModel: MBaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val errorMessage: String =
            if (error.error != null && error.error.isNotEmpty()) error.error
            else if (error.errorRes != null) getString(error.errorRes)
            else getString(R.string.generic_error)
        showAlert(errorMessage, R.color.colorAccent)
    }

    open fun hideLoading() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_circular)
        if (progressBar != null)
            progressBar.visibility = View.GONE
    }

    open fun showLoading() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_circular)
        if (progressBar != null)
            progressBar.visibility = View.VISIBLE
    }

    abstract fun getBaseViewModel(): MBaseViewModel

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
