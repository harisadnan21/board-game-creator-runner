package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Class that represents the Actions Tab
 */
public class ActionsTab extends GameElementTab {
    public static String ACTION = "action";

    public ActionsTab(CallbackDispatcher dispatcher) {
        super(dispatcher, ACTION);
    }
}