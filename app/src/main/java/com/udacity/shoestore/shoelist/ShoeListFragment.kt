package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ViewShoeItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.sharedviewmodel.SharedShoeViewModel

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: SharedShoeViewModel by activityViewModels()
    private lateinit var shoeItemBinding: ViewShoeItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.vm = viewModel

        setHasOptionsMenu(true)
        viewModel.fetchShoeList()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserves()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shoeListFragment -> {
                val navController = Navigation.findNavController(
                    requireActivity(),
                    R.id.nav_host_fragment_container
                )
                navController.navigate(R.id.action_shoeListFragment_to_loginFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupObserves() {

        viewModel.eventShowDetailScreen.observe(viewLifecycleOwner, Observer { hasAddBtnClicked ->
            if (hasAddBtnClicked) {
                showDetailScreen()
                viewModel.onClickShoeDetailComplete()
            }
        })

        viewModel.shoeListVO.observe(viewLifecycleOwner, Observer { newList ->
            if (newList.isNotEmpty()) {
                setupListView(newList)
            } else {
                setupEmptyView()
            }
        })
    }

    private fun showDetailScreen() {
        findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
    }

    private fun setupEmptyView() {
        binding.emptyListFeedback.isVisible
        binding.emptyListFeedback.text = getString(R.string.shoe_list_empty_text)
    }

    private fun setupListView(newList: List<Shoe>) {
        binding.emptyListFeedback.isGone
        addShoeItemView(newList)
    }

    private fun addShoeItemView(list: List<Shoe>) {
        val linearLayoutContainer = binding.shoeListContainer

        list.forEach { shoe ->
            shoeItemBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.view_shoe_item,
                linearLayoutContainer,
                false
            )

            shoeItemBinding.name.text = shoe.name
            shoeItemBinding.size.text = shoe.size.toString()
            shoeItemBinding.company.text = shoe.company
            shoeItemBinding.description.text = shoe.description

            linearLayoutContainer.addView(shoeItemBinding.root)
        }

    }

}
