package dev.hypix.dataparser.registry.food;

import java.io.IOException;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import dev.hypix.dataparser.Parser;

public final class FoodParser implements Parser {

    @Override
    public void load() throws IOException {
        final String template = loadTemplate("registry/food");
        final JSONArray array = loadJsonArray("registry/food.json");
        final StringBuilder builder = new StringBuilder();

        int i = 0;
        for (final Object entry : array) {
            if (!(entry instanceof JSONObject food)) {
                continue;
            }
            final String name = food.getString("name");
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(name));
            builder.append(" = new Food(");
            append(builder,
                name,
                food.getIntValue("id"),
                food.getDoubleValue("foodPoints"),
                i
            );

            builder.append(')');

            if (++i != array.size()) {
                builder.append(',');
            }
        }

        writeFile(getClass(), template
            .replace("{ALL_FIELD}", String.valueOf(array.size()))
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "Food");
    }
}
