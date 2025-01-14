package dev.hypix.reactor.api.entity;

public interface Entity {
    EntityType getType();

    default int getHealth() {
        return EntityUtils.UNIMPLEMENTED;
    }
}
