package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Class that represents the Conditions Tab
 *
 * @author Ricky Weerts
 */
public class ConditionsTab extends GameElementTab {

  public static String CONDITION = "condition";

  /**
   * Initializes the conditions tab by calling the GameElementTab superclass
   *
   * @param dispatcher - callbackDispatcher to communicate with the controller
   */
  public ConditionsTab(CallbackDispatcher dispatcher) {
    super(dispatcher, CONDITION);
  }
}