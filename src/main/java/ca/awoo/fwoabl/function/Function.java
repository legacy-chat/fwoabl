package ca.awoo.fwoabl.function;

/**
 * A function that takes in a single argument and returns a single value.
 * <p>
 * This is a functional interface whose functional method is {@link #invoke(Object)}.
 * If you are using Java 8, you can use lambda expressions to represent the instance of this interface.
 * </p>
 * @param <T> The type of the argument.
 * @param <R> The type of the return value.
 */
public interface Function<T, R> {
    /**
     * Applies this function to the given argument.
     * @param t The argument.
     * @return The return value.
     */
    public R invoke(T t);
}
