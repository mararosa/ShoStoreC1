package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoelist.viewmodel.ShoeListViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupInputChangeListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.stateShowErrorMessage.observe(viewLifecycleOwner, Observer { isInputEmpty ->
            if (isInputEmpty) {
             //Do something fix the logic
            }
        })
    }

    private fun setupInputChangeListeners() {
        val shoe = Shoe("", 0.0, "", "")
        binding.nameEditText.addTextChangedListener {
            shoe.name = it.toString()
        }
        binding.companyEditText.addTextChangedListener {
            shoe.company = it.toString()
        }
        binding.descriptionEditText.addTextChangedListener {
            shoe.description = it.toString()
        }
        viewModel.onUserChangedInput(shoe)
    }

    private fun setupClickListeners() {
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
        binding.saveButton.setOnClickListener {
            viewModel.onClickSaveDetail()
        }
    }

}