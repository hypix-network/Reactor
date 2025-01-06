package dev.hypix.reactor.api.entity;

public interface Entity {
    int getType();

    default int getHealth() {
        return EntityUtils.UNIMPLEMENTED;
    }
}
