package oogasalad.builder.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {
    private final Property prop1 = new Property(String.class, "field name", "some value"),
            prop2 = new Property(String.class, "field name", "other value"),
            prop3 = new Property(String.class, "different field name", "some value"),
            prop4 = new Property(Integer.class, "different field name", "123");

    @Test
    void equals() {
        assertEquals(prop1, prop1);
        assertEquals(prop1, prop2);
        assertNotEquals(prop1, prop3);
        assertNotEquals(prop2, prop3);
        assertNotEquals(prop1, prop4);
        assertNotEquals(prop2, prop4);
        assertNotEquals(prop3, prop4);
    }

    @Test
    void fullEquals() {
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
