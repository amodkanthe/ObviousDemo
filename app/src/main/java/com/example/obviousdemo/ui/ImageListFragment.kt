package com.example.obviousdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.obviousdemo.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.obviousdemo.api.Status
import com.example.obviousdemo.databinding.FragmentImageListBinding
import com.example.obviousdemo.viewmodel.ImagesViewModel

@AndroidEntryPoint
class ImageListFragment : Fragment() {

    val imagesViewModel: ImagesViewModel by activityViewModels()
    lateinit var binding: FragmentImageListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvImages.layoutManager = GridLayoutManager(context, 2);
        imagesViewModel.getImagesData()
        setObserver()
    }

    fun setObserver() {
        imagesViewModel._listImagesResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.ERROR -> {

                }
                Status.SUCCESS -> {
                    val imageListAdapter =
                        ImageListAdapter(it?.data?.response)
                    imageListAdapter.onItemClick = { index ->
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.add(R.id.fragmentContainer, ImageDetailsFragment.newInstance(index))
                            ?.addToBackStack("Details")
                            ?.commit()
                    }
                    binding.rvImages.adapter = imageListAdapter
                }
                Status.LOADING -> {

                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ImageListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}