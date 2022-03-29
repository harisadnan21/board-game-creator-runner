package oogasalad.builder.model.element.factory;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.WinCondition;

import java.util.*;

public class WinConditionFactory extends GameElementFactory<WinCondition> {

    public WinConditionFactory() {
        super("/elements/WinCondition.properties");
    }

    @Override
    public WinCondition createElement(String name, Set<Property> properties) {
        return new WinCondition(name, properties);
    }
}