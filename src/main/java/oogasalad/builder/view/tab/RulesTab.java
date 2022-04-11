package oogasalad.builder.view.tab;


import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Class that represents the Rules Tab
 */
public class RulesTab extends GameElementTab {
    public static String RULE = "rule";

    public RulesTab(BuilderController controller, CallbackDispatcher dispatcher) {
        super(controller, dispatcher, RULE);
    }
}