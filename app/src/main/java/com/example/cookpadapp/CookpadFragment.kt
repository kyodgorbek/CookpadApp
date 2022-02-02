package com.example.cookpadapp


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.cookpadapp.databinding.FragmentCookpadBinding
import com.example.cookpadapp.ui.BaseFragment
import com.example.cookpadapp.ui.adapter.RecipeAdapter
import com.example.cookpadapp.viewmodel.CookpadViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CookpadFragment : BaseFragment<FragmentCookpadBinding>() {

    override val layoutId: Int = R.layout.fragment_cookpad

    private val viewModel: CookpadViewModel by viewModel()

    private val adapter = RecipeAdapter(arrayListOf()) { recipe ->
        findNavController().navigate(
            CookpadFragmentDirections.cookpadToRecipeDetail(recipe.id)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.recipeRV.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getRecipes()
        }
    }

    private fun initObservers() {
        viewModel.cookpad.observe(viewLifecycleOwner) { recipes ->
            adapter.update(recipes)
        }
        viewModel.progress.observe(viewLifecycleOwner) { loading ->
            binding.swipeRefresh.isRefreshing = loading
        }
    }
}