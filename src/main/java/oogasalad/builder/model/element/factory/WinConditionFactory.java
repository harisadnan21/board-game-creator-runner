package oogasalad.builder.model.element.factory;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.WinCondition;

import java.util.*;

public class WinConditionFactory extends GameElementFactory<WinCondition> {

    public WinConditionFactory() {
        super("elements.WinCondition");
    }

    @Override
    public WinCondition createElement(String name, Collection<Property> properties) {
        return new WinCondition(name, properties);
    }
}