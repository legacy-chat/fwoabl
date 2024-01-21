package ca.awoo.fwoabl;

/**
 * A simple implementation of the Pair type.
 * @param <T1> The type of the first value.
 * @param <T2> The type of the second value.
 */
public class Pair<T1, T2> {
    /**
     * The first value.
     */
    public T1 first;

    /**
     * The second value.
     */
    public T2 second;

    /**
     * Create a new Pair with the given values.
     * @param first The first value.
     * @param second The second value.
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}
