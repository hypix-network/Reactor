package dev.hypix.reactor.api.nbt;

public abstract class NumericTag extends TagNBT {

    public NumericTag(final String key) {
        super(key);
    }

    public abstract byte toByte();
    public abstract short toShort();
    public abstract float toFloat();
    public abstract double toDouble();
    public abstract int toInt();
    public abstract long toLong();
}
