package dev.hypix.reactor.api.plugin;

import java.io.File;
import java.util.Collection;
import java.util.function.Consumer;

import dev.hypix.reactor.api.plugin.event.EventPriority;
import dev.hypix.reactor.api.plugin.listener.EventExecutor;

public interface PluginManager {
    void registerListeners(final Plugin plugin, final Object... listeners);
    void registerListeners(final Plugin plugin, final EventExecutor executor, final Object... listeners);

    <T> void registerListener(final Plugin plugin, final EventExecutor executor, final EventPriority priority, final boolean ignoreCancelled, final Class<T> clazz, final Consumer<T> consumer);
    <T> void registerListener(final Plugin plugin, final Class<T> clazz, final Consumer<T> consumer);

    void unloadListener(final Object listener);
    void unloadListeners(final Plugin plugin);

    void unloadPlugins();
    void enablePlugins();

    void loadPlugins(final File directory);
    void callEvent(Object event);

    Plugin getPlugin(final String name);
    <T extends Plugin> T getPlugin(final Class<T> pluginClass);

    Collection<Plugin> getPlugins();
}