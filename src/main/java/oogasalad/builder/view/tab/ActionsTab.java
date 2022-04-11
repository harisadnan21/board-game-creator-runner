package oogasalad.builder.view.tab;


import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Class that represents the Actions Tab
 */
public class ActionsTab extends GameElementTab {
    public static String ACTION = "action";

    public ActionsTab(BuilderController controller, CallbackDispatcher dispatcher) {
        super(controller, dispatcher, ACTION);
    }
}