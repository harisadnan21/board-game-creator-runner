package oogasalad.engine.model.ai.evaluation.patterns;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import io.vavr.Tuple;
import io.vavr.collection.Set;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

public class PatternEvaluator implements StateEvaluator {
  protected SortedSet<Pattern> patterns;
  protected SortedMap<Position, Set<Pattern>> includes;
  protected Board oldBoard;
  protected ConcurrentHashMap<Pattern, Integer> scores;
  protected Map<Integer, Set<Pattern>> patternsForPlayer;

  public PatternEvaluator(Collection<Pattern> patterns) {
    this.patterns = TreeSet.ofAll(patterns);
    this.includes = createIncludes();
    this.oldBoard = null;
    this.scores = new ConcurrentHashMap<>(patterns.size());
    this.scores.putAll(Seq.seq(patterns.stream()).toMap(Function.identity(), pattern -> pattern.size));
    this.patternsForPlayer = Seq.of(Piece.PLAYER_ONE, Piece.PLAYER_TWO).toMap(Function.identity(), integer -> TreeSet.ofAll(patterns.stream().filter(pattern -> integer==pattern.forPlayer())));
  }


  @Override
  public Evaluation evaluate(Board board) {
    PriorityBlockingQueue<Position> positionsChanged = getPositionsToCheck(board);
    Seq.seq(positionsChanged).parallel().
        map(position -> new Tuple2<>(position, patternsAt(position))).
        forEach(positionSetTuple2 -> this.handle(positionSetTuple2, board));
    int[] distance = Seq.of(Piece.PLAYER_ONE, Piece.PLAYER_TWO).parallel().map((player) -> patternsForPlayer.get(player).map(pattern -> scores.get(pattern))).mapToInt(set -> set.min().get()).toArray();
    int diff = distance[0]-distance[1];
    return new Evaluation(diff, -1*diff);
  }

  public void handle(Tuple2<Position, Set<Pattern>> positionSetTuple2, Board newBoard) {
    Position position = positionSetTuple2.v1;
    positionSetTuple2.v2.forEach(pattern -> scores.put(pattern, scores.get(pattern) + newBoard.getPositionStateAt(position).player()==pattern.pieceAt(position).player()? 1: -1));
  }

  public PriorityBlockingQueue<Position> getPositionsToCheck(Board newBoard) {
    List<Position> changedPositions;
    if(oldBoard==null) {
      changedPositions = newBoard.getPositions().toList();
    }
    else {
      changedPositions = newBoard.getPositions().parallel().filter(position -> oldBoard.getPositionStateAt(position).player()!=newBoard.getPositionStateAt(position).player()).toList();
    }
    return new PriorityBlockingQueue<>(changedPositions);
  }

  public SortedSet<Pattern> getPatterns() {
    return patterns;
  }

  public SortedMap<Position, Set<Pattern>> getIncludes() {
    return includes;
  }

  public Set<Pattern> patternsAt(Position position) {
    return includes.get(position).getOrNull();
  }

  private SortedMap<Position, Set<Pattern>> createIncludes() {
    var positions = Seq.seq(patterns).flatMap(pattern -> pattern.getPositions().toJavaStream()).distinct();
    return TreeMap.ofAll(positions, Function.identity(), position -> TreeSet.ofAll(patternsForPos(position)));
  }

  private Stream<Pattern> patternsForPos(Position pos) {
    return patterns.filter(pattern -> pattern.includes(pos)).toJavaStream();
  }
}
