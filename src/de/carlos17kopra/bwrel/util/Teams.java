//CopyRight at Carlos17Kopra | Arvid

package de.carlos17kopra.bwrel.util;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public enum Teams {

    BLUE("Blau", "§1", new ItemBuilder(Material.STAINED_CLAY, 1, 11).setName("§1Blau").create()),
    RED("Rot", "§4", new ItemBuilder(Material.STAINED_CLAY, 1, 14).setName("§4Rot").create()),
    GREEN("Grün", "§2", new ItemBuilder(Material.STAINED_CLAY, 1, 13).setName("§2Grün").create()),
    YELLOW("Gelb", "§e", new ItemBuilder(Material.STAINED_CLAY, 1, 4).setName("§eGelb").create()),
    NONE("Kein Team", "§7", new ItemBuilder(Material.QUARTZ_BLOCK, 1).setName("§7kein Team").create());

    Teams(String name, String color, ItemStack stack){
        this.name = name;
        this.color = color;
        this.stack = stack;
    }
    private String name;
    private String color;
    private ItemStack stack;

    public ItemStack getStack() {
        return stack;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
