package oogasalad.engine.model.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.rule.Rule;
import oogasalad.engine.model.rule.terminal_conditions.EndRule;
import oogasalad.engine.model.rule.Move;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class controls game logic, such as generation of available moves, checking rules, etc
 *
 * @author Jake Heller
 */
public class Oracle implements AIOracle {

  private static final Logger LOG = LogManager.getLogger(Oracle.class);

  private Collection<Move> myMoves;
  private Collection<EndRule> myEndRules;
  private Collection<Move> myPersistentRules;

  private int myNumPlayers;

  public Oracle(Collection<Rule> rules, int numPlayers) {

    Collection<Move> moves = filterByClass(rules, Move.class);
    Collection<EndRule> endRules = filterByClass(rules, EndRule.class);

    myMoves = moves.stream().filter(move -> !move.isPersistent()).toList();
    myPersistentRules = moves.stream().filter(move -> move.isPersistent()).toList();
    myEndRules = endRules;
    myNumPlayers = numPlayers;

    LOG.info("New: Size of moves, persistent, end rules: {}, {}, {}\n", myMoves.size(), myPersistentRules.size(), myEndRules.size());
    //moves = filterByClass(rules, Move.class);
  }

  // generic method to filter collection by class type
  private <T> Collection<T> filterByClass(Collection<Rule> collection, Class<T> type) {
    return collection.stream().filter(object -> object.getClass().equals(type)).map(object -> (T) object).toList();
  }

  /**
   * Returns valid moves for given position and board
   * If you want to use the game's current board, you can
   * use the gameGameStateBoard() function
   *
   * Note: this returns all available moves, not specific to
   * a player
   *
   * @param board
   * @param referencePoint
   * @return
   */
  public Stream<Move> getValidMovesForPosition(Board board, Position referencePoint) {
    return myMoves.stream().filter((move) -> move.isValid(board, referencePoint));
  }

  private Stream<Choice> getValidChoicesForPosition(Board board, Position referencePoint) {
    return getValidMovesForPosition(board, referencePoint).map(move -> new Choice(referencePoint, move, board));
  }

  /**
   * Returns all valid moves for this board state as a stream
   * @param board
   * @return
   */
  public Stream<Choice> getValidChoices(Board board) {
    return board.getPositionStatesStream().flatMap(positionState -> getValidChoicesForPosition(board, positionState.position()));
  }

  /**
   * Applies persistent rules to a board
   * Should be called after a player move gets executed and before
   * the end check
   *
   * @param board
   * @return
   */
  public Board applyPersistentRules(Board board) {
    for (Move rule: myPersistentRules) {
      for (PositionState cell: board) {
        if (rule.isValid(board, cell.position())) {
          board = rule.doMove(board, cell.position());
        }
      }
    }
    return board;
  }

  /**
   * Returns next state for this board, including incrementing player
   * @param board
   * @param choice
   * @return
   */
  public Board getNextState(Board board, Choice choice) {
    if (!choice.isValidChoice(board)) {
      throw new RuntimeException("Invalid Choice");
    }
    else {
      board = choice.move().doMove(board, choice.position());
      board = applyPersistentRules(board);
    }
    return board;
  }

  /**
   *
   * @param p1 relative point
   * @param p2 representative point
   * @return
   */
  public Optional<Move> getMoveSatisfying(Board board, Position p1, Position p2) {
    Optional<Move> choice = myMoves.stream().filter(move -> move.isValid(board, p1)).filter(move -> move.getRepresentativeCell(
        p1).equals(p2)).findFirst();
    return choice;
  }

  public List<Position> getRepresentativePoints(Stream<Move> moves, Position referencePoint) {
    List<Position> positions = new ArrayList<>();
    moves.forEach((move) -> positions.add(move.getRepresentativeCell(referencePoint)));

    return positions;
  }

  public Board incrementPlayer(Board board) {
    int nextPlayer = (board.getPlayer() + 1) % myNumPlayers;
    return board.setPlayer(nextPlayer);
  }

  /**
   * Gets choices for a specific player
   * Switches player to specified player if not active player
   * @param board
   * @param player
   * @return
   */
  @Override
  public Stream<Choice> getChoices(Board board, int player) {
    board = board.setPlayer(player);
    return getValidChoices(board);
  }

  @Override
  public boolean isWinningState(Board board) {
    Optional<EndRule> satisfyingEndRule = getValidEndRules(board).findFirst();
    return satisfyingEndRule.isPresent();
  }

  /**
   * Returns player id if that player is a winner given board state
   * if no winner for board state, returns -1
   * @param board
   * @return
   */
  public int getWinner(Board board) {
    if (isWinningState(board)) {
      Optional<EndRule> validEndRule = getValidEndRules(board).findFirst();
      return validEndRule.get().getWinner(board);
    }
    else {
      return -1;
    }
  }

  private Stream<EndRule> getValidEndRules(Board board) {
    return board.getPositionStatesStream().flatMap(positionState ->
        myEndRules.stream().filter(endRule ->
            endRule.isValid(board, positionState.position())));
  }
}
