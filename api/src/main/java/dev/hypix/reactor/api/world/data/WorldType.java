package dev.hypix.reactor.api.world.data;

/*
 * See description in: https://web.archive.org/web/20241130184457/https://wiki.vg/Registry_Data
 */
public record WorldType(
    String infiniburn,
    boolean hasSkyLight,
    boolean hasCeiling,
    boolean ultraWarm,
    boolean natural,
    double coordinateScale,
    boolean bedWorks,
    boolean respawnAnchorWorks,
    int minY,
    int height,
    int logicalHeight,
    float ambientLight,
    boolean piglinSafe,
    boolean hasRaids,
    String effects
) {

    public static final WorldType NORMAL = new WorldType(
        "#minecraft:infiniburn_overworld",
        true,
        false,
        false,
        true,
        1.0,
        true,
        false,
        -64,
        384,
        384,
        0.0F,
        true,
        true,
        "minecraft:overworld"
    );

    public static final WorldType NETHER = new WorldType(
        "#minecraft:infiniburn_nether",
        false,
        true,
        true,
        false,
        8.0,
        false,
        true,
        0,
        256,
        128,
        0.1F,
        false,
        false,
        "minecraft:the_nether"
    );

    public static final WorldType END = new WorldType(
        "#minecraft:infiniburn_end",
        false,
        false,
        false,
        false,
        1.0,
        false,
        false,
        0,
        256,
        256,
        0.0F,
        true,
        false,
        "minecraft:the_end"
    );
}
