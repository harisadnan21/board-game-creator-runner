package oogasalad.engine.model.ai.evaluation.patterns;

import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeSet;
import java.util.Collection;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import org.jooq.lambda.Seq;

public class Pattern implements Comparable<Pattern> {
  protected final SortedSet<PositionState> positionStates;
  protected final transient SortedSet<Position> positions;
  protected final transient SortedMap<Position, Piece> pieceAtPositions;
  protected final transient int size;

  protected Pattern(Collection<PositionState> positionStates) {
    this.positionStates = TreeSet.ofAll(positionStates);
    this.positions = TreeSet.ofAll(positionStates.stream().map(PositionState::position));
    this.pieceAtPositions = TreeMap.ofAll(Seq.seq(positionStates.stream()).toMap(PositionState::position, PositionState::piece));
    this.size = positionStates.size();
  }

  public boolean includes(Position position) {
    return positions.contains(position);
  }

  public boolean includes(PositionState positionState) {
    return positionStates.contains(positionState);
  }

  public Piece pieceAt(Position position) {
    return pieceAtPositions.get(position).getOrNull();
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

  @Override
  public int compareTo(Pattern pattern) {
    return this.positionStates.get().compareTo(pattern.getPositionStates().get());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pattern)) {
      return false;
    }

    Pattern pattern = (Pattern) o;

    return getPositionStates() != null ? getPositionStates().equals(pattern.getPositionStates()) : pattern.getPositionStates() == null;
  }

  @Override
  public int hashCode() {
    return getPositionStates() != null ? getPositionStates().hashCode() : 0;
  }

  public int forPlayer() {
    return 0;
  }
}
