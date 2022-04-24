package oogasalad.builder.view.property;

import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * PropertySelector that lets users choose any number of Conditions and put them in a list
 * Users can delete Conditions with a button once they've been added to the list
 *
 * @author Ricky Weerts
 */
public class ConditionListSelector extends GameElementListSelector {

  /**
   * Creates a new ConditionListSelector
   *
   * @param property the property that will be "filled in" by the Field
   * @param dispatcher the callback dispatcher for getting data about game elements
   */
  protected ConditionListSelector(Property property, CallbackDispatcher dispatcher) {
    super(property, "condition", dispatcher);
  }
}
