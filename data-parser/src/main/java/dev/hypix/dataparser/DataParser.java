package dev.hypix.dataparser;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.alibaba.fastjson2.JSON;

import dev.hypix.dataparser.banner.BannerParser;
import dev.hypix.dataparser.biome.BiomeParser;
import dev.hypix.dataparser.chat.ChatTypeParser;
import dev.hypix.dataparser.damage.DamageTypeParser;
import dev.hypix.dataparser.painting.PaintingParser;
import dev.hypix.dataparser.sound.SoundParser;
import dev.hypix.dataparser.trim.material.TrimMaterialParser;
import dev.hypix.dataparser.trim.pattern.TrimPatterParser;
import dev.hypix.dataparser.wolf.WorldVariantParser;

public final class DataParser {

    private static final File HOME = new File("").getAbsoluteFile();
    private static final File RESOURCES = new File(HOME, "data-parser/src/main/resources");
    private static final File SRC = new File(HOME, "data-parser/src/main/java/dev/hypix/dataparser");

    public static void main(String[] args) {
        final String version = "1.21.1";
        final String parserName = "chat";

        final Parser parser = switch (parserName) {
            case "banner" -> new BannerParser();
            case "sound" -> new SoundParser();
            case "damage" -> new DamageTypeParser();
            case "painting" -> new PaintingParser();
            case "trim-pattern" -> new TrimPatterParser();
            case "trim-material" -> new TrimMaterialParser();
            case "wolf" -> new WorldVariantParser();
            case "biome" -> new BiomeParser();
            case "chat" -> new ChatTypeParser();
            default -> null;
        };

        if (parser == null) {
            System.out.println("No existe el parser " + parserName);
            return;
        }

        final File fileRequired = new File(RESOURCES, version + "/" + parser.requiredFile());
        if (!fileRequired.exists()) {
            System.out.println("No existe el archivo " + fileRequired.getPath());
            return;
        }
        System.out.println("Archivo " + fileRequired + " encontrado. Ejecutando el parser: " + parserName + "...");

        try {
            long time = System.currentTimeMillis();
            final Parser.ParsedFile parsedFile = parser.parse(JSON.parseObject(Files.newInputStream(fileRequired.toPath()), StandardCharsets.UTF_8));
            if (parsedFile == null) {
                return;
            }
            long diferenceTime =  (System.currentTimeMillis() - time);
            System.out.println("Tiempo de parsed : " + diferenceTime + "ms");

            time = System.currentTimeMillis();
            final File out = new File(SRC, parsedFile.destination());
            if (!out.exists()) {
                out.createNewFile();
            }
            Files.newOutputStream(out.toPath()).write(parsedFile.data().getBytes());
            diferenceTime = System.currentTimeMillis() - time;
            System.out.println("Tiempo de escritura: " + diferenceTime + "ms");
        } catch (Exception e) {
            System.err.println("Hubo un error al ejecutar el parser " + parserName);
            e.printStackTrace();
        }
    }
}
