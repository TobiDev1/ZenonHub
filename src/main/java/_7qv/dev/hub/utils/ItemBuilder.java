package _7qv.dev.hub.utils;

import com.google.common.base.Preconditions;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.HashMap;
import java.util.List;

public class ItemBuilder {

    private Material material;
    private Short durability;
    private String title;
    private int amount = 1;
    private List<String> lores;
    private ItemMeta meta;
    @SuppressWarnings("unused")
    private byte materialData;
    private HashMap<Enchantment, Integer> enchantments = new HashMap<>();
    private ItemStack itemStack;

    public ItemBuilder() {
        this.itemStack = new ItemStack(Material.AIR);
    }

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder setName(final String displayName) {
        this.meta.setDisplayName(displayName);
        return this;
    }


    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemBuilder(Material material, int amount) {
        this(material, amount, (byte) 0);
    }
    public ItemBuilder(Material material, int amount, byte data) {
        Preconditions.checkNotNull(material, "Material cannot be null");
        Preconditions.checkArgument(amount > 0, "Amount must be positive");
        this.itemStack = new ItemStack(material, amount, data);
    }

    public ItemBuilder material(Material material){
        this.material = material;
        return this;
    }

    public ItemBuilder durability(short durability){
        this.durability = durability;
        return this;
    }

    public ItemBuilder title(String title){
        this.title = title;
        return this;
    }

    public ItemBuilder displayName(String name) {
        if (this.meta == null) {
            this.meta = itemStack.getItemMeta();
        }

        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if (this.meta == null) {
            this.meta = itemStack.getItemMeta();
        }

        meta.setLore(lore);
        return this;
    }

    public ItemBuilder amount(int amount){
        this.amount = amount;
        return this;
    }

    public ItemBuilder lores(List<String> lores){
        this.lores = lores;
        return this;
    }

    public ItemBuilder enchantment(final Enchantment enchantment, final int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemBuilder enchantment(final Enchantment enchantment) {
        enchantment(enchantment, 1);
        return this;
    }

    public ItemBuilder clearLore() {
        lores.clear();
        return this;
    }
    public ItemBuilder color(Color color) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (!(meta instanceof LeatherArmorMeta)) {
            throw new UnsupportedOperationException("Cannot set color of a non-leather armor item.");
        }
        ((LeatherArmorMeta)meta).setColor(color);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder data(final int data) {
        this.materialData = (byte) data;
        return this;
    }

    public ItemBuilder clearEnchantments() {
        enchantments.clear();
        return this;
    }
    public ItemBuilder durability(final int durability) {
        this.itemStack.setDurability((short)durability);
        return this;
    }

    public ItemStack build(){
        ItemStack itemStack = this.itemStack;
        if (this.material != null) {
            itemStack.setType(this.material);
        }
        //TODO Enchantments
        for (Enchantment enchantment : enchantments.keySet()) {
            itemStack.addUnsafeEnchantment(enchantment, enchantments.get(enchantment));
        }
        ItemMeta meta = itemStack.getItemMeta();
        if (this.amount > 0)
            itemStack.setAmount(this.amount);
        if (this.durability != null)
            itemStack.setDurability(this.durability);
        if (this.title != null)
            meta.setDisplayName(Format.formatMessage("&r" + this.title));
        if (this.lores != null && this.lores.size() > 0)
            meta.setLore(Format.formatMessages(this.lores));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
