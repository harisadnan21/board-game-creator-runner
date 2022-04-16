package oogasalad.engine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
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

  private List<Move> myMoves;
  private List<WinCondition> myWinConditions;
  private List<Move> myPersistentRules = new ArrayList<>();
  private Consumer<Board> updateView;
  private Consumer<Set<Position>> setViewValidMarks;
  private Consumer0 clearViewMarkers;

  /**
   *
   * @param board
   */
  // not sure this is how winning should work, I'd like to see
  // right now it is designed like this so the front end could
  // use it as a signal to see who won, the winner variable has
  // no functionality in the engine
  public void checkForWin(Board board) {
    for(WinCondition winCondition : myWinConditions){
      if(winCondition.isOver(board)){
        board.setWinner(winCondition.getWinner(board));
      }
    }
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
   * @param i
   * @param j
   * @return
   */
  public Set<Move> getValidMoves(Board board, int i, int j) {
    // If a player wants to display the moves on a screen, they should use the representative point
    // of the move with rule.getRepresentativePoint(i, j)
    Set<Move> moves = new HashSet<>();
    for (Move move: myMoves) {
      if (move.isValid(board, i, j)) {
        moves.add(move);
      }
    }
    return moves;
  }

  /**
   * Outer map
   * @param board
   * @return two dimensional map, where outer map key is the 'reference point' for the move, while the
   * inner map key is the 'representative point' of the move, or the
   */
  public Map<Position, Set<Move>> getAllValidMoves(Board board) {
    Map<Position, Set<Move>> allMoves = new HashMap<>();
    for (PositionState cell: board) {
      Position position = cell.position();
      allMoves.put(position, getValidMoves(board, position.i(), position.j()));
    }
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
    for (Move rule: myPersistentRules) {
      board = rule.doMovement(board, 0, 0);
    }
    return board;
  }


}
