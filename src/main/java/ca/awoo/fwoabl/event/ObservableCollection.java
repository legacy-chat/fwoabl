package ca.awoo.fwoabl.event;

import java.util.Collection;
import java.util.Iterator;

/**
 * An observable collection.
 * <p>
 * Wraps an existing collection and fires events when it is modified.
 * </p>
 * @param <T> The type of the collection.
 */
public class ObservableCollection<T> implements Collection<T> {

    protected Collection<T> backingCollection;

    /**
     * Event arguments for collection events.
     */
    public static class CollectionEventArgs<T>{
        /**
         * The collection that was modified.
         */
        public Collection<T> collection;

        /**
         * Create collection event arguments.
         * @param collection The collection that was modified.
         */
        public CollectionEventArgs(Collection<T> collection){
            this.collection = collection;
        }
    }

    /**
     * Event arguments for an add event.
     */
    public static class AddEventArgs<T> extends CollectionEventArgs<T>{
        /**
         * The item that was added.
         */
        public T added;

        /**
         * Create add event arguments.
         * @param added The item that was added.
         * @param collection The collection that was modified.
         */
        public AddEventArgs(T added, Collection<T> collection){
            super(collection);
            this.added = added;
        }
    }

    /**
     * Event arguments for a remove event.
     */
    public static class RemoveEventArgs<T> extends CollectionEventArgs<T>{
        /**
         * The item that was removed.
         */
        public T removed;

        /**
         * Create remove event arguments.
         * @param removed The item that was removed.
         * @param collection The collection that was modified.
         */
        public RemoveEventArgs(T removed, Collection<T> collection){
            super(collection);
            this.removed = removed;
        }
    }

    /**
     * Event arguments for a reset event.
     */
    public static class ResetEventArgs<T> extends CollectionEventArgs<T>{
        /**
         * Create reset event arguments.
         * @param collection The collection that was modified.
         */
        public ResetEventArgs(Collection<T> collection){
            super(collection);
        }
    }

    /**
     * Fired when the collection is modified.
     */
    public final Event<CollectionEventArgs<T>> onChanged = new Event<CollectionEventArgs<T>>();

    /**
     * Create an observable collection.
     * @param backingCollection The collection to wrap.
     */
    public ObservableCollection(Collection<T> backingCollection) {
        this.backingCollection = backingCollection;
    }

    public int size() {
        return backingCollection.size();
    }

    public boolean isEmpty() {
        return backingCollection.isEmpty();
    }

    public boolean contains(Object o) {
        return backingCollection.contains(o);
    }

    public Iterator<T> iterator() {
        return backingCollection.iterator();
    }

    public Object[] toArray() {
        return backingCollection.toArray();
    }

    public <TA> TA[] toArray(TA[] a) {
        return backingCollection.toArray(a);
    }

    /**
     * Add an item to the collection.
     * <p>
     * Fires a CollectionEventArgs.Action.Add event.
     * </p>
     * @param o The item to add.
     */
    public boolean add(T o) {
        boolean result = backingCollection.add(o);
        if(result){
            CollectionEventArgs<T> args = new AddEventArgs<T>(o, this);
            onChanged.invoke(args);
        }
        return result;
    }

    /**
     * Remove an item from the collection.
     * <p>
     * Fires a CollectionEventArgs.Action.Remove event.
     * </p>
     * @param o The item to remove.
     */
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        boolean result = backingCollection.remove(o);
        if(result){
            CollectionEventArgs<T> args = new RemoveEventArgs<T>((T)o, this);
            onChanged.invoke(args);
        }
        return result;
    }

    public boolean containsAll(Collection<?> c) {
        return backingCollection.containsAll(c);
    }

    /**
     * Add all items in a collection to this collection.
     * <p>
     * Fires a CollectionEventArgs.Action.Add event for each item added.
     * </p>
     * <p>
     * Does not use the backing collection's {@link Collection#addAll(Collection)} method.
     * </p>
     * @param c The collection of items to add.
     */
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for(Object item : c){
            if(backingCollection.add((T)c)){
                modified = true;
                CollectionEventArgs<T> args = new AddEventArgs<T>((T)item, this);
                onChanged.invoke(args);
            }
        }
        return modified;
    }

    /**
     * Remove all this collection's items that are also in the given collection.
     * <p>
     * Fires a CollectionEventArgs.Action.Remove event for each item removed.
     * </p>
     * <p>
     * Does not use the backing collection's {@link Collection#removeAll(Collection)} method.
     * </p>
     * @param c The collection of items to remove.
     */
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for(Object item : c){
            if(backingCollection.remove(c)){
                modified = true;
                CollectionEventArgs<T> args = new RemoveEventArgs<T>((T)item, this);
                onChanged.invoke(args);
            }
        }
        return modified;
    }

    /**
     * Retain only items in this collection that are also in the given collection.
     * <p>
     * Fires a CollectionEventArgs.Action.Reset event.
     * </p>
     * @param c The collection of items to retain.
     */
    public boolean retainAll(Collection<?> c) {
        boolean result = backingCollection.retainAll(c);
        if(result){
            CollectionEventArgs<T> args = new ResetEventArgs<T>(this);
            onChanged.invoke(args);
        }
        return result;
    }

    /**
     * Clear the collection.
     * <p>
     * Fires a CollectionEventArgs.Action.Reset event.
     * </p>
     */
    public void clear() {
        backingCollection.clear();
        CollectionEventArgs<T> args = new ResetEventArgs<T>(this);
        onChanged.invoke(args);
    }
    
}
