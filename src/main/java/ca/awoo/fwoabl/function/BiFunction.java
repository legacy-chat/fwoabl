package ca.awoo.fwoabl.function;

/**
 * A function that takes in two arguments and returns a single value.
 * <p>
 * This is a functional interface whose functional method is {@link #invoke(Object, Object)}.
 * If you are using Java 8, you can use lambda expressions to represent the instance of this interface.
 * </p>
 */
public interface BiFunction<T, U, R> {
    /**
     * Applies this function to the given arguments.
     * @param t The first argument.
     * @param u The second argument.
     * @return The return value.
     */
    public R invoke(T t, U u);
}
