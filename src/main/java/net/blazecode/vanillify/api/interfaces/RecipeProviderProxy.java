package net.blazecode.vanillify.api.interfaces;

import net.minecraft.recipe.Recipe;

public interface RecipeProviderProxy<T extends Recipe<?>>
{
    public T getRecipeProxy();
}
