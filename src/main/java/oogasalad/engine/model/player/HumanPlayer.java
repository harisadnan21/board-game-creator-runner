package oogasalad.engine.model.player;

import java.util.HashSet;
import java.util.Optional;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Move;
import oogasalad.engine.model.utilities.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HumanPlayer extends Player{
  private static final Logger LOG = LogManager.getLogger(Player.class);

  private Position mySelectedCell = null;
  private Set<Position> myValidMoves = null;

  private Move mySelectedMove = null;
  private Choice myChoice;

  private Consumer<Set<Position>> mySetValidMarks;

  public HumanPlayer(Oracle oracle, Game game, BiConsumer<Player, Choice> executeMove, Consumer<Set<Position>> setValidMarks) {
    super(oracle, game, executeMove);
    mySetValidMarks = setValidMarks;
  }

  @Override
  public Choice chooseMove() {

    LOG.info("Player asked to choose move");
    while (mySelectedMove == null || mySelectedCell == null) {

    }
    Choice choice = new Choice(mySelectedCell, mySelectedMove);
    resetSelected();
    LOG.info("Choice about to be returned");
    return choice;
  }

  public void onCellSelect(int i, int j) {
    Position cellClicked = new Position(i, j);
    if (mySelectedCell == null) {
      makePieceSelected(i, j);
    }
    else {
      Oracle oracle = getOracle();
      Board board = getGameBoard();
      Optional<Move> move = oracle.getMoveSatisfying(board, mySelectedCell, cellClicked);
      if (move.isPresent()) {
        mySelectedMove = move.get();
        myChoice = new Choice(mySelectedCell, mySelectedMove);
        LOG.info("Move {} selected", mySelectedMove.getName());
        resetSelected();
        executeMove(this, myChoice);
      }
      resetSelected();
    }
  }

  private void makePieceSelected(int x, int y) {
    Board board = getGameBoard();

    // makes position selected if board has piece with current player or position is empty
    // should this condition exist, or should it be baked into rules?
    if (!board.isEmpty(x, y) && board.getPositionStateAt(x, y).player() == board.getPlayer() || board.isEmpty(x, y)) {
      mySelectedCell = new Position(x, y);
      myValidMoves = new HashSet<>(getOracle().getRepresentativePoints(getOracle().getValidMovesForPosition(board, mySelectedCell), mySelectedCell));
      setMarkers(myValidMoves);
      LOG.info("{} valid moves for this piece\n", myValidMoves.size());
    }
  }

  private void setMarkers(Set<Position> positions) {
    mySetValidMarks.accept(positions);
  }

  private void clearMarkers() {
    setMarkers(new HashSet<>());
  }

  private void resetSelected() {
    mySelectedCell = null;
    mySelectedMove = null;
    myValidMoves = null;
    clearMarkers();
  }

}
