package dev.hypix.reactor.api.world;

import java.util.ArrayList;
import java.util.List;

import dev.hypix.reactor.api.world.data.Biome;
import dev.hypix.reactor.api.world.data.WorldType;

public final class WorldManager {

    private final List<World> worlds = new ArrayList<>();
    private World defaultWorld;

    public void setDefault() {
        this.worlds.clear();
        this.defaultWorld = new World("default", WorldType.NORMAL, Biome.PLAINS);
        this.worlds.add(defaultWorld);
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public World getDefaultWorld() {
        return defaultWorld;
    }

    public void setDefaultWorld(World defaultWorld) {
        this.worlds.remove(defaultWorld);
        this.defaultWorld = defaultWorld;
    }
}