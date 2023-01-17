package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.sharedviewmodel.SharedShoeViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: SharedShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.eventSaveDetail.observe(viewLifecycleOwner, Observer { hasSaveDetailClicked ->
            if (hasSaveDetailClicked) {
                showListScreenWithANewItem()
                viewModel.onClickSubmitFormCompleted()
            }
        })
    }

    private fun showListScreenWithANewItem() {
        findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
    }


    private fun setupClickListeners() {
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
        binding.saveButton.setOnClickListener {
            val size = binding.sizeEditText.text.toString()
            viewModel.onClickSubmitForm(
                Shoe(
                    name = binding.nameEditText.text.toString(),
                    description = binding.descriptionEditText.text.toString(),
                    company = binding.companyEditText.text.toString(),
                    size = size.toDouble()
                )
            )
        }
    }

}