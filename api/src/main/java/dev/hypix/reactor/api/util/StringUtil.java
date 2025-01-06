package dev.hypix.reactor.api.util;

import java.util.LinkedList;
import java.util.List;

public final class StringUtil {

    private StringUtil() {
        throw new IllegalAccessError("Util class can't be instance");
    }

    public static List<String> split(final String text, final char character) {
        final List<String> list = new LinkedList<>();
        int start = 0;
        int index;
        while ((index = text.indexOf(character, start)) != -1) {
            list.add(text.substring(start, index));
            start = index + 1;
        }
        if (start == 0) {
            list.add(text);
        } else {
            list.add(text.substring(start));
        }
        return list;
    }
}