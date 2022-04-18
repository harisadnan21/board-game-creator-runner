package oogasalad.builder.view.property;

import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

public class ActionListSelector extends GameElementListSelector {
  protected ActionListSelector(Property property, CallbackDispatcher dispatcher) {
    super(property, "action", dispatcher);
  }
}
