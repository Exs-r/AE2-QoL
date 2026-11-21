package com.ae2qol.gift;

import appeng.api.implementations.items.IAEItemPowerStorage;
import appeng.api.ids.AEComponents;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.GenericStack;
import appeng.api.storage.cells.IBasicCellItem;
import com.ae2qol.Ae2Qol;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Builds the two kits. Both are fully charged 256k portable cells whose contents are
 * written through AE2's data components.
 */
public final class GiftFactory {
    private static final String PORTABLE_CELL = "ae2:portable_item_cell_256k";

    private GiftFactory() {
    }

    public static ItemStack buildStarter() {
        ItemStack cell = ItemLookup.stack(PORTABLE_CELL);
        if (cell.isEmpty()) {
            return ItemStack.EMPTY;
        }

        List<GenericStack> contents = new ArrayList<>();
        add(contents, "ae2:controller", 68);
        add(contents, "extendedae:ex_drive", 64);
        add(contents, "ae2:fluix_smart_cable", 64);
        add(contents, "ae2:creative_energy_cell", 1);
        add(contents, "ae2:quantum_ring", 8);
        add(contents, "ae2:quantum_link", 1);
        add(contents, "ae2:crafting_terminal", 1);
        add(contents, "ae2:pattern_encoding_terminal", 1);
        add(contents, "ae2:wireless_crafting_terminal", 1);
        add(contents, "ae2wtlib:wireless_pattern_encoding_terminal", 1);
        add(contents, "extendedae:ex_pattern_access_part", 1);
        add(contents, "extendedae:wireless_ex_pat", 1);
        add(contents, "ae2wtlib:quantum_bridge_card", 1);
        add(contents, "extendedae_plus:infinity_biginteger_cell", 1);
        add(contents, "ae2_ftbquest_detector:me_quests_detector", 1);

        // The two singularities must share the same entanglement id to form a quantum pair.
        addEntangledSingularities(contents);

        return finish(cell, "新手礼包", contents);
    }

    public static ItemStack buildInfinity() {
        ItemStack cell = ItemLookup.stack(PORTABLE_CELL);
        if (cell.isEmpty()) {
            return ItemStack.EMPTY;
        }

        List<GenericStack> contents = new ArrayList<>();
        add(contents, "ae2:fluix_smart_cable", 1);
        add(contents, "ae2:quartz_fiber", 1);
        add(contents, "ae2:storage_bus", 1);
        add(contents, "ae2:speed_card", 1);
        add(contents, "ae2:blank_pattern", 1);
        add(contents, "extendedae:infinity_water_cell", 1);
        add(contents, "extendedae:infinity_cobblestone_cell", 1);
        add(contents, "extendedae:ex_interface", 1);
        add(contents, "extendedae:ex_pattern_provider", 1);
        add(contents, "extendedae:ex_import_bus_part", 1);
        add(contents, "extendedae:ex_export_bus_part", 1);
        add(contents, "extendedae_plus:labeled_wireless_transceiver", 1);

        return finish(cell, "ME无限元件包", contents);
    }

    private static void add(List<GenericStack> contents, String id, long amount) {
        ItemStack stack = ItemLookup.stack(id);
        if (stack.isEmpty()) {
            return;
        }
        contents.add(new GenericStack(AEItemKey.of(stack), amount));
    }

    private static void addEntangledSingularities(List<GenericStack> contents) {
        ItemStack singularity = ItemLookup.stack("ae2:quantum_entangled_singularity");
        if (singularity.isEmpty()) {
            return;
        }
        singularity.set(AEComponents.ENTANGLED_SINGULARITY_ID, ThreadLocalRandom.current().nextLong());
        contents.add(new GenericStack(AEItemKey.of(singularity), 2));
    }

    private static ItemStack finish(ItemStack cell, String name, List<GenericStack> contents) {
        validateCapacity(cell, contents);

        cell.set(DataComponents.CUSTOM_NAME, Component.literal(name));
        cell.set(AEComponents.STORAGE_CELL_INV, List.copyOf(contents));
        charge(cell);
        return cell;
    }

    private static void charge(ItemStack cell) {
        if (cell.getItem() instanceof IAEItemPowerStorage power) {
            double max = power.getAEMaxPower(cell);
            if (max > 0) {
                cell.set(AEComponents.ENERGY_CAPACITY, max);
                cell.set(AEComponents.STORED_ENERGY, max);
            }
        }
    }

    private static void validateCapacity(ItemStack cell, List<GenericStack> contents) {
        if (!(cell.getItem() instanceof IBasicCellItem basic)) {
            return;
        }

        int maxTypes = basic.getTotalTypes(cell);
        long capacity = basic.getBytes(cell);
        long items = 0L;
        for (GenericStack stack : contents) {
            items += stack.amount();
        }

        if (contents.size() > maxTypes || items / 8L > capacity) {
            Ae2Qol.LOG.warn("Gift '{}' exceeds cell capacity: {} types (max {}), {} items (capacity {} bytes)",
                    nameOf(cell), contents.size(), maxTypes, items, capacity);
        }
    }

    private static String nameOf(ItemStack stack) {
        return stack.getHoverName().getString();
    }
}
