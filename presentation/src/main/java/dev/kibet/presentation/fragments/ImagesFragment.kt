package dev.kibet.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.kibet.domain.utils.Status
import dev.kibet.presentation.adapter.ImagesAdapter
import dev.kibet.presentation.databinding.FragmentImagesBinding
import dev.kibet.presentation.viewmodels.ImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagesFragment : Fragment() {
    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImagesViewModel by viewModel()
    private val imagesAdapter = ImagesAdapter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImagesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        recyclerView = binding.imagesRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = imagesAdapter

        imagesAdapter.setOnItemClickListener {
            findNavController().navigate(
                ImagesFragmentDirections.actionImagesFragmentToImageDetailsFragment(it.id)
            )
        }
        viewModel.getImages("dog")
    }

    private fun subscribeToObservers() {
        viewModel.imageStatus.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.let {
                        imagesAdapter.differ.submitList(it.data)
                        // recyclerView.adapter = imagesAdapter
                    }
                    // Toast.makeText(context, "${it.data}", Toast.LENGTH_LONG).show()
                    Log.d("IMAGES", "${it.data}")
                }
                Status.ERROR -> {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG).show()
                    Log.d("ERROR", "${it.message}")
                }
            }
        })
    }
}
