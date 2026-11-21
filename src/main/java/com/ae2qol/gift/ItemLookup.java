package com.ae2qol.gift;

import com.ae2qol.Ae2Qol;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Resolves items by registry name so that optional addons never become hard dependencies.
 */
public final class ItemLookup {
    private ItemLookup() {
    }

    public static ItemStack stack(String id) {
        ResourceLocation rl = ResourceLocation.tryParse(id);
        if (rl == null) {
            Ae2Qol.LOG.warn("Invalid item id '{}'", id);
            return ItemStack.EMPTY;
        }

        Item item = BuiltInRegistries.ITEM.get(rl);
        if (item == null || item == Items.AIR) {
            Ae2Qol.LOG.warn("Item '{}' is missing - the related addon is probably not installed", id);
            return ItemStack.EMPTY;
        }
        return new ItemStack(item);
    }
}
