package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.block.MythosBlock;
import com.ecarrascon.orpheus.block.NectarCropBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.Supplier;

public enum BlocksRegistry {

    DEEPSLATE_TEARS_OF_HADES_ORE("deepslate_tears_of_hades_ore", () -> new ExperienceDroppingBlock(FabricBlockSettings
            .copyOf(Blocks.DEEPSLATE_DIAMOND_ORE)
            .requiresTool(),
            UniformIntProvider.create(3, 7)
    )),
    EPIPHANY_TABLE("epiphany_table", () -> new Block(FabricBlockSettings
            .copyOf(Blocks.QUARTZ_BLOCK)
            .nonOpaque()
    )),
    MYTHOS_BLOCK("mythos_block", () -> new MythosBlock(FabricBlockSettings
            .copyOf(Blocks.WHITE_WOOL)
            .sounds(BlockSoundGroup.WOOL)
            .strength(0.8f)
    )),
    MOLY_HERB("moly_herb", () -> new FlowerBlock(
            StatusEffects.SLOW_FALLING, 50,
            FabricBlockSettings.copy(Blocks.OXEYE_DAISY)
    )),
    NECTAR_CROP("nectar_crop", () -> new NectarCropBlock(
            FabricBlockSettings.copy(Blocks.WHEAT))),
    POTTED_MOLY_HERB("potted_moly_herb", () -> new FlowerPotBlock(
            BlocksRegistry.MOLY_HERB.block,
            FabricBlockSettings.copy(Blocks.POTTED_OXEYE_DAISY)
    )),
    PEGASUS_FEATHERS_BLOCK("pegasus_feathers_block", () -> new Block(FabricBlockSettings
            .copyOf(Blocks.WHITE_WOOL)
            .sounds(BlockSoundGroup.WOOL)
            .strength(0.8f)
    )),
    TEARS_OF_HADES_ORE("tears_of_hades_ore", () -> new ExperienceDroppingBlock(FabricBlockSettings
            .copyOf(Blocks.DIAMOND_ORE)
            .requiresTool(),
            UniformIntProvider.create(3, 7)
    )),
    TEARS_OF_HADES_BLOCK("tears_of_hades_block", () -> new Block(FabricBlockSettings
            .copyOf(Blocks.DIAMOND_BLOCK)
            .requiresTool().strength(5.0f, 6.0f)
            .sounds(BlockSoundGroup.METAL)
            ));


    private static FlammableBlockRegistry.Entry flammable(int burnChance, @SuppressWarnings("SameParameterValue") int spreadChance) {
        return new FlammableBlockRegistry.Entry(burnChance, spreadChance);
    }

    private static boolean isValidFlammableEntry(FlammableBlockRegistry.Entry flammableRate) {
        return flammableRate != null && flammableRate.getBurnChance() > 0 && flammableRate.getSpreadChance() > 0;
    }

    private final String pathName;
    private final Supplier<Block> blockSupplier;
    private final FlammableBlockRegistry.Entry flammableRate;
    private final boolean isCutout;
    private Block block;

    BlocksRegistry(String pathName, Supplier<Block> blockSupplier) {
        this(pathName, blockSupplier, false, new FlammableBlockRegistry.Entry(0, 0));
    }

    BlocksRegistry(String pathName, Supplier<Block> blockSupplier, boolean isCutout) {
        this(pathName, blockSupplier, isCutout, new FlammableBlockRegistry.Entry(0, 0));
    }

    BlocksRegistry(String pathName, Supplier<Block> blockSupplier, boolean isCutout, FlammableBlockRegistry.Entry flammableRate) {
        this.pathName = pathName;
        this.blockSupplier = blockSupplier;
        this.flammableRate = flammableRate;
        this.isCutout = isCutout;
    }

    public static void registerAll() {
        for (BlocksRegistry value : values()) {
            Block block = value.get();
            Registry.register(Registries.BLOCK, new Identifier(Orpheus.MOD_ID, value.pathName), block);
            if (isValidFlammableEntry(value.flammableRate)) {
                FlammableBlockRegistry.getDefaultInstance().add(block, value.flammableRate);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static void registerRenderLayer() {
        for (BlocksRegistry value : values()) {
            if (value.isCutout) {
                BlockRenderLayerMap.INSTANCE.putBlock(value.get(), RenderLayer.getCutout());
            }
        }
    }

    public Block get() {
        if (block == null) {
            block = blockSupplier.get();
        }

        return block;
    }

    public String getId() {
        return Registries.BLOCK.getId(get()).toString();
    }

}
