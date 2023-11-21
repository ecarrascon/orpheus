package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Orpheus.MOD_ID);
    public static final RegistryObject<Block> DEEPSLATE_TEARS_OF_HADES_ORE = BLOCKS.register("deepslate_tears_of_hades_ore",
            () -> new DropExperienceBlock(Block.Properties.copy(Blocks.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f),
                    UniformInt.of(3, 7)
            ));
    public static final RegistryObject<Block> EPIPHANY_TABLE = BLOCKS.register("epiphany_table",
            () -> new Block(Block.Properties.copy(Blocks.STONE)
                    .strength(2.5f)
            ));

    public static final RegistryObject<Block> PEGASUS_FEATHERS_BLOCK = BLOCKS.register("pegasus_feathers_block",
            () -> new Block(Block.Properties.copy(Blocks.WHITE_WOOL)
                    .strength(0.8f)
            ));

    public static final RegistryObject<Block> TEARS_OF_HADES_ORE = BLOCKS.register("tears_of_hades_ore",
            () -> new DropExperienceBlock(Block.Properties.copy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f),
                    UniformInt.of(3, 7)
            ));

    public static final RegistryObject<Block> TEARS_OF_HADES_BLOCK = BLOCKS.register("tears_of_hades_block",
            () -> new Block(Block.Properties.copy(Blocks.DIAMOND_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
            ));
}
