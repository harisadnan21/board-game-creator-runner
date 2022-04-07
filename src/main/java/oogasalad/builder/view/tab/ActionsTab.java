package oogasalad.builder.view.tab;


import oogasalad.builder.controller.BuilderController;

/**
 * Class that represents the Actions Tab
 */
public class ActionsTab extends GameElementTab {
    public static String ACTION = "action";

    public ActionsTab(BuilderController controller) {
        super(controller, ACTION);
    }
}