package oogasalad.engine.model.engine;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Move;

import oogasalad.engine.model.player.Player;
import org.jooq.lambda.function.Consumer0;


public abstract class Engine {

  private Game myGame;

  private Collection<Move> myMoves;
  private Collection<WinCondition> myWinConditions;
  private Consumer<Board> updateView;
  private Consumer<Set<Position>> setViewValidMarks;
  private Consumer0 clearViewMarkers;

  private Collection<Player> players;

  public Engine(Game game, Collection<Move> moves,
      Collection<WinCondition> winConditions, Consumer<Board> update,
      Consumer<Set<Position>> setValidMarks, Consumer0 clearMarkers) {

    myGame = game;
    myWinConditions = winConditions;
    myMoves = moves;
    updateView = update;
    setViewValidMarks = setValidMarks;
    clearViewMarkers = clearMarkers;
  }

  public Engine(Game game){
    myGame = game;
  }

  protected Collection<Move> getMoves() {
    return myMoves;
  }

  protected Collection<WinCondition> getWinConditions() {
    return myWinConditions;
  }

  /**
   * @param player player requesting possible actions
   * @return
   */
  public Move[] getPossibleActions(int player) {

    return null;
  }
  protected void updateView(Board board){
    updateView.accept(board);
  }

  protected void setMarkers(Set<Position> validMoves){
    if(validMoves != null) {
      setViewValidMarks.accept(validMoves);
    }
  }
  protected void clearMarkers(){
    clearViewMarkers.accept();
  }

  protected Game getGame() {
    return myGame;
  }

  public abstract void onCellSelect(int x, int y)
      throws OutOfBoardException;

  public abstract Board getGameStateBoard();
}
