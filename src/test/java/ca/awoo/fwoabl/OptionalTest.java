package ca.awoo.fwoabl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.awoo.fwoabl.function.Consumer;
import ca.awoo.fwoabl.function.Function;

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
        Optional<String> some = new Optional.Some<String>("Hello");
        assertTrue("isSome()", some.isSome());
        assertFalse("isNone()", some.isNone());
    }

    /**
     * Test the {@link Optional#none(Class)} method.
     * @see Optional#none(Class)
     */
    @Test
    public void testNone(){
        Optional<String> none = new Optional.None<String>();
        assertFalse("isSome()", none.isSome());
        assertTrue("isNone()", none.isNone());
    }

    /**
     * Test the {@link Optional#or(Object)} method.
     * @see Optional#or(Object)
     */
    @Test
    public void testOrObject(){
        Optional<String> some = new Optional.Some<String>("Hello");
        assertEquals("or()", "Hello", some.or("Goodbye"));
        Optional<String> none = new Optional.None<String>();
        assertEquals("or()", "Goodbye", none.or("Goodbye"));
    }

    
    /**
     * Test the {@link Optional#or(Optional)} method.
     * @see Optional#or(Optional)
     */
    @Test
    public void testOr(){
        Optional<String> some = new Optional.Some<String>("Hello");
        assertEquals("or()", some, some.or(new Optional.Some<String>("Goodbye")));
        assertEquals("or()", some, some.or(new Optional.None<String>()));
        Optional<String> none = new Optional.None<String>();
        assertEquals("or()", new Optional.Some<String>("Goodbye"), none.or(new Optional.Some<String>("Goodbye")));
        assertEquals("or()", new Optional.None<String>(),                none.or(new Optional.None<String>()));
    }

    /**
     * Test the {@link Optional#map(Function)} method.
     * @see Optional#map(Function)
     */
    @Test
    public void testMap(){
        Optional<String> some = new Optional.Some<String>("Hello");
        assertEquals("map()", new Optional.Some<Integer>(5), some.map(new Function<String, Integer>(){
            public Integer invoke(String s){
                return s.length();
            }
        }));
        Optional<String> none = new Optional.None<String>();
        assertEquals("map()", new Optional.None<Integer>(), none.map(new Function<String, Integer>(){
            public Integer invoke(String s){
                return s.length();
            }
        }));
    }

    /**
     * Test the {@link Optional#consume(Consumer)} method.
     * @see Optional#consume(Consumer)
     */
    @Test
    public void testConsume(){
        Optional<String> some = new Optional.Some<String>("Hello");
        final StringBuilder sb = new StringBuilder();
        some.consume(new Consumer<String>(){
            public void invoke(String s){
                sb.append(s);
            }
        });
        assertEquals("consume()", "Hello", sb.toString());
        Optional<String> none = new Optional.None<String>();
        none.consume(new Consumer<String>(){
            public void invoke(String s){
                sb.append(s);
            }
        });
        assertEquals("consume()", "Hello", sb.toString());
    }
    
}
