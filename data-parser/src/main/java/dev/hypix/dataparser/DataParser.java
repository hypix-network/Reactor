package dev.hypix.dataparser;

import java.io.IOException;

import dev.hypix.dataparser.registry.banner.BannerParser;
import dev.hypix.dataparser.registry.biome.BiomeParser;
import dev.hypix.dataparser.registry.chat.ChatParser;
import dev.hypix.dataparser.registry.damage.DamageTypeParser;
import dev.hypix.dataparser.registry.dimension.DimensionTypeParser;
import dev.hypix.dataparser.registry.enchantment.EnchantmentParser;
import dev.hypix.dataparser.registry.food.FoodParser;
import dev.hypix.dataparser.registry.jukebox.JukeboxParser;
import dev.hypix.dataparser.registry.painting.PaintingParser;
import dev.hypix.dataparser.registry.trim.material.TrimMaterialParser;
import dev.hypix.dataparser.registry.trim.pattern.TrimPatternParser;
import dev.hypix.dataparser.registry.wolf.WolfVariantParser;
import dev.hypix.dataparser.tag.entity.EntityParser;
import dev.hypix.dataparser.tag.item.ItemParser;

public final class DataParser {

    public static void main(String[] args) {
        final String version = "1.21.4";
        final String parserName = "banner";
        final String option = "registry";

        Parser parser = null;

        if (option.equals("registry")) {
            parser = handleRegistry(version, parserName);
        } else if (option.equals("tags")) {
            parser = handleTags(version, parserName);
        }

        if (parser == null) {
            System.out.println("Can't found the option " + option);
            return;
        }
        try {
            parser.load();
        } catch (IOException e) {
            System.err.println("Error trying in registry parser. Parser loader:" + parserName);
            e.printStackTrace();
        }
    }

    private static Parser handleTags(final String version, final String parserName) {
        return switch(parserName) {
            case "entity" -> new EntityParser();
            case "item" -> new ItemParser();
            default -> null;
        };
    }
    
    private static Parser handleRegistry(final String version, final String parserName) {
        return switch(parserName) {
            case "chat" -> new ChatParser();
            case "food" -> new FoodParser();
            case "biome" -> new BiomeParser();
            case "banner" -> new BannerParser();
            case "jukebox" -> new JukeboxParser();
            case "wolf" -> new WolfVariantParser();
            case "damage" -> new DamageTypeParser();
            case "painting" -> new PaintingParser();
            case "dimension" -> new DimensionTypeParser();
            case "enchantment" -> new EnchantmentParser();
            case "trim-pattern" -> new TrimPatternParser();
            case "trim-material" -> new TrimMaterialParser();
            default -> null;
        };
    }
}
