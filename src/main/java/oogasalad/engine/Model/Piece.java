package oogasalad.engine.Model;

import java.util.*;

/**
 * Class that defines a single Piece that is created by user and placed on Board
 */
public class Piece {
  private PieceType pieceType;
  double PieceXCor;
  double PieceYCor;

  /**
   * Default constructor for Piece Class
   */
  public Piece(PieceType piecetype, double XCor, double YCor) {
    pieceType = piecetype;
    PieceXCor = XCor;
    PieceYCor = YCor;

  }
  /**
   * Changes Position of the Piece on the Board
   * @param X : x coordinate of new position of Piece
   * @param Y : y coordinate of new position of Piece
   */
  public void move(double X, double Y) throws Exception{
    PieceXCor = X;
    PieceYCor = Y;
  }

  /**
   * Changes the Piece's Type
   * @param: PieceType to change the Piece to
   */
  public void changeType(PieceType newPieceType) throws Exception{
    pieceType = newPieceType;
  }

}
