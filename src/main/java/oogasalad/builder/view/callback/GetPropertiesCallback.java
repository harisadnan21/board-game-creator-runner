package oogasalad.builder.view.callback;

import oogasalad.builder.model.property.Property;

import java.util.Collection;

public record GetPropertiesCallback(String type) implements Callback<Collection<Property>> {

}
