package ca.awoo.fwoabl.event;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test the Event class.
 * @see Event
 */
public class EventTest {
    /**
     * A listener for an event.
     */
    public class listener implements Event.Listener<String> {
        public String lastEvent;
        public void onEvent(String args) {
            lastEvent = args;
        }
    }

    /**
     * Test that an event is recieved by a listener.
     */
    @Test
    public void testEvent() {
        Event<String> event = new Event<String>();
        listener listener = new listener();
        event.add(listener);
        event.invoke("test");
        assertEquals("Class recieved event", "test", listener.lastEvent);
    }
    
}
