package dev.hypix.reactor.internal.plugin.listener.executor;

import java.lang.invoke.MethodHandle;

import org.tinylog.Logger;

import dev.hypix.reactor.api.plugin.event.Cancellable;
import dev.hypix.reactor.api.plugin.listener.EventExecutor;
import dev.hypix.reactor.api.plugin.listener.Listener;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListenerReflectionExecutor implements EventExecutor {
    private final Listener listener;
    private final MethodHandle methodHandle;

    @Override
    public void handle(final Object event) {
        if (event instanceof Cancellable cancellable && cancellable.isCancelled() && !listener.ignoreCancelled()) {
            return;
        }
        try {
            methodHandle.invoke(event);
        } catch (Throwable e) {
            Logger.error(e);
        }
    }
}
