package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.block.MyhtosBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
    public static final RegistryObject<Block> MYTHOS_BLOCK = BLOCKS.register("mythos_block",
            () -> new MyhtosBlock(Block.Properties.copy(Blocks.WHITE_WOOL)
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


    public static final RegistryObject<Block> MOLY_HERB = BLOCKS.register("moly_herb",
            () -> new FlowerBlock(() -> MobEffects.SLOW_FALLING, 50,
                    BlockBehaviour.Properties.copy(Blocks.OXEYE_DAISY).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_MOLY_HERB = BLOCKS.register("potted_moly_herb",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BlocksRegistry.MOLY_HERB,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_OXEYE_DAISY).noOcclusion()));
}
