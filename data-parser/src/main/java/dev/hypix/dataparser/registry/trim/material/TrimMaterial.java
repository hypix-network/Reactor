/*
* Autogenerated file. Please don't touch :)
* Class generator: dev.hypix.dataparser.registry.trim.material.TrimMaterialParser (module data-parser)
* Date: Year: 2025. Month: 01. Day: 14. Time: 17:26:23
*/
package dev.hypix.dataparser.registry.trim.material;
import lombok.Getter;

import java.util.Collection;
import dev.hypix.dataparser.util.CopyOnWriteCollection;

@Getter
public final class TrimMaterial {

    public static final Collection<TrimMaterial> ALL = new CopyOnWriteCollection<>(11);

    private final String assetName, description, ingredient;
    private final double modelIndex;

    public TrimMaterial(String assetName, String description, String ingredient, double modelIndex) {
        this.assetName = assetName;
        this.description = description;
        this.ingredient = ingredient;
        this.modelIndex = modelIndex;
    }

    private TrimMaterial(String assetName, int index, String description, String ingredient, double modelIndex) {
        this(assetName, description, ingredient, modelIndex);
        ALL.add(this);
    }

    public static final TrimMaterial 
        GOLD = new TrimMaterial("gold",0,"trim_material.minecraft.gold","minecraft:gold_ingot",0.6000000238418579),
        DIAMOND = new TrimMaterial("diamond",1,"trim_material.minecraft.diamond","minecraft:diamond",0.800000011920929),
        RESIN = new TrimMaterial("resin",2,"trim_material.minecraft.resin","minecraft:resin_brick",1.0),
        COPPER = new TrimMaterial("copper",3,"trim_material.minecraft.copper","minecraft:copper_ingot",0.5),
        IRON = new TrimMaterial("iron",4,"trim_material.minecraft.iron","minecraft:iron_ingot",0.20000000298023224),
        QUARTZ = new TrimMaterial("quartz",5,"trim_material.minecraft.quartz","minecraft:quartz",0.10000000149011612),
        LAPIS = new TrimMaterial("lapis",6,"trim_material.minecraft.lapis","minecraft:lapis_lazuli",0.8999999761581421),
        AMETHYST = new TrimMaterial("amethyst",7,"trim_material.minecraft.amethyst","minecraft:amethyst_shard",1.0),
        REDSTONE = new TrimMaterial("redstone",8,"trim_material.minecraft.redstone","minecraft:redstone",0.4000000059604645),
        EMERALD = new TrimMaterial("emerald",9,"trim_material.minecraft.emerald","minecraft:emerald",0.699999988079071),
        NETHERITE = new TrimMaterial("netherite",10,"trim_material.minecraft.netherite","minecraft:netherite_ingot",0.30000001192092896);
}