package oogasalad.builder.controller;

import java.util.*;

/**
 * 
 */
public class Property<T> {
    private Class<T> type;

    private String name;

    private T value;

    public Property(Class<T> type, String name) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T newValue) {
        Objects.requireNonNull(newValue);
        if(!type.equals(newValue.getClass())) {
            throw new IllegalArgumentException(ExceptionResourcesSingleton.getInstance().getString("BadPropertyValueType"));
        }
        value = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Property<?> property = (Property<?>) o;
        return type.equals(property.type) && name.equals(property.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}