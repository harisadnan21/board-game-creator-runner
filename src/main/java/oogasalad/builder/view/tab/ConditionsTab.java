package oogasalad.builder.view.tab;


import oogasalad.builder.controller.BuilderController;

/**
 * Class that represents the Conditions Tab
 */
public class ConditionsTab extends GameElementTab {
    public static String CONDITION = "condition";

    public ConditionsTab(BuilderController controller) {
        super(controller, CONDITION);
    }
}