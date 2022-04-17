package oogasalad.builder.view.callback;

import oogasalad.builder.model.property.Property;

import java.util.Collection;

public record UpdateGameElementCallback(String type, String name, Collection<Property> properties) implements Callback<Void> {
}
