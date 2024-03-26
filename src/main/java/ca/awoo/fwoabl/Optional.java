package ca.awoo.fwoabl;

import ca.awoo.fwoabl.function.Consumer;
import ca.awoo.fwoabl.function.Function;

/**
 * A simple implementation of the Option type.
 */
public interface Optional<T> {
    public static class Some<T> implements Optional<T>{
        private final T value;

        public Some(T value){
            this.value = value;
        }

        public boolean isSome() {
            return true;
        }

        public boolean isNone() {
            return false;
        }

        public T or(T defaultValue) {
            return value;
        }

        public Optional<T> or(Optional<T> other) {
            return this;
        }

        public <U> Optional<U> map(Function<T, U> f) {
            return new Some<U>(f.invoke(value));
        }

        public void consume(Consumer<T> c) {
            c.invoke(value);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Some<?> other = (Some<?>) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Some(" + value.toString() + ")";
        }
    }

    public static class None<T> implements Optional<T> {

        public None(){}

        public boolean isSome() {
            return false;
        }

        public boolean isNone() {
            return true;
        }

        public T or(T defaultValue) {
            return defaultValue;
        }

        public Optional<T> or(Optional<T> other) {
            return other;
        }

        public <U> Optional<U> map(Function<T, U> f) {
            return new None<U>();
        }

        public void consume(Consumer<T> c) {
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof None;
        }

        @Override
        public String toString() {
            return "None";
        }
    }

    /**
     * Check if this Optional has a value.
     * @return True if this Optional has a value, false otherwise.
     */
    public boolean isSome();

    /**
     * Check if this Optional has no value.
     * @return True if this Optional has no value, false otherwise.
     */
    public boolean isNone();

    /**
     * Get the value of this Optional, or the given default value if this Optional has no value.
     * @param defaultValue The default value to return if this Optional has no value.
     * @return The value of this Optional, or the given default value if this Optional has no value.
     */
    public T or(T defaultValue);

    /**
     * Get the value of this Optional, or the value of the given Optional if this Optional has no value.
     * @param other The Optional to use if this Optional has no value.
     * @return The value of this Optional, or the value of the given Optional if this Optional has no value.
     */
    public Optional<T> or(Optional<T> other);

    /**
     * Map the value of this Optional to a new value.
     * @param <U> The type of the new value.
     * @param f The function to apply to the value of this Optional.
     * @return An Optional containing the new value, or None if this Optional has no value.
     */
    public <U> Optional<U> map(Function<T, U> f);

    /**
     * Consume the value of this Optional, if it has one.
     * @param c The function to apply to the value of this Optional.
     */
    public void consume(Consumer<T> c);

}
