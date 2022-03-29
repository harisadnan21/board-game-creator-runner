package oogasalad.builder.model.element.factory;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.Rule;

import java.util.Set;

public class RuleFactory extends GameElementFactory<Rule> {

    public RuleFactory() {
        super("/elements/Rule.properties");
    }

    @Override
    public Rule createElement(String name, Set<Property> properties) {
        return new Rule(name, properties);
    }
}