package oogasalad.builder.view.tab.pieceTab;


import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.tab.GameElementTab;

/**
 * 
 */
public class PiecesTab extends GameElementTab {
    public static String PIECE = "piece";

    public PiecesTab(BuilderController controller, CallbackDispatcher dispatcher) {
        super(controller, dispatcher, PIECE);
    }
}