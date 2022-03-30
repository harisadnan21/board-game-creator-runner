package oogasalad.builder.model.element.factory;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.WinCondition;

import java.util.*;

public class WinConditionFactory extends GameElementFactory {

    public WinConditionFactory() {
        super("/elements/WinCondition.properties");
    }

    @Override
    public WinCondition createElement(String name, Collection<Property> properties) {
        return new WinCondition(name, properties);
    }
}