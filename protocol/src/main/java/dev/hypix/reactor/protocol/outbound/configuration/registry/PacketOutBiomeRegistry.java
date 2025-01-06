package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.api.world.data.Biome;
import dev.hypix.reactor.api.world.data.Biome.Effects;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutBiomeRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final int amount = Biome.ALL.length;
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 256, 64);

        buffer.writeString("minecraft:worldgen/biome");
        buffer.writeVarInt(amount);
    
        for (final Biome biome : Biome.ALL) {
            buffer.writeString(biome.getId());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addBoolean("has_precipitation", biome.isHasPrecipitation());
            nbt.addFloat("temperature", (float)biome.getTemperature());
            nbt.addFloat("downfall", (float)biome.getDownFall());
            nbt.addCompound("effects", createEffects(biome.getEffects()));
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    private NBTFastWrite createEffects(final Effects effects) {
        final NBTFastWrite nbt = new NBTFastWrite();
        nbt.addInt("fog_color", effects.fogColor());
        nbt.addInt("water_color", effects.waterColor());
        nbt.addInt("water_fog_color", effects.waterFogColor());
        nbt.addInt("sky_color", effects.skyColor());
        if (effects.foliageColor() != null) {
            nbt.addInt("foliage_color", effects.foliageColor());
        }
        if (effects.grassColor() != null) {
            nbt.addInt("grass_color", effects.grassColor());
        }
        if (effects.grassColorModifier() != null) {
            nbt.addString("grass_color_modifier", effects.grassColorModifier());
        }

        if (effects.particle() != null) {
            nbt.addCompound("particle", createParticle(effects.particle()));
        }
        if (effects.moodSound() != null) {
            nbt.addCompound("mood_sound", createMoodSound(effects.moodSound()));
        }
        if (effects.additionSound() != null) {
            nbt.addCompound("additions_sound", createAdditionSound(effects.additionSound()));
        }
        if (effects.music() != null) {
            nbt.addCompound("music", createMusic(effects.music()));
        }

        return nbt;
    }

    private NBTFastWrite createParticle(final Effects.Particle particle) {
        final NBTFastWrite nbt = new NBTFastWrite(1);
        nbt.addString("type", particle.type());
        return nbt;
    }

    private NBTFastWrite createMoodSound(final Effects.MoodSound sound) {
        final NBTFastWrite nbt = new NBTFastWrite(4);
        nbt.addString("sound", sound.sound());
        nbt.addInt("block_search_extent", sound.blockSearchExtent());
        nbt.addInt("tick_delay", sound.tickDelay());
        nbt.addDouble("offset", sound.offSet());
        return nbt;
    }

    private NBTFastWrite createAdditionSound(final Effects.AdditionSound sound) {
        final NBTFastWrite nbt = new NBTFastWrite(2);
        nbt.addString("sound", sound.sound());
        nbt.addDouble("tick_chance", sound.tickChance());
        return nbt;
    }

    private NBTFastWrite createMusic(final Effects.Music music) {
        final NBTFastWrite nbt = new NBTFastWrite(4);
        nbt.addString("sound", music.sound());
        nbt.addInt("min_delay", music.minDelay());
        nbt.addInt("max_delay", music.maxDelay());
        nbt.addBoolean("replace_current_music", music.replaceCurrentMusic());
        return nbt;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}