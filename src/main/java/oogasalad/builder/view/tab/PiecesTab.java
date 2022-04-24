package oogasalad.builder.view.tab;


import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Represents the Pieces Tab
 *
 * @author Ricky Weerts
 */
public class PiecesTab extends GameElementTab {

  public static String PIECE = "piece";

  /**
   * Initializes the PiecesTab by calling the GameElementTab super class
   *
   * @param dispatcher - callback dispatcher to communicate with the controller
   */
  public PiecesTab(CallbackDispatcher dispatcher) {
    super(dispatcher, PIECE);
  }
}