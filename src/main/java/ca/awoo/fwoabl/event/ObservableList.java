package ca.awoo.fwoabl.event;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * An observable list.
 * <p>
 * Wraps an existing list and fires events when it is modified.
 * </p>
 * @see ObservableCollection
 * @see List
 * @param <T> The type of the list.
 */
public class ObservableList<T> extends ObservableCollection<T> implements List<T> {

    /**
     * Event arguments for a replace event.
     */
    public static class ReplaceEventArgs<T> extends CollectionEventArgs<T> {
        /**
         * The item that was replaced
         */
        public T oldItem;
        /**
         * The item that replaced it
         */
        public T newItem;

        /**
         * Create replace event arguments.
         * @param oldItem The item that was replaced
         * @param newItem The item that replaced it
         * @param collection The collection that was modified.
         */
        public ReplaceEventArgs(T oldItem, T newItem, Collection<T> collection){
            super(collection);
            this.oldItem = oldItem;
            this.newItem = newItem;
        }
    }

    /**
     * Create an observable list.
     * @param backingCollection The collection to wrap.
     */
    public ObservableList(List<T> backingCollection) {
        super(backingCollection);
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
     * Add all items in a collection to this collection.
     * <p>
     * Fires a CollectionEventArgs.Action.Add event for each item added.
     * </p>
     * <p>
     * Does not use the backing collection's {@link Collection#addAll(Collection)} method.
     * </p>
     * @param c The collection to add.
     */
    public boolean addAll(Collection<? extends T> c) {
        boolean result = false;
        for(T item : c){
            result |= add(item);
        }
        return result;
    }

    /**
     * Add all items in a collection to this collection at a specific index.
     * <p>
     * Fires a CollectionEventArgs.Action.Add event for each item added.
     * </p>
     * <p>
     * Does not use the backing collection's {@link Collection#addAll(Collection)} method.
     * </p>
     * @param index The index to add the items at.
     * @param c The collection to add.
     */
    public boolean addAll(int index, Collection<? extends T> c) {
        if(((List<T>)backingCollection).addAll(index, c)){
            for(T item : c){
                CollectionEventArgs<T> args = new AddEventArgs<T>(item, this);
                onChanged.invoke(args);
            }
            return true;
        }
        return false;
    }

    public T get(int index) {
        return ((List<T>)backingCollection).get(index);
    }

    /**
     * Replace an item in the collection.
     * <p>
     * Fires a CollectionEventArgs.Action.Replace event.
     * </p>
     * @param index The index of the item to replace.
     * @param element The item to replace it with.
     * @return The item that was replaced.
     */
    public T set(int index, T element) {
        T result = ((List<T>)backingCollection).set(index, element);
        CollectionEventArgs<T> args = new ReplaceEventArgs<T>(result, element, this);
        onChanged.invoke(args);
        return result;
    }

    /**
     * Add an item to the collection at a specific index.
     * <p>
     * Fires a CollectionEventArgs.Action.Add event.
     * </p>
     * @param index The index to add the item at.
     * @param element The item to add.
     */
    public void add(int index, T element) {
        ((List<T>)backingCollection).add(index, element);
        CollectionEventArgs<T> args = new AddEventArgs<T>(element, this);
        onChanged.invoke(args);
    }

    /**
     * Remove an item from the collection at a specific index.
     * <p>
     * Fires a CollectionEventArgs.Action.Remove event.
     * </p>
     * @param index The index to remove the item at.
     * @return The item that was removed.
     */
    public T remove(int index) {
        T result = ((List<T>)backingCollection).remove(index);
        CollectionEventArgs<T> args = new RemoveEventArgs<T>(result, this);
        onChanged.invoke(args);
        return result;
    }

    public int indexOf(Object o) {
        return ((List<T>)backingCollection).indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return ((List<T>)backingCollection).lastIndexOf(o);
    }

    public ListIterator<T> listIterator() {
        return ((List<T>)backingCollection).listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return ((List<T>)backingCollection).listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return ((List<T>)backingCollection).subList(fromIndex, toIndex);
    }
    
}
