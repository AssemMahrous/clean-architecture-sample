package net.mobiquity.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.home_fragment.*
import net.mobiquity.R
import net.mobiquity.core.platform.BaseFragment
import net.mobiquity.modules.category.entities.CategoryView
import net.mobiquity.modules.product.entities.ProductView
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {
    @Inject
    lateinit var viewModel: HomeViewModel

    override fun getBaseViewModel(): HomeViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.categories.observe(this, Observer {
            initView(it)
        })

        viewModel.products.observe(this, Observer {
            updateList(it)
        })

        no_internet_connection.setOnClickListener {
            it.visibility = View.GONE
            viewModel.getCategories()
        }

        viewModel.getCategories()
    }

    override fun showLoading() {
        textViewProducts.visibility = View.GONE
        super.showLoading()
    }

    override fun hideLoading() {
        super.hideLoading()
        textViewProducts.visibility = View.VISIBLE
    }

    private fun updateList(list: List<ProductView>) {
        // todo
        textViewProducts.text = ""
        list.forEach {
            textViewProducts.text = "${textViewProducts.text}$it\n\n"
        }
    }

    private fun initView(list: List<CategoryView>) {
        list.forEach { categoryView: CategoryView ->
            val tab = tabLayoutCategories.newTab()
                .apply {
                    text = categoryView.name
                    tag = categoryView.id
                }

            tabLayoutCategories.addTab(tab)
        }
        tabLayoutCategories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                // pass
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // pass
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { viewModel.getProducts(tab.tag as String) }
            }
        })
        list.firstOrNull()?.let { viewModel.getProducts(it.id) }
    }
}
