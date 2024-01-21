package ca.awoo.fwoabl.function;

/**
 * A function that takes in a single argument and returns no value.
 * <p>
 * This is a functional interface whose functional method is {@link #invoke(Object)}.
 * If you are using Java 8, you can use lambda expressions to represent the instance of this interface.
 * </p>
 */
public interface Consumer<T> {
    /**
     * Applies this function to the given argument.
     * @param t The argument.
     */
    public void invoke(T t);
}
