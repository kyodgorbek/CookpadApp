package com.example.cookpadapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookpadapp.com.example.cookpadapp.utils.parseDate
import com.example.cookpadapp.com.example.cookpadapp.utils.userFormat
import com.example.cookpadapp.databinding.ItemRecipeBinding
import com.example.cookpadapp.model.Recipe


class RecipeAdapter(
    private var recipes: MutableList<Recipe>,
    private val onClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun getItemCount(): Int = recipes.size

    // update you data in recycler
    fun update(recipes: MutableList<Recipe>) {
        this.recipes = recipes.toMutableList()

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        val itemBinding =
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    inner class ViewHolder(private val itemBinding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(recipe: Recipe) {
            itemBinding.recipeTitle.text = recipe.title
            itemBinding.recipeStory.text = recipe.story
            recipe.publishedAt.parseDate()?.let {
                itemBinding.recipeDate.text = it.userFormat()
            }
            itemBinding.recipe = recipe
            itemView.setOnClickListener { onClick(recipe) }
        }
    }

}