package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Class that represents the Actions Tab
 *
 * @author Ricky Weerts
 */
public class ActionsTab extends GameElementTab {

  public static String ACTION = "action";

  /**
   * Initializes the actions tab by calling the GameElementTab super class
   *
   * @param dispatcher - callbackDispatcher to communicate with controller
   */
  public ActionsTab(CallbackDispatcher dispatcher) {
    super(dispatcher, ACTION);
  }
}