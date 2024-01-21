package ca.awoo.fwoabl.function;

/**
 * A function that takes in two arguments and returns no value.
 * <p>
 * This is a functional interface whose functional method is {@link #invoke(Object, Object)}.
 * If you are using Java 8, you can use lambda expressions to represent the instance of this interface.
 * </p>
 */
public interface BiConsumer<T, U> {
    /**
     * Applies this function to the given arguments.
     * @param t The first argument.
     * @param u The second argument.
     */
    public void invoke(T t, U u);
}
