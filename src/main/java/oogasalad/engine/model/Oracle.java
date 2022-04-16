package oogasalad.engine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.move.Move;
import org.jooq.lambda.function.Consumer0;

/**
 * This class controls game logic, such as generation of available moves, checking rules, etc
 *
 * @author Jake Heller
 */
public class Oracle {

  private Collection<Move> myMoves;
  private Collection<WinCondition> myWinConditions;
  private Collection<Move> myPersistentRules;



  public Oracle(Collection<Move> moves, Collection<WinCondition> winConditions, Collection<Move> persistentRules) {
    myMoves = moves;
    myWinConditions = winConditions;
    myPersistentRules = persistentRules;

  }
  /**
   *
   * @param board
   */
  // not sure this is how winning should work, I'd like to see
  // right now it is designed like this so the front end could
  // use it as a signal to see who won, the winner variable has
  // no functionality in the engine
  //checks to see if any of the win conditions are satisfied and if they are it sets the winner on the board.
  public Board checkForWin(Board board) {
    for (Iterator<WinCondition> it = myWinConditions.iterator(); it.hasNext(); ) {
      WinCondition winCondition = it.next();
      if(winCondition.isOver(board)){
        return board.setWinner(winCondition.getWinner(board));
      }
    }
    return board;
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
    return myMoves.stream().filter((move) -> move.isValid(board, referencePoint.i(), referencePoint.j()));
  }

  /**
   * Outer map
   * @param board
   * @return two dimensional map, where outer map key is the 'reference point' for the move, while the
   * inner map key is the 'representative point' of the move, or the
   */
  public Map<Position, Stream<Move>> getAllValidMoves(Board board) {
    Map<Position, Stream<Move>> allMoves = new HashMap<>();
    board.getPositionStatesStream().forEach(posState ->
        allMoves.put(posState.position(), getValidMovesForPosition(board, posState.position())));
    return allMoves;
  }
  /**
   * Applies persistent rules to a board
   * Should be called after a player move gets executed and before
   * the end check
   *
   * @param board
   * @return
   */
  public Board applyRules(Board board) {
    Board finalBoard = board;
    for (Move rule: myPersistentRules) {
      board = rule.doMovement(board, 0, 0);
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
    Optional<Move> choice = myMoves.stream().filter(move -> move.isValid(board, p1.i(), p1.j())).filter(move -> move.getRepresentativeCell(
        p1.i(), p1.j()).equals(p2)).findFirst();
    return choice;
  }

  public List<Position> getRepresentativePoints(Stream<Move> moves, Position referencePoint) {
    int i = referencePoint.i();
    int j = referencePoint.j();
    List<Position> positions = new ArrayList<>();
    moves.forEach((move) -> positions.add(move.getRepresentativeCell(i,j)));

    return positions;
  }

}
