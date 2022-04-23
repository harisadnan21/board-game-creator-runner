package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * @author Mike Keohane
 */
public class WinConditionsTab extends GameElementTab {

    public static final String WIN_CONDITION = "winCondition";
    /**
     * Default constructor
     */
    public WinConditionsTab(CallbackDispatcher callbackDispatcher) {
        super(callbackDispatcher, WIN_CONDITION);
    }


}