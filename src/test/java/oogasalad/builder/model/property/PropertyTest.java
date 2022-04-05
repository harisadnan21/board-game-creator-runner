package oogasalad.builder.model.property;

import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {
    private final Property prop1 = new Property(String.class, "field name", "some value"),
            prop2 = new Property(String.class, "field name", "other value"),
            prop3 = new Property(String.class, "different field name", "some value"),
            prop4 = new Property(Integer.class, "different field name", "123");

    @Test
    void testTypeChecking() {
        assertThrows(IllegalArgumentException.class, () -> prop1.withValue(123));
        assertThrows(IllegalArgumentException.class, () -> prop4.withValue("123"));
        assertThrows(IllegalArgumentException.class, () -> prop4.withValue("hello"));
    }

    @Test
    void testNewInstance() {
        assertFullEquals(new Property(String.class, "test", "testVal"), Property.newInstance(String.class, "test", "testVal"));
        assertFullEquals(new Property(Integer.class, "test", "123"), Property.newInstance(Integer.class, "test", 123));
    }

    @Test
    void testWithValue() {
        assertEquals("good value", prop1.withValue("good value").value());
        assertEquals(prop1, prop1.withValue("good value"));
        assertEquals("999", prop4.withValue(999).value());
        assertEquals(prop4, prop4.withValue(999));
        assertEquals("999", prop4.withValueAsString("999").value());
        assertEquals(prop4, prop4.withValueAsString("999"));
        assertFullEquals(prop2, prop1.withValue("other value"));
    }

    @Test
    void testEquals() {
        assertEquals(prop1, prop1);
        assertEquals(prop1, prop2);
        assertNotEquals(prop1, prop3);
        assertNotEquals(prop2, prop3);
        assertNotEquals(prop1, prop4);
        assertNotEquals(prop2, prop4);
        assertNotEquals(prop3, prop4);
    }

    @Test
    void testHashCode() {
        assertEquals(prop1.hashCode(), prop1.hashCode());
        assertEquals(prop1.hashCode(), prop2.hashCode());
        assertNotEquals(prop1.hashCode(), prop3.hashCode());
        assertNotEquals(prop2.hashCode(), prop3.hashCode());
        assertNotEquals(prop1.hashCode(), prop4.hashCode());
        assertNotEquals(prop2.hashCode(), prop4.hashCode());
        assertNotEquals(prop3.hashCode(), prop4.hashCode());
    }

    @Test
    void testFullEquals() {
        assertFullEquals(prop1, prop1);
        assertNotFullEquals(prop1, prop2);
        assertNotFullEquals(prop1, prop3);
        assertNotFullEquals(prop2, prop3);
        assertNotFullEquals(prop1, prop4);
        assertNotFullEquals(prop2, prop4);
        assertNotFullEquals(prop3, prop4);
    }

    void assertFullEquals(Property expected, Property actual) {
        if(!expected.fullEquals(actual)) {
            fail("\nExpected\t:" + expected + "\nActual\t:" + actual);
        }
    }

    void assertNotFullEquals(Property expected, Property actual) {
        if(expected.fullEquals(actual)) {
            fail("Expected not full equals but was " + actual);
        }
    }
}
