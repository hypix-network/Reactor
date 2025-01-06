package dev.hypix.reactor.api.item.armor;

import lombok.Getter;

/* Autogenerated - Don't touch :) */
@Getter
public final class TrimMaterial {

    public static final TrimMaterial[] ALL = new TrimMaterial[10];
    private final String name;
    private final String description;
    private final String ingredient;
    private final double modelIndex;

    public TrimMaterial(String name, String description, String ingredient, double modelIndex) {
        this.name = name;
        this.description = description;
        this.ingredient = ingredient;
        this.modelIndex = modelIndex;
    }

    private TrimMaterial(String name, String description, String ingredient, double modelIndex, int index) {
        this(name, description, ingredient, modelIndex);
        ALL[index] = this;
    }

    public static final TrimMaterial
        AMETHYST = new TrimMaterial("minecraft:amethyst","trim_material.minecraft.amethyst","minecraft:amethyst_shard",1.0,0),
        COPPER = new TrimMaterial("minecraft:copper","trim_material.minecraft.copper","minecraft:copper_ingot",0.5,1),
        DIAMOND = new TrimMaterial("minecraft:diamond","trim_material.minecraft.diamond","minecraft:diamond",0.800000011920929,2),
        EMERALD = new TrimMaterial("minecraft:emerald","trim_material.minecraft.emerald","minecraft:emerald",0.699999988079071,3),
        GOLD = new TrimMaterial("minecraft:gold","trim_material.minecraft.gold","minecraft:gold_ingot",0.6000000238418579,4),
        IRON = new TrimMaterial("minecraft:iron","trim_material.minecraft.iron","minecraft:iron_ingot",0.20000000298023224,5),
        LAPIS = new TrimMaterial("minecraft:lapis","trim_material.minecraft.lapis","minecraft:lapis_lazuli",0.8999999761581421,6),
        NETHERITE = new TrimMaterial("minecraft:netherite","trim_material.minecraft.netherite","minecraft:netherite_ingot",0.30000001192092896,7),
        QUARTZ = new TrimMaterial("minecraft:quartz","trim_material.minecraft.quartz","minecraft:quartz",0.10000000149011612,8),
        REDSTONE = new TrimMaterial("minecraft:redstone","trim_material.minecraft.redstone","minecraft:redstone",0.4000000059604645,9);
}