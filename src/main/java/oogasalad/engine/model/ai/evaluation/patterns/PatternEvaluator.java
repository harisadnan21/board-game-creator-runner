package oogasalad.engine.model.ai.evaluation.patterns;

import static java.util.function.Function.identity;
import static oogasalad.engine.model.board.Piece.PLAYER_ONE;
import static oogasalad.engine.model.board.Piece.PLAYER_TWO;
import static org.jooq.lambda.Seq.seq;

import io.vavr.collection.Set;
import io.vavr.collection.SortedMap;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeMap;
import io.vavr.collection.TreeSet;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;
import oogasalad.engine.model.ai.evaluation.Evaluation;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

/**
 * @author Alex Bildner
 */
public class PatternEvaluator implements StateEvaluator {
  protected SortedSet<Pattern> patterns;
  protected SortedMap<Position, Set<Pattern>> includes;
  protected Board oldBoard;
  protected ConcurrentHashMap<Pattern, Integer> scores;
  protected Map<Integer, Set<Pattern>> patternsForPlayers;

  public PatternEvaluator(Collection<Pattern> patterns) {
    throwIfInvalid(patterns);
    this.patterns = TreeSet.ofAll(patterns);
    this.includes = createIncludes();
    this.oldBoard = null;
    this.scores = buildScores(patterns);
    this.patternsForPlayers = buildPatternsForPlayers();
  }

  private void throwIfInvalid(Collection<Pattern> patterns) {
    if(patterns==null || patterns.size()==0) {
      throw new IllegalArgumentException("bad patterns");
    }
  }

  private Map<Integer, Set<Pattern>> buildPatternsForPlayers() {
    return seq(playersStream()).
            toMap(identity(),
                  this::patternsForPlayer);
  }

  private SortedSet<Pattern> patternsForPlayer(int player) {
    return patterns.filter(pattern -> player == pattern.forPlayer());
  }

  private ConcurrentHashMap<Pattern, Integer> buildScores(Collection<Pattern> patterns) {
    ConcurrentHashMap<Pattern, Integer> ret = new ConcurrentHashMap<>(patterns.size());
    ret.putAll(seq(patterns.stream()).toMap(identity(), pattern -> pattern.size));
    return ret;
  }


  @Override
  public Evaluation evaluate(Board board) {
    Seq<Position> positionsChanged = getPositionsToCheck(board);

    updateScores(board, positionsChanged);

    int[] distance = playersStream().map(this::scoresForPlayer).mapToInt(this::getBestScore).toArray();

    int playerOneAdvantage = distance[PLAYER_TWO] - distance[PLAYER_ONE];
    int playerTwoAdvantage = playerOneAdvantage * -1;

    oldBoard = board;

    return new Evaluation(playerOneAdvantage, playerTwoAdvantage);
  }

  private void updateScores(Board board, Seq<Position> positionsChanged) {
    positionsChanged.parallel().
        map(withItsPatterns()).
        forEach(positionWithPatterns -> updateScoresForPosition(positionWithPatterns, board));
  }

  private Integer getBestScore(Set<Integer> set) {
    return set.min().get();
  }

  private Set<Integer> scoresForPlayer(int player) {
    return patternsForPlayers.get(player).map(pattern -> scores.get(pattern));
  }

  private Stream<Integer> playersStream() {
    return Stream.of(PLAYER_ONE, PLAYER_TWO).parallel();
  }

  private Function<Position, Tuple2<Position, Set<Pattern>>> withItsPatterns() {
    return position -> new Tuple2<>(position, patternsAt(position));
  }

  public void updateScoresForPosition(Tuple2<Position, Set<Pattern>> positionWithPatterns, Board newBoard) {
    Position position = positionWithPatterns.v1;
    positionWithPatterns.v2.forEach(pattern -> updateScoresForPattern(newBoard, position, pattern));
  }

  private void updateScoresForPattern(Board newBoard, Position position, Pattern pattern) {
    int oldScore = scores.get(pattern);
    boolean isSamePlayer = playerAtPosition(newBoard, position) == playerAtPosition(position, pattern);
    if(isSamePlayer) scores.put(pattern, oldScore - 1);
    else scores.put(pattern, oldScore + 1);
  }

  private int playerAtPosition(Position position, Pattern pattern) {
    return pattern.pieceAt(
        position).player();
  }

  private int playerAtPosition(Board board, Position position) {
    return board.getPositionStateAt(position).player();
  }

  public Seq<Position> getPositionsToCheck(Board newBoard) {
    var relevantPositions = relevantPositions(newBoard);

    return oldBoard==null? relevantPositions : changedPositions(newBoard, relevantPositions);
  }

  private Seq<Position> changedPositions(Board newBoard, Seq<Position> relevantPositions) {
    return relevantPositions.filter(
        position -> differentPlayerAtPosition(newBoard, oldBoard, position));
  }

  private Seq<Position> relevantPositions(Board newBoard) {
    return newBoard.getPositions().filter(position -> includes.keySet().contains(position));
  }

  private boolean differentPlayerAtPosition(Board board1, Board board2, Position position) {
    return playerAtPosition(board1, position) != playerAtPosition(board2, position);
  }

  public Set<Pattern> patternsAt(Position position) {
    return includes.get(position).getOrNull();
  }

  private SortedMap<Position, Set<Pattern>> createIncludes() {
    var positions = seq(patterns).flatMap(pattern -> pattern.getPositions().toJavaStream()).distinct();
    return TreeMap.ofAll(positions, identity(), position -> TreeSet.ofAll(patternsForPos(position)));
  }

  private Stream<Pattern> patternsForPos(Position pos) {
    return patterns.filter(pattern -> pattern.includes(pos)).toJavaStream();
  }
}
