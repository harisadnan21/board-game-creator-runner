package oogasalad.engine.Model;
import java.util.*;



/**
 * Class that defines the Board for the Game that will be displayed
 */
public class Board {
  private List<PieceRecord> listOfPieces;

  /**
   * Default constructor for the Board. Contains list of all the Pieces contained in the board
   * as Records.
   */
  public Board(List<PieceRecord> PiecesRecords) {
    this.listOfPieces = PiecesRecords;
  }

}
