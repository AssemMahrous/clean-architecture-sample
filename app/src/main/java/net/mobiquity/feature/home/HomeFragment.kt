package net.mobiquity.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.home_fragment.*
import net.mobiquity.R
import net.mobiquity.core.platform.BaseFragment
import net.mobiquity.core.utils.EventObserver
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
        viewModel.categories.observe(this, EventObserver {
            initView(it)
        })

        viewModel.products.observe(this, EventObserver {
            updateList(it)
        })

        no_internet_connection.setOnClickListener {
            it.visibility = View.GONE
            getCategories()
        }

        getCategories()
    }

    private fun getCategories() {
        viewModel.getCategories()
    }

    private val adapter = ProductsAdapter { product: ProductView, imageView: ImageView ->
        val extras = FragmentNavigatorExtras(
            imageView to "imageView"
        )
        val bundle =
            bundleOf("name" to product.name, "price" to product.price, "image" to product.imageUrl)
        findNavController().navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundle,
            null,
            extras
        )
    }

    private fun updateList(list: List<ProductView>) {
        adapter.submitList(list)
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
        recycler_view_products.adapter = adapter
    }
}
