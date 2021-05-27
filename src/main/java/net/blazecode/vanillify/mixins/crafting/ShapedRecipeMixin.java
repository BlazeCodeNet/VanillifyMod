package net.blazecode.vanillify.mixins.crafting;

import net.blazecode.vanillify.api.interfaces.ItemStackProxy;
import net.blazecode.vanillify.api.interfaces.RecipeProviderProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin( ShapedRecipe.class )
public abstract class ShapedRecipeMixin implements CraftingRecipe, RecipeProviderProxy<ShapedRecipe>
{
    @Shadow
    @Final
    int width;
    
    @Shadow
    @Final
    int height;
    
    @Shadow
    @Final
    DefaultedList<Ingredient> input;
    
    @Shadow
    @Final
    ItemStack output;
    
    @Shadow
    @Final
    private Identifier id;
    
    @Shadow
    @Final
    String group;
    
    
    @Override
    public ShapedRecipe getRecipeProxy( )
    {
        ItemStack out = this.output;
        Item outputItem = out.getItem();
        
        if( outputItem instanceof ItemStackProxy )
        {
            out = ((ItemStackProxy)outputItem).getClientItemStack( out );
        }
        
        return new ShapedRecipe( id, group, width, height, input, out );
    }
}
