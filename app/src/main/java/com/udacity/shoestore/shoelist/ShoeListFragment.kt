package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ViewShoeItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoelist.viewmodel.ShoeListViewModel

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeListViewModel
    private lateinit var shoeItemBinding: ViewShoeItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)

        setHasOptionsMenu(true)

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
        viewModel.stateEmptyFeedback.observe(viewLifecycleOwner, Observer { isEmpty ->
            if (isEmpty) setupEmptyView()
        })

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { newList ->
            if (newList.isNotEmpty()) setupList(newList)
        })
    }

    private fun setupEmptyView() {
        binding.emptyListFeedback.isVisible
        binding.emptyListFeedback.text =
            "You do not have any shoe in your list yet. Click in the add button for add a item"
    }

    private fun setupList(newList: List<Shoe>) {
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
