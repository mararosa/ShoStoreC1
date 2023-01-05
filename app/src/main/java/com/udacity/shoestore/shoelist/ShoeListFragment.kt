package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoelist.viewmodel.ShoeListViewModel

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
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
        viewModel.showList.observe(viewLifecycleOwner, Observer { newList ->
            setupView(newList)
        })
    }

    private fun setupView(newList: List<Shoe>?) {
        if (!newList.isNullOrEmpty()) {
            binding.teste.text = newList.map {
                it.name
            }.toString()
        } else {
            binding.teste.text =
                "You do not have any shoe in your list yet. Click in the add button for add a item"
        }

    }
}