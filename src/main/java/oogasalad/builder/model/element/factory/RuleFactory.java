package oogasalad.builder.model.element.factory;

import java.util.Collection;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.Rule;

import java.util.Set;

public class RuleFactory extends GameElementFactory<Rule> {

    public RuleFactory() {
        super("elements.Rule");
    }

    @Override
    public Rule createElement(String name, Collection<Property> properties) {
        return new Rule(name, properties);
    }
}