package dev.hypix.dataparser.registry.dimension;

import java.io.IOException;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONObject;

import dev.hypix.dataparser.Parser;
import dev.hypix.dataparser.util.AppendOptions;

public final class DimensionTypeParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/dimension");
        final JSONObject jsonObject = loadJsonObject("registry/dimension_types.json");
        final StringBuilder builder = new StringBuilder();

        final AppendOptions options = new AppendOptions()
            .setSpacesInNewLine(12)
            .setStartInNewLine(true);

        int i = 0;
        for (final Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!(entry.getValue() instanceof JSONObject dimensionType)) {
                continue;
            }
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(entry.getKey()));
            builder.append(" = new WorldType(");

            append(options, builder,
                entry.getKey(),
                dimensionType.getDouble("ambient_light"),
                dimensionType.getBoolean("bed_works"),
                dimensionType.getDouble("coordinate_scale"),
                dimensionType.getString("effects"),
                dimensionType.getBoolean("has_ceiling"),
                dimensionType.getBoolean("has_raids"),
                dimensionType.getBoolean("has_skylight"),
                dimensionType.getIntValue("height"),
                dimensionType.getString("infiniburn"),
                dimensionType.getIntValue("logical_height"),
                dimensionType.getIntValue("min_y"),
                dimensionType.getIntValue("monster_spawn_block_light_limit"),
                dimensionType.getBoolean("natural"),
                dimensionType.getBoolean("piglin_safe"),
                dimensionType.getBoolean("respawn_anchor_works"),
                dimensionType.getBoolean("ultrawarm")
            );

            builder.append(')');

            if (++i != jsonObject.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "WorldType");
    }
}
