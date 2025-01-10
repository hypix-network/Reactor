package dev.hypix.reactor.api.plugin.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dev.hypix.reactor.api.plugin.event.EventPriority;

@Target(ElementType.METHOD)
public @interface Listener {
    /*
     * If the event is canceled, ignore this fact and run the listener anyway
     */
    boolean ignoreCancelled() default false;

    /*
     * The lower the priority, the sooner it will be executed
     */
    EventPriority priority() default EventPriority.NORMAL;
}