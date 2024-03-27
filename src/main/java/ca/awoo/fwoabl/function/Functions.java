package ca.awoo.fwoabl.function;

/**
 * A collection of common function types.
 * <p>
 * This is especially useful when working in Java 7 and below, where lambda expressions are not available to avoid lengthy inline anonymous classes.
 * </p>
 */
public final class Functions {
    private Functions(){}

    /**
     * A function that returns its argument.
     * @return A function that returns its argument.
     */
    public static <T> Function<T, T> identity(){
        return new Function<T, T>(){
            public T invoke(T arg){
                return arg;
            }
        };
    }

    /**
     * A predicate that tests if its argument is equal to the given value.
     * @param <T> The type of the argument.
     * @param value The value to compare against.
     * @return A predicate that tests if its argument is equal to the given value.
     */
    public static <T> Predicate<T> equals(final T value){
        return new Predicate<T>(){
            public boolean invoke(T arg){
                return value.equals(arg);
            }
        };
    }

    /**
     * A predicate that inverts the value of the given predicate
     * @param <T> The type of the argument.
     * @param p The predicate to invert.
     * @return A predicate that inverts the value of the given predicate.
     */
    public static <T> Predicate<T> not(final Predicate<T> p){
        return new Predicate<T>(){
            public boolean invoke(T arg){
                return !p.invoke(arg);
            }
        };
    }

    /**
     * A predicate that returns true if and only if both of the given predicates return true.
     * @param <T> The type of the argument.
     * @param p1 The first predicate.
     * @param p2 The second predicate.
     * @return A predicate that returns true if and only if both of the given predicates return true.
     */
    public static <T> Predicate<T> and(final Predicate<T> p1, final Predicate<T> p2){
        return new Predicate<T>(){
            public boolean invoke(T arg){
                return p1.invoke(arg) && p2.invoke(arg);
            }
        };
    }

    /**
     * A predicate that returns true if any of the given predicates return true.
     * @param <T> The type of the argument.
     * @param p1 The first predicate.
     * @param p2 The second predicate.
     * @return A predicate that returns true if any of the given predicates return true.
     */
    public static <T> Predicate<T> or(final Predicate<T> p1, final Predicate<T> p2){
        return new Predicate<T>(){
            public boolean invoke(T arg){
                return p1.invoke(arg) || p2.invoke(arg);
            }
        };
    }

    /**
     * A supplier that always returns the given value.
     * @param <T> The type of the value.
     * @param value The value to return.
     * @return A supplier that always returns the given value.
     */
    public static <T> Supplier<T> constant(final T value){
        return new Supplier<T>(){
            public T invoke(){
                return value;
            }
        };
    }
}
