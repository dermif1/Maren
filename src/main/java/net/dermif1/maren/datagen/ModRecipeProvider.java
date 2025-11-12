package net.dermif1.maren.datagen;

import net.dermif1.maren.block.ModBlocks;
import net.dermif1.maren.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Maren Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.MISC, ModBlocks.ANARCHY.get())
                .pattern("SSS")
                .pattern("FLF")
                .pattern(" S ")
                .define('S', Blocks.STONE_BRICKS)
                .define('L', ModBlocks.LUGTER_PLANKS)
                .define('F', ModItems.SOUL_FRAGMENT)
                .unlockedBy("has_lugter_planks", has(ModBlocks.LUGTER_PLANKS)).save(output);

        /*shapeless(RecipeCategory.MISC, ModBlocks.LUGTER_PLANKS.get(), 4)
                .requires(ModBlocks.LUGTER_LOG)
                .unlockedBy("has_lugter_log", has(ModBlocks.LUGTER_LOG))
                .save(output, "maren:lugter_planks_from_lugter_log");
        shapeless(RecipeCategory.MISC, ModBlocks.LUGTER_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_LUGTER_LOG)
                .unlockedBy("has_stripped_lugter_log", has(ModBlocks.STRIPPED_LUGTER_LOG))
                .save(output, "maren:lugter_planks_from_stripped_lugter_log");*/

        stairBuilder(ModBlocks.LUGTER_STAIRS.get(), Ingredient.of(ModBlocks.LUGTER_PLANKS)).group("lugter")
                .unlockedBy("has_lugter_planks", has(ModBlocks.LUGTER_PLANKS)).save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUGTER_SLAB.get(), ModBlocks.LUGTER_PLANKS);

        buttonBuilder(ModBlocks.LUGTER_BUTTON.get(), Ingredient.of(ModBlocks.LUGTER_PLANKS)).group("lugter")
                .unlockedBy("has_lugter_planks", has(ModBlocks.LUGTER_PLANKS)).save(output);
        pressurePlate(ModBlocks.LUGTER_PRESSURE_PLATE.get(), ModBlocks.LUGTER_PLANKS);

        fenceBuilder(ModBlocks.LUGTER_FENCE.get(), Ingredient.of(ModBlocks.LUGTER_PLANKS)).group("lugter")
                .unlockedBy("has_lugter_planks", has(ModBlocks.LUGTER_PLANKS)).save(output);
        fenceGateBuilder(ModBlocks.LUGTER_FENCE_GATE.get(), Ingredient.of(ModBlocks.LUGTER_PLANKS)).group("lugter")
                .unlockedBy("has_lugter_planks", has(ModBlocks.LUGTER_PLANKS)).save(output);

        doorBuilder(ModBlocks.LUGTER_DOOR.get(), Ingredient.of(ModBlocks.LUGTER_PLANKS)).group("lugter")
                .unlockedBy("has_lugter_planks", has(ModBlocks.LUGTER_PLANKS)).save(output);
        trapdoorBuilder(ModBlocks.LUGTER_TRAPDOOR.get(), Ingredient.of(ModBlocks.LUGTER_PLANKS)).group("lugter")
                .unlockedBy("has_lugter_planks", has(ModBlocks.LUGTER_PLANKS)).save(output);
    }
}
