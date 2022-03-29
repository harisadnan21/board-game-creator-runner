package oogasalad.builder.controller;

import java.util.*;

/**
 * An immutable class that stores a value of a specified type as a String under a given name
 */
public record Property(Class<?> type, String name, String value) {

    public <T> Property(Class<T> type, String name, T value) {
        this(type, name, Objects.requireNonNull(value).toString());
    }

    public Property withValue(Object newValue) {
        return withValueAsString(Objects.requireNonNull(newValue).toString());
    }

    public Property withValueAsString(String newValue) {
        Objects.requireNonNull(newValue);
        return new Property(type, name, newValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Property property = (Property) o;
        return type.equals(property.type) && name.equals(property.name);
    }

    public boolean fullEquals(Object o) {
        return equals(o) && ((Property)o).value.equals(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}