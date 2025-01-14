package dev.hypix.reactor.protocol.outbound.configuration;

import dev.hypix.reactor.api.entity.damage.DamageType;
import dev.hypix.reactor.api.entity.wolf.WolfType;
import dev.hypix.reactor.api.item.armor.TrimMaterial;
import dev.hypix.reactor.api.item.armor.TrimPattern;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.api.world.block.Banner;
import dev.hypix.reactor.api.world.block.Painting;
import dev.hypix.reactor.api.world.data.Biome;
import dev.hypix.reactor.api.world.data.Biome.Effects;
import dev.hypix.reactor.api.world.data.WorldType;
import dev.hypix.reactor.protocol.outbound.CachedPacket;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class Registries {

    public static CachedPacket packet(final byte[] data) {
        return new CachedPacket(data, IdPacketOutbound.CONFIG_REGISTRY_DATA);
    }

    public static byte[] wolfVariants() {
        final int amountWolfs = WolfType.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amountWolfs * 64, 64);

        buffer.writeString("minecraft:wolf_variant");
        buffer.writeVarInt(amountWolfs);

        for (final WolfType wolfType : WolfType.ALL) {
            buffer.writeString("minecraft:"+wolfType.getName());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("wild_texture", wolfType.getWildTexture());
            nbt.addString("tame_texture", wolfType.getTameTexture());
            nbt.addString("angry_texture", wolfType.getAngryTexture());
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    public static byte[] damageTypes() {
        final int amount = DamageType.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 32, 16);

        buffer.writeString("minecraft:damage_type");
        buffer.writeVarInt(amount);
    
        for (final DamageType damageType : DamageType.ALL) {
            buffer.writeString("minecraft:"+damageType.getName());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("message_id", damageType.getMessageId());
            nbt.addString("scaling", damageType.getScaling());
            nbt.addFloat("exhaustion", (float)damageType.getExhaustion());
            nbt.addString("effects", "hurt");
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    public static byte[] trimMaterial() {
        final int amount = TrimMaterial.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 128, 24);

        buffer.writeString("minecraft:trim_material");
        buffer.writeVarInt(amount);
    
        for (final TrimMaterial material : TrimMaterial.ALL) {
            buffer.writeString("minecraft:"+material.getAssetName());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("asset_name", material.getAssetName());
            nbt.addString("ingredient", material.getIngredient());
            nbt.addFloat("item_model_index", (float)material.getModelIndex());
            nbt.addString("description", material.getDescription());

            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    public static byte[] trimPattern() {
        final int amount = TrimPattern.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 32, 16);

        buffer.writeString("minecraft:trim_pattern");
        buffer.writeVarInt(amount);
    
        for (final TrimPattern pattern : TrimPattern.ALL) {
            buffer.writeString(pattern.getAssetId());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("asset_id", pattern.getAssetId());
            nbt.addString("template_item", pattern.getTemplateItem());
            nbt.addString("description", pattern.getDescription());
            nbt.addBoolean("decal", pattern.isDecal());
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    public static byte[] banner() {
        final int amount = Banner.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 48, 16);

        buffer.writeString("minecraft:banner_pattern");
        buffer.writeVarInt(amount);
    
        for (final Banner banner : Banner.ALL) {
            buffer.writeString(banner.getAssetId());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("asset_id", banner.getAssetId());
            nbt.addString("translation_key", banner.getTranslationKey());
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    public static byte[] painting() {
        final int amount = Painting.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 18, 8);

        buffer.writeString("minecraft:painting_variant");
        buffer.writeVarInt(amount);
    
        for (final Painting painting : Painting.ALL) {
            buffer.writeString(painting.getAssetId());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("asset_id", painting.getAssetId());
            nbt.addInt("height", painting.getHeight());
            nbt.addInt("width", painting.getWidth());
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    public static byte[] dimensionTypes() {
        final int amount = WorldType.ALL.length;
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 18, 8);

        buffer.writeString("minecraft:dimension_type");
        buffer.writeVarInt(amount);
    
        for (final WorldType world : WorldType.ALL) {
            buffer.writeMCId(world.name());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite(15);

            nbt.addBoolean("has_skylight", world.hasSkylight());
            nbt.addBoolean("has_ceiling", world.hasCeiling());
            nbt.addBoolean("ultrawarm", world.ultrawarm());
            nbt.addBoolean("natural", world.natural());
            nbt.addDouble("coordinate_scale", world.coordinateScale());
            nbt.addBoolean("bed_works", world.bedWorks());
            nbt.addBoolean("respawn_anchor_works", world.respawnAnchorWorks());
            nbt.addInt("min_y", world.minY());
            nbt.addInt("height", world.height());
            nbt.addInt("logical_height", world.logicalHeight());
            nbt.addString("infiniburn", world.infiniburn());
            nbt.addString("effects", world.effects());
            nbt.addFloat("ambient_light", (float)world.ambientLight());
            nbt.addBoolean("piglin_safe", world.piglinSafe());
            nbt.addBoolean("has_raids", world.hasRaids());

            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    public static byte[] biome() {
        final int amount = Biome.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 256, 64);

        buffer.writeString("minecraft:worldgen/biome");
        buffer.writeVarInt(amount);
    
        for (final Biome biome : Biome.ALL) {
            buffer.writeString("minecraft:"+biome.getIdentifier());
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

    private static NBTFastWrite createEffects(final Effects effects) {
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

    private static NBTFastWrite createParticle(final Effects.Particle particle) {
        final NBTFastWrite nbt = new NBTFastWrite(1);
        nbt.addString("type", particle.type());
        return nbt;
    }

    private static NBTFastWrite createMoodSound(final Effects.MoodSound sound) {
        final NBTFastWrite nbt = new NBTFastWrite(4);
        nbt.addString("sound", sound.sound());
        nbt.addInt("block_search_extent", sound.blockSearchExtent());
        nbt.addInt("tick_delay", sound.tickDelay());
        nbt.addDouble("offset", sound.offSet());
        return nbt;
    }

    private static NBTFastWrite createAdditionSound(final Effects.AdditionSound sound) {
        final NBTFastWrite nbt = new NBTFastWrite(2);
        nbt.addString("sound", sound.sound());
        nbt.addDouble("tick_chance", sound.tickChance());
        return nbt;
    }

    private static NBTFastWrite createMusic(final Effects.Music music) {
        final NBTFastWrite nbt = new NBTFastWrite(4);
        nbt.addString("sound", music.sound());
        nbt.addInt("min_delay", music.minDelay());
        nbt.addInt("max_delay", music.maxDelay());
        nbt.addBoolean("replace_current_music", music.replaceCurrentMusic());
        return nbt;
    }
}
