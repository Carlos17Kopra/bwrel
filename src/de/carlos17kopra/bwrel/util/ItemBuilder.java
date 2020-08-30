package de.carlos17kopra.bwrel.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    private ItemStack is;
    private ItemMeta im;

    public ItemBuilder(Material material) {
        this.is = new ItemStack(material, 1);
        this.im = this.is.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.is = new ItemStack(material, amount);
        this.im = this.is.getItemMeta();
    }

    public ItemBuilder(Material material, int amount, int subid) {
        this.is = new ItemStack(material, amount, (short) subid);
        this.im = this.is.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        this.im.setDisplayName(name);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        this.im.addEnchant(enchantment, level, false);
        return this;
    }

    public ItemBuilder addItemflag(ItemFlag... itemFlag) {
        this.im.addItemFlags(itemFlag);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.im.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.im.setLore(lore);
        return this;
    }

    public ItemBuilder addLore2(String lorename1, String lorename2) {
        List<String> lore = new ArrayList<String>();
        this.im.setLore(lore);
        lore.add(lorename1);
        lore.add(lorename2);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.is.setAmount(amount);
        return this;
    }

    public ItemBuilder setSkullOwner(String name) {
        SkullMeta meta = (SkullMeta) this.im;
        meta.setOwner(name.toLowerCase());
        return this;
    }

    public ItemBuilder setLeatherColor(Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) this.im;
        meta.setColor(color);
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        this.is.setDurability((short) durability);
        return this;
    }

    public ItemStack create() {
        this.is.setItemMeta(this.im);
        return this.is;
    }
}
