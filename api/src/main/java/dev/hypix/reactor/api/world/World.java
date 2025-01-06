package dev.hypix.reactor.api.world;

import dev.hypix.reactor.api.world.data.Biome;
import dev.hypix.reactor.api.world.data.Gamerule;
import dev.hypix.reactor.api.world.data.WorldType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class World {
    private static int WORLDS_AMOUNT = 0;

    private final Gamerule gamerule = new Gamerule();
    private final int id = WORLDS_AMOUNT++;

    private final String name;
    private final WorldType worldType;
    private final Biome biome;


    public String identifier() {
        return "minecraft:"+name;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof World world && world.id == this.id);
    }
}
