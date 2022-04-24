package oogasalad.builder.view.property;

import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * PropertySelector that lets users choose any number of Actions and put them in a list
 * Users can delete Actions with a button once they've been added to the list
 *
 * @author Ricky Weerts
 */
public class ActionListSelector extends GameElementListSelector {
  /**
   * Creates a new ActionListSelector
   *
   * @param property the property that will be "filled in"
   * @param dispatcher the callback dispatcher for getting data about game elements
   */
  public ActionListSelector(Property property, CallbackDispatcher dispatcher) {
    super(property, "action", dispatcher);
  }
}
