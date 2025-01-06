package dev.hypix.reactor.api.command;

import dev.hypix.reactor.api.chat.ChatComponent;
import dev.hypix.reactor.api.chat.ChatLegacy;

public interface CommandSender {
    void send(final ChatComponent[] components);
    void send(final ChatComponent component);

    default void send(final String message) {
        send(ChatLegacy.from(message));
    }
    default void sendRaw(final String message) {
        send(new ChatComponent(message));
    }

    String getName();
}
