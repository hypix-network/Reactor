package dev.hypix.reactor.api.plugin;

public abstract class Plugin {

    public static final byte
        DISABLED = 0,
        LOADED = 1,
        ENABLED = 2;

    private byte state = DISABLED;

    protected void onLoad() {}
    protected void onEnable() {}
    protected void onDisable() {}

    public boolean load() {
        if (state != DISABLED) {
            return false;
        }
        onLoad();
        state = LOADED;
        return true;
    }

    public boolean enable() {
        if (state != LOADED) {
            return false;
        }
        onEnable();
        state = ENABLED;
        return true;
    }

    public boolean disable() {
        if (state != ENABLED) {
            return false;
        }
        onDisable();
        state = DISABLED;
        return true;
    }

    public int getState() {
        return state;
    }

    public abstract String getName();

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Plugin otherPlugin && otherPlugin.getName().equals(this.getName()));
    }
}