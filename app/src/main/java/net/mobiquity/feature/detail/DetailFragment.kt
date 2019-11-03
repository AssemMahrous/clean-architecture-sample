package net.mobiquity.feature.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_detail.*
import net.mobiquity.R
import net.mobiquity.core.platform.BaseFragment
import net.mobiquity.core.utils.loadImage
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : BaseFragment<DetailViewModel>() {
    @Inject
    lateinit var viewModel: DetailViewModel

    override fun getBaseViewModel(): DetailViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textView_name.text = arguments!!.getString("name")
        textView_price.text = arguments!!.getString("price")
        arguments!!.getString("image")?.let {
            loadImage(it, image_product)
        }
    }
}
