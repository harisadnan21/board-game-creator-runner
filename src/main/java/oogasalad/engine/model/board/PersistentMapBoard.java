package oogasalad.engine.model.board;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import javafx.util.Pair;
import oogasalad.engine.model.OutOfBoardException;

public class PersistentMapBoard implements Board {


  @Override
  public void placeNewPiece(int row, int column, int type, int player) {

  }

  @Override
  public void remove(int i, int j) {

  }

  @Override
  public boolean isEmpty(int i, int j) {
    return false;
  }

  @Override
  public Optional<PieceRecord> getPieceRecord(int i, int j) {
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
  public Iterator<Pair<Position, Piece>> iterator() {
    return null;
  }
}
