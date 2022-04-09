package oogasalad.engine.model.board;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import oogasalad.engine.model.OutOfBoardException;
import io.vavr.collection.SortedMap;
import io.vavr.collection.TreeMap;

public class PersistentMapBoard implements Board {

  private SortedMap<Position, Piece> myPieces;

  private int myWidth;
  private int myHeight;

  public PersistentMapBoard(int rows, int columns) {
    myPieces = TreeMap.of(new Position(0, 0), new Piece(0,0));
    myPieces = myPieces.put(new Position(0, 1), new Piece(0,0));
  }

  @Override
  public void placeNewPiece(int row, int column, int type, int player) {
    try {
      PersistentMapBoard newBoard = (PersistentMapBoard) this.clone();
      newBoard.myPieces = myPieces.put(new Position(row, column), new Piece(0,0));
    } catch(Exception e) {
      throw new RuntimeException("");
    }
  }

  @Override
  public void remove(int i, int j) {

  }

  @Override
  public boolean isEmpty(int i, int j) {

    return false;
  }

  @Override
  public Optional<Piece> getPieceRecord(int i, int j) {
    return Optional.empty();
  }

  @Override
  public void move(int i1, int j1, int i2, int j2) {

  }

  @Override
  public void setPlayer(int player) {

  }

  @Override
  public int getPlayer() {
    return 0;
  }

  @Override
  public boolean isValidPosition(int i, int j) {
    return false;
  }

  @Override
  public boolean isValidPosition(Position position) {
    return false;
  }

  @Override
  public ArrayBoard deepCopy() throws OutOfBoardException {
    return null;
  }

  @Override
  public void setValidMoves(Set<Position> validMoves) {

  }

  @Override
  public Set<Position> getValidMoves() {
    return null;
  }

  @Override
  public void setWinner(int winner) {

  }

  @Override
  public int getWinner() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public Iterator<PositionState> iterator() {
    return null;
  }
}
