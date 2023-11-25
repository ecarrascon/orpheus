package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Orpheus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }


}
