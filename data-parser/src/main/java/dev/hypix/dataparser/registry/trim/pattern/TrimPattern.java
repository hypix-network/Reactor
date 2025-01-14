/*
* Autogenerated file. Please don't touch :)
* Class generator: dev.hypix.dataparser.registry.trim.pattern.TrimPatternParser (module data-parser)
* Date: Year: 2025. Month: 01. Day: 14. Time: 17:24:14
*/
package dev.hypix.dataparser.registry.trim.pattern;
import lombok.Getter;

import dev.hypix.dataparser.util.CopyOnWriteCollection;
import java.util.Collection;

@Getter
public final class TrimPattern {

    public static final Collection<TrimPattern> ALL = new CopyOnWriteCollection<>(18);

    private final String assetId, description, templateItem;
    private final boolean decal;

    public TrimPattern(String assetId, String description, String templateItem, boolean decal) {
        this.assetId = assetId;
        this.description = description;
        this.templateItem = templateItem;
        this.decal = decal;
    }

    private TrimPattern(String assetId, int index, String description, String templateItem, boolean decal) {
        this(assetId, description, templateItem, decal);
        ALL.add(this);
    }

    public static final TrimPattern 
        TIDE = new TrimPattern("minecraft:tide",0,"trim_pattern.minecraft.tide","minecraft:tide_armor_trim_smithing_template",false),
        DUNE = new TrimPattern("minecraft:dune",1,"trim_pattern.minecraft.dune","minecraft:dune_armor_trim_smithing_template",false),
        WAYFINDER = new TrimPattern("minecraft:wayfinder",2,"trim_pattern.minecraft.wayfinder","minecraft:wayfinder_armor_trim_smithing_template",false),
        VEX = new TrimPattern("minecraft:vex",3,"trim_pattern.minecraft.vex","minecraft:vex_armor_trim_smithing_template",false),
        RIB = new TrimPattern("minecraft:rib",4,"trim_pattern.minecraft.rib","minecraft:rib_armor_trim_smithing_template",false),
        WARD = new TrimPattern("minecraft:ward",5,"trim_pattern.minecraft.ward","minecraft:ward_armor_trim_smithing_template",false),
        EYE = new TrimPattern("minecraft:eye",6,"trim_pattern.minecraft.eye","minecraft:eye_armor_trim_smithing_template",false),
        COAST = new TrimPattern("minecraft:coast",7,"trim_pattern.minecraft.coast","minecraft:coast_armor_trim_smithing_template",false),
        SHAPER = new TrimPattern("minecraft:shaper",8,"trim_pattern.minecraft.shaper","minecraft:shaper_armor_trim_smithing_template",false),
        SPIRE = new TrimPattern("minecraft:spire",9,"trim_pattern.minecraft.spire","minecraft:spire_armor_trim_smithing_template",false),
        RAISER = new TrimPattern("minecraft:raiser",10,"trim_pattern.minecraft.raiser","minecraft:raiser_armor_trim_smithing_template",false),
        HOST = new TrimPattern("minecraft:host",11,"trim_pattern.minecraft.host","minecraft:host_armor_trim_smithing_template",false),
        SILENCE = new TrimPattern("minecraft:silence",12,"trim_pattern.minecraft.silence","minecraft:silence_armor_trim_smithing_template",false),
        BOLT = new TrimPattern("minecraft:bolt",13,"trim_pattern.minecraft.bolt","minecraft:bolt_armor_trim_smithing_template",false),
        SNOUT = new TrimPattern("minecraft:snout",14,"trim_pattern.minecraft.snout","minecraft:snout_armor_trim_smithing_template",false),
        WILD = new TrimPattern("minecraft:wild",15,"trim_pattern.minecraft.wild","minecraft:wild_armor_trim_smithing_template",false),
        FLOW = new TrimPattern("minecraft:flow",16,"trim_pattern.minecraft.flow","minecraft:flow_armor_trim_smithing_template",false),
        SENTRY = new TrimPattern("minecraft:sentry",17,"trim_pattern.minecraft.sentry","minecraft:sentry_armor_trim_smithing_template",false);
}