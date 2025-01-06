package dev.hypix.reactor.api.entity;

public final class EntityUtils {

    public static final int UNIMPLEMENTED = Integer.MIN_VALUE;

    private static int ENTITY_ID_COUNTER = 0;

    public static int getNext() {
        return ENTITY_ID_COUNTER++;
    }
}
