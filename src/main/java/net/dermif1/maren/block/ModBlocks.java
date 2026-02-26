package net.dermif1.maren.block;

import net.dermif1.maren.Maren;
import net.dermif1.maren.block.custom.AnarchyBlock;
import net.dermif1.maren.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Maren.MOD_ID);

    public static final DeferredBlock<Block> LUGTER_PLANKS = registerBlock("lugter_planks",
            (properties) -> new Block(properties) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    /*public static final DeferredBlock<Block> LUGTER_LOG = registerBlock("lugter_log",
            (properties) -> new Block(properties
                    .strength(2f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> STRIPPED_LUGTER_LOG = registerBlock("stripped_lugter_log",
            (properties) -> new Block(properties
                    .strength(2f).sound(SoundType.WOOD)));*/

    public static final DeferredBlock<StairBlock> LUGTER_STAIRS = registerBlock("lugter_stairs",
            (properties) -> new StairBlock(ModBlocks.LUGTER_PLANKS.get().defaultBlockState(),
                    properties.strength(2f)));

    public static final DeferredBlock<SlabBlock> LUGTER_SLAB = registerBlock("lugter_slab",
            (properties) -> new SlabBlock(properties.strength(2f)));

    public static final DeferredBlock<PressurePlateBlock> LUGTER_PRESSURE_PLATE = registerBlock("lugter_pressure_plate",
            (properties) -> new PressurePlateBlock(BlockSetType.OAK,properties.strength(2f)));
    public static final DeferredBlock<ButtonBlock> LUGTER_BUTTON = registerBlock("lugter_button",
            (properties) -> new ButtonBlock(BlockSetType.OAK, 30,properties.strength(2f).noCollision()));

    public static final DeferredBlock<FenceBlock> LUGTER_FENCE = registerBlock("lugter_fence",
            (properties) -> new FenceBlock(properties.strength(2f)));
    public static final DeferredBlock<FenceGateBlock> LUGTER_FENCE_GATE = registerBlock("lugter_fence_gate",
            (properties) -> new FenceGateBlock(WoodType.OAK, properties.strength(2f)));

    public static final DeferredBlock<DoorBlock> LUGTER_DOOR = registerBlock("lugter_door",
            (properties) -> new DoorBlock(BlockSetType.OAK,properties.strength(2f).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> LUGTER_TRAPDOOR = registerBlock("lugter_trapdoor",
            (properties) -> new TrapDoorBlock(BlockSetType.OAK, properties.strength(2f).noOcclusion()));

    public static final DeferredBlock<Block> ANARCHY = registerBlock("anarchy",
            (properties) -> new AnarchyBlock(properties.noOcclusion()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE_BRICKS)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
