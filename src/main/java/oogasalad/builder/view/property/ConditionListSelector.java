package oogasalad.builder.view.property;

import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

public class ConditionListSelector extends GameElementListSelector {
  protected ConditionListSelector(Property property, CallbackDispatcher dispatcher) {
    super(property, "condition", dispatcher);
  }
}
