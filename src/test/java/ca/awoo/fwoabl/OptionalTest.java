package ca.awoo.fwoabl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for {@link Optional}.
 */
public class OptionalTest {
    /**
     * Test the {@link Optional#some(Object)} method.
     * @see Optional#some(Object)
     */
    @Test
    public void testSome(){
        Optional<String> some = Optional.some("Hello");
        assertTrue("isSome()", some.isSome());
        assertFalse("isNone()", some.isNone());
        assertEquals("get()", "Hello", some.get());
    }

    /**
     * Test the {@link Optional#none(Class)} method.
     * @see Optional#none(Class)
     */
    @Test
    public void testNone(){
        Optional<String> none = Optional.none(String.class);
        assertFalse("isSome()", none.isSome());
        assertTrue("isNone()", none.isNone());
    }

    /**
     * Test the {@link Optional#getOrElse(Object)} method.
     * @see Optional#getOrElse(Object)
     */
    @Test
    public void testGetOrElse(){
        Optional<String> some = Optional.some("Hello");
        assertEquals("getOrElse()", "Hello", some.getOrElse("Goodbye"));
        Optional<String> none = Optional.none(String.class);
        assertEquals("getOrElse()", "Goodbye", none.getOrElse("Goodbye"));
    }

    /**
     * Test the {@link Optional#or(Optional)} method.
     * @see Optional#or(Optional)
     */
    @Test
    public void testOr(){
        Optional<String> some = Optional.some("Hello");
        assertEquals("or()", some, some.or(Optional.some("Goodbye")));
        assertEquals("or()", some, some.or(Optional.none(String.class)));
        Optional<String> none = Optional.none(String.class);
        assertEquals("or()", Optional.some("Goodbye"), none.or(Optional.some("Goodbye")));
        assertEquals("or()", Optional.none(String.class), none.or(Optional.none(String.class)));
    }

    /**
     * Test the {@link Optional#orElse(Object)} method.
     * @see Optional#orElse(Object)
     */
    @Test
    public void testOrElse(){
        Optional<String> some = Optional.some("Hello");
        assertEquals("orElse()", some, some.orElse("Goodbye"));
        assertEquals("orElse()", some, some.orElse(null));
        Optional<String> none = Optional.none(String.class);
        assertEquals("orElse()", Optional.some("Goodbye"), none.orElse("Goodbye"));
        assertEquals("orElse()", Optional.none(String.class), none.orElse(null));
    }
    
}
