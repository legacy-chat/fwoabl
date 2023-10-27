package ca.awoo.fwoabl.event;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple event system.
 * @param <T> The type of event to be passed to listeners.
 */
public class Event<T> {
    /**
     * A listener for an event.
     */
    public static interface Listener<T> {
        /**
         * Called when an event is invoked.
         * @param args The event arguments.
         */
        public void onEvent(T args);
    }

    private Set<Listener<T>> listeners = Collections.synchronizedSet(new HashSet<Listener<T>>());

    /**
     * Add a listener to this event.
     * @param listener The listener to add.
     */
    public void add(Listener<T> listener) {
        listeners.add(listener);
    }

    /**
     * Remove a listener from this event.
     * @param listener The listener to remove.
     */
    public void remove(Listener<T> listener) {
        listeners.remove(listener);
    }

    /**
     * Invoke this event.
     * @param args The event arguments.
     */
    public void invoke(T args) {
        synchronized(listeners){
            for (Listener<T> listener : listeners) {
                listener.onEvent(args);
            }
        }
    }
}
