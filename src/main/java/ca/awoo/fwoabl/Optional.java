package ca.awoo.fwoabl;

/**
 * A simple implementation of the Option type.
 */
public class Optional<T> {
    private final T value;

    /**
     * Create a new Optional with the given value.
     * @param value The value to wrap.
     */
    public Optional(T value) {
        this.value = value;
    }

    /**
     * Create a new Optional with the given value.
     * @param <T> The type of the value.
     * @param value The value to wrap.
     * @return A new Optional wrapping the given value.
     */
    public static <T> Optional<T> some(T value){
        return new Optional<T>(value);
    }

    /**
     * Create a new Optional with no value.
     * @param <T> The type of the value.
     * @param clazz The class of the value. Not used, but required for Java generics.
     * @return A new Optional with no value.
     */
    public static <T> Optional<T> none(Class<T> clazz){
        return new Optional<T>(null);
    }

    /**
     * Check if this Optional has a value.
     * @return True if this Optional has a value, false otherwise.
     */
    public boolean isSome(){
        return value != null;
    }

    /**
     * Check if this Optional has no value.
     * @return True if this Optional has no value, false otherwise.
     */
    public boolean isNone(){
        return value == null;
    }

    /**
     * Get the value of this Optional.
     * @return The value of this Optional.
     */
    public T get(){
        return value;
    }

    /**
     * Get the value of this Optional, or the given default value if this Optional has no value.
     * @param defaultValue The default value to return if this Optional has no value.
     * @return The value of this Optional, or the given default value if this Optional has no value.
     */
    public T getOrElse(T defaultValue){
        if (isSome()){
            return value;
        } else {
            return defaultValue;
        }
    }

    /**
     * Get the value of this Optional, or the value of the given Optional if this Optional has no value.
     * @param other The Optional to use if this Optional has no value.
     * @return The value of this Optional, or the value of the given Optional if this Optional has no value.
     */
    public Optional<T> or(Optional<T> other){
        if (isSome()){
            return this;
        } else {
            return other;
        }
    }

    /**
     * Get the value of this Optional, or the given default value if this Optional has no value.
     * @param defaultValue The default value to return if this Optional has no value.
     * @return The value of this Optional, or the given default value if this Optional has no value.
     */
    public Optional<T> orElse(T defaultValue){
        if (isSome()){
            return this;
        } else {
            return new Optional<T>(defaultValue);
        }
    }

    /**
     * Check if this Optional is equal to the given object.
     * @param other The object to compare to.
     * @return True if this Optional is equal to the given object, false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Optional){
            Optional<?> otherOptional = (Optional<?>)other;
            if (isSome() && otherOptional.isSome()){
                return value.equals(otherOptional.value);
            } else if (isNone() && otherOptional.isNone()){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Get the hash code of this Optional.
     * @return The hash code of this Optional.
     */
    @Override
    public int hashCode(){
        if (isSome()){
            return value.hashCode();
        } else {
            return 0;
        }
    }

    /**
     * Get a string representation of this Optional.
     * @return A string representation of this Optional.
     */
    @Override
    public String toString(){
        if (isSome()){
            return "Some(" + value.toString() + ")";
        } else {
            return "None";
        }
    }
}
