package dev.hypix.reactor.internal.plugin.listener;

import dev.hypix.reactor.api.plugin.Plugin;
import dev.hypix.reactor.api.plugin.listener.EventExecutor;
import dev.hypix.reactor.api.plugin.listener.Listener;

public record RegisteredListener(
    Class<?> sourceClass,
    Plugin plugin,
    Listener listener,
    EventExecutor eventExecutor,
    Class<?> eventClass
) {}