package oogasalad.engine.model.engine;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import oogasalad.engine.model.Oracle;
import oogasalad.engine.model.conditions.terminal_conditions.WinCondition;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.move.Move;
import oogasalad.engine.model.player.HumanPlayer;
import oogasalad.engine.model.player.Player;
import oogasalad.engine.model.utilities.Pair;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oogasalad.engine.model.board.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;

public class PieceSelectionEngine extends Engine {
  private static final Logger LOG = LogManager.getLogger(PieceSelectionEngine.class);
  private boolean myIsPieceSelected = false;
  private Position mySelectedCell = null;
  private Set<Position> myValidMoves = null;
  private List<Move> myPersistentRules = new ArrayList<>();
  private Map<Integer, Player> myPlayers = new HashMap<>();

  // from Engine
  private Game myGame;

  private Oracle myOracle;

  private Collection<Move> myMoves;
  private Consumer<Set<Position>> setViewValidMarks;

  public PieceSelectionEngine(Game game, Collection<Move> moves,
      Collection<WinCondition> winConditions, Consumer<Board> update, Consumer<Set<Position>> setValidMarks) {
    super(game, moves, winConditions, update, setValidMarks);

    myGame = game;
    myMoves = moves;
    setViewValidMarks = setValidMarks;

    myOracle = new Oracle(moves, winConditions, new ArrayList<>());

    myPlayers.put(0, new HumanPlayer(myOracle, myGame, setValidMarks));
    myPlayers.put(1, new HumanPlayer(myOracle, myGame, setValidMarks));
    //createWinCondition();
    //createCheckersMove();
    //createPlayer1Moves();
  }

  @Override
  public void gameLoop() {
    while (true) {
      for (int playerID : myPlayers.keySet()) {
        Player player = myPlayers.get(playerID);
        Pair<Position, Move> choice = player.chooseMove();
        if (choice.value().isValid(getGameStateBoard(), choice.key())) {
          Board newBoard = choice.value().doMovement(getGameStateBoard(), choice.key());
          Position position = choice.key();
          LOG.info("{} executed at {},{}", choice.value().getName(), position.i(), position.j());
          myGame.setBoard(newBoard);
        }
        else {
          LOG.warn("Player {}'s move was not valid", playerID);
        }
      }
    }
  }

  public void onCellSelect(int i, int j) {
    Board board = getGameStateBoard();
    Player activePlayer = myPlayers.get(board.getPlayer());
    activePlayer.onCellSelect(i, j);
  }

//  @Override
//  public void onCellSelect(int x, int y) throws OutOfBoardException {
//    Board board = getGameStateBoard();
//    Position cellClicked = new Position(x, y);
//    if (!myIsPieceSelected) {
//      makePieceSelected(x, y);
//    }
//    else {
//      Optional<Move> move = myOracle.getMoveSatisfying(board, mySelectedCell, cellClicked);
//      if (move.isPresent()) {
//        Board newBoard = move.get().doMovement(board, mySelectedCell.i(), mySelectedCell.j());
//        LOG.info("{} executed at {},{}", move.get().getName(), x, y);
//        newBoard = myOracle.checkForWin(newBoard);
//        myGame.setBoard(newBoard);
//        resetSelected();
//        setMarkers(new HashSet<>());
//        return;
//      }
//      resetSelected();
//      setMarkers(new HashSet<>());
//    }
//
//    LOG.info("Valid Moves for selected piece are {} ", board.getValidMoves());
//  }

  private void makePieceSelected(int x, int y) {
    Board board = getGameStateBoard();

    // makes position selected if board has piece with current player or position is empty
    // should this condition exist, or should it be baked into rules?
    if (!board.isEmpty(x, y) && board.getPositionStateAt(x, y).player() == board.getPlayer() || board.isEmpty(x, y)) {
      myIsPieceSelected = true;
      mySelectedCell = new Position(x, y);
      myValidMoves = new HashSet<>(myOracle.getRepresentativePoints(myOracle.getValidMovesForPosition(board, mySelectedCell), mySelectedCell));
      setMarkers(myValidMoves);
      LOG.info("{} valid moves for this piece\n", myValidMoves.size());
    }
  }

  /**
   * Plays a turn, presumably executed from the Player class
   * Updates the game's board with the resultant board
   * @param player
   * @param move
   * @param i
   * @param j
   */
  public void playTurn(Player player, Move move, int i, int j) {
    Board board = getGameStateBoard();
    int activePlayer = board.getPlayer();
    if (move.isValid(getGameStateBoard(), i, j) && myPlayers.get(activePlayer) == player) {
      board = move.doMovement(board, i, j);
    }
    board = myOracle.applyRules(board);
    myGame.setBoard(board);
  }

  /**
   *
   * @return the board which is at the head of the game's board stack
   */
  @Override
  public Board getGameStateBoard() {
    return myGame.getBoard();
  }

  private void resetSelected() {
    myIsPieceSelected = false;
    mySelectedCell = null;
    myValidMoves = null;
  }

  private void setMarkers(Set<Position> validMoves){
    if(validMoves != null) {
      setViewValidMarks.accept(validMoves);
    }
  }
}
