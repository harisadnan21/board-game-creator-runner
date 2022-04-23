package oogasalad.engine.model.ai.evaluation.patterns;

import static org.jooq.lambda.Seq.seq;

import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeSet;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;

public class Pattern implements Comparable<Pattern> {
  protected final SortedSet<PositionState> positionStates;
  protected final transient SortedSet<Position> positions;
  protected final transient SortedMap<Position, Piece> pieceAtPositions;
  protected final transient int size;
  protected final transient int forPlayer;

  public Pattern(Collection<PositionState> positionStates) {
    validate(positionStates);
    this.positionStates = TreeSet.ofAll(positionStates);
    this.positions = TreeSet.ofAll(allPositionsIn(positionStates));
    this.pieceAtPositions = TreeMap.ofAll(positionToPiecemap(positionStates));
    this.size = positionStates.size();
    this.forPlayer = getPlayer(positionStates);
  }

  private void validate(Collection<PositionState> positionStates) {
    if(nullOrEmpty(positionStates) || !allSamePlayer(positionStates)) {
      throw new IllegalArgumentException("Illegal Position States");
    }
  }

  private boolean nullOrEmpty(Collection<PositionState> positionStates) {
    return positionStates == null || positionStates.isEmpty();
  }

  private boolean allSamePlayer(Collection<PositionState> positionStates) {
    return positionStates.stream().mapToInt(PositionState::player).distinct().count() == 1;
  }

  private int getPlayer(Collection<PositionState> positionStates) {
    return positionStates.stream().findAny().get().player();
  }

  private Stream<Position> allPositionsIn(Collection<PositionState> positionStates) {
    return positionStates.stream().map(PositionState::position);
  }

  private Map<Position, Piece> positionToPiecemap(Collection<PositionState> positionStates) {
    return seq(positionStates.stream()).toMap(PositionState::position, PositionState::piece);
  }

  public boolean includes(Position position) {
    return positions.contains(position);
  }

  public Piece pieceAt(Position position) {
    return getPieceAtPositions().get(position).getOrNull();
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
    return forPlayer;
  }
}
