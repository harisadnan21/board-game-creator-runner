package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Class that represents the Conditions Tab
 */
public class ConditionsTab extends GameElementTab {
    public static String CONDITION = "condition";

    public ConditionsTab(CallbackDispatcher dispatcher) {
        super(dispatcher, CONDITION);
    }
}