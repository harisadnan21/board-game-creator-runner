package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Represents the win conditions tab
 *
 * @author Mike Keohane
 */
public class WinConditionsTab extends GameElementTab {

  public static final String WIN_CONDITION = "winCondition";

  /**
   * Initializes the win conditions by calling the GameElementTab superclass
   *
   * @param callbackDispatcher - CallbackDispatcher to communicate with the controller
   */
  public WinConditionsTab(CallbackDispatcher callbackDispatcher) {
    super(callbackDispatcher, WIN_CONDITION);
  }


}