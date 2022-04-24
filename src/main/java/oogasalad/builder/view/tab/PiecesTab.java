package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * 
 */
public class PiecesTab extends GameElementTab {
    public static String PIECE = "piece";

    public PiecesTab(CallbackDispatcher dispatcher) {
        super(dispatcher, PIECE);
    }
}