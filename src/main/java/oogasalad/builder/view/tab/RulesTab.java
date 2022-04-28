package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Class that represents the Rules Tab
 *
 * @author Ricky Weerts
 */
public class RulesTab extends GameElementTab {

  public static final String RULE = "rule";

  /**
   * initializes the RulesTab by calling the GameElementTab super class
   *
   * @param dispatcher - callbackDispatcher to communicate with the controller
   */
  public RulesTab(CallbackDispatcher dispatcher) {
    super(dispatcher, RULE);
  }
}