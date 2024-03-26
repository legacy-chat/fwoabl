package ca.awoo.fwoabl.function;

/**
 * A function that takes no arguments and returns a single value.
 * <p>
 * This is a functional interface whose functional method is {@link #invoke()}.
 * If you are using Java 8, you can use lambda expressions to represent the instance of this interface.
 * </p>
 * @param <T> The type of the return value.
 */
public interface Supplier<T> {
    /**
     * Gets the value supplied by this function.
     * @return The return value.
     */
    public T invoke();
}
