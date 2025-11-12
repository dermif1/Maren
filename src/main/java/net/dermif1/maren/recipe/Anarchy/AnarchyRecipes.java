package net.dermif1.maren.recipe.Anarchy;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record AnarchyRecipes(Ingredient ingredient, ItemStack output) implements Recipe<AnarchyRecipesInput> {

    @Override
    public boolean matches(AnarchyRecipesInput anarchyRecipesInput, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(AnarchyRecipesInput anarchyRecipesInput, HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public RecipeSerializer<? extends Recipe<AnarchyRecipesInput>> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<? extends Recipe<AnarchyRecipesInput>> getType() {
        return null;
    }

    @Override
    public PlacementInfo placementInfo() {
        return null;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
}
