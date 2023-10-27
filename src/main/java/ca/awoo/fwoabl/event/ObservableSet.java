package ca.awoo.fwoabl.event;

import java.util.Set;

/**
 * An observable set.
 * <p>
 * Wraps an existing set and fires events when it is modified.
 * </p>
 * @see ObservableCollection
 * @see Set
 * @param <T> The type of the set.
 */
public class ObservableSet<T> extends ObservableCollection<T> implements Set<T>{

    /**
     * Create an observable set.
     * @param backingCollection The collection to wrap.
     */
    public ObservableSet(Set<T> backingCollection) {
        super(backingCollection);
    }
    
}
