package oogasalad.builder.view.tab;


import oogasalad.builder.controller.BuilderController;

/**
 * Class that represents the Rules Tab
 */
public class RulesTab extends GameElementTab {
    public static String RULE = "rule";

    public RulesTab(BuilderController controller) {
        super(controller, RULE);
    }
}