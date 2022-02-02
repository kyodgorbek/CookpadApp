package com.example.cookpadapp

import android.os.Bundle
import android.view.View
import com.example.cookpadapp.com.example.cookpadapp.utils.parseDate
import com.example.cookpadapp.com.example.cookpadapp.utils.userFormat
import com.example.cookpadapp.databinding.FragmentDetailBinding
import com.example.cookpadapp.model.RecipeDetailsResponse
import com.example.cookpadapp.ui.BaseFragment
import com.example.cookpadapp.viewmodel.DetailCookpadViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val layoutId: Int = R.layout.fragment_detail

    private val viewModel: DetailCookpadViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let { DetailFragmentArgs.fromBundle(it) }
        viewModel.initData(args?.recipeId)
        initObservers()
    }

    private fun initObservers(){
        viewModel.recipeDetails.observe(viewLifecycleOwner){ recipeDetail ->
            binding.username.text = recipeDetail.user.name
            binding.ingredients.text = recipeDetail.ingredients.toString()
            binding.steps.text = recipeDetail.steps.toString()
            recipeDetail.publishedAt.parseDate()?.let {
                binding.publishedAt.text = it.userFormat()
            }
           binding.title.text = recipeDetail.title

        }
    }
}