package dev.kibet.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dev.kibet.domain.utils.Status
import dev.kibet.presentation.databinding.FragmentImageDetailsBinding
import dev.kibet.presentation.viewmodels.ImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDetailsFragment : Fragment() {
    private var _binding: FragmentImageDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImagesViewModel by viewModel()
    private val args: ImageDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageId = args.id
        viewModel.getImageDetails(imageId)
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.imageDetails.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.apply {
                        Glide.with(requireContext()).load(it.data?.userImageURL)
                            .into(detailUserImage)
                        Glide.with(requireContext()).load(it.data?.webformatURL)
                            .into(detailImageView)
                        detailViews.text = "${it.data?.views} views"
                        detailLikes.text = "${it.data?.likes} likes"
                        detailComments.text = "${it.data?.comments} comments"
                        detailDownloads.text = "${it.data?.downloads} downloads"
                        detailUsername.text = it.data?.user
                        //Toast.makeText(context, "${it.data}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}
