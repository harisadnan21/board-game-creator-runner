package oogasalad.engine.model.ai.evaluation.patterns;

import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedSet;
import java.util.Comparator;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;

@EqualsAndHashCode
@ToString
public abstract class Pattern implements Comparator<Pattern>, Comparable<Pattern> {
  protected final SortedSet<PositionState> positionStates;
  protected final transient SortedSet<Position> positions;
  protected final transient SortedMap<Position, Piece> pieceAtPositions;
  protected final transient int size;

  //TODO: get rid of, Intelij made me add
  protected Pattern(
      SortedSet<PositionState> positionStates,
      SortedSet<Position> positions,
      SortedMap<Position, Piece> pieceAtPositions, int size) {
    this.positionStates = positionStates;
    this.positions = positions;
    this.pieceAtPositions = pieceAtPositions;
    this.size = size;
  }

  public boolean includes(Position position) {
    return positions.contains(position);
  }

  public boolean includes(PositionState positionState) {
    return positionStates.contains(positionState);
  }

  public SortedSet<PositionState> getPositionStates() {
    return positionStates;
  }

  public SortedSet<Position> getPositions() {
    return positions;
  }

  public SortedMap<Position, Piece> getPieceAtPositions() {
    return pieceAtPositions;
  }

  public int getSize() {
    return size;
  }

}
