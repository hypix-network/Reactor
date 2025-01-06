package dev.hypix.dataparser;

import com.alibaba.fastjson2.JSONObject;

public interface Parser {

    String requiredFile();
    ParsedFile parse(final JSONObject json);

    default void appendString(final String string, final StringBuilder builder) {
        builder.append('"');
        builder.append(string);
        builder.append('"');
    }

    default void append(final StringBuilder builder, final Object... objects) {
        append(false, true, builder, objects);
    }

    default void append(final boolean appendNullable, final boolean addString, final StringBuilder builder, final Object... objects) {
        int i = 0;
        for (final Object object : objects) {
            if (object == null && !appendNullable) {
                if (++i != objects.length) {
                    builder.append(',');
                }
                continue;
            }
            if (object instanceof String string && addString) {
                appendString(string, builder);
            } else {
                builder.append(object);
            }
            if (++i != objects.length) {
                builder.append(',');
            }
        }
    }

    default String toFieldName(String string) {
        final int index = string.indexOf(':');
        if (index != -1) {
            string = string.substring(index+1);
        }
        return string.replace('.', '_').toUpperCase();
    }

    public static final record ParsedFile(
        String destination,
        String data
    ){}
}
