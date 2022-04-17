package oogasalad.builder.view.callback;

import oogasalad.builder.model.property.Property;

import java.util.Collection;

public record GetElementPropertiesCallback(String type, String name) implements Callback<Collection<Property>> {

}
