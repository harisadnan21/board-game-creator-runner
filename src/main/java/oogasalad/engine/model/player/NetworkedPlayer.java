package oogasalad.engine.model.player;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.rule.Move;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class NetworkedPlayer extends Player{
  private static final Logger LOG = LogManager.getLogger(Player.class);

  private Choice myChoice;

  private Socket socket;
  private DataInputStream socketInput;

  public NetworkedPlayer(Oracle oracle, Game game, BiConsumer<Player, Choice> executeMove, Socket socket) throws IOException {
    super(oracle, game, executeMove);
    this.socket = socket;
    this.socketInput = new DataInputStream(socket.getInputStream());
  }

  @Override
  public void chooseMove() {
    LOG.info("Player asked to choose move");
    Oracle oracle = getOracle();
    Board board = getGameBoard();
    Position selectedCell = readPosition();
    System.out.println(selectedCell);
    Optional<Move> move = oracle.getMoveSatisfying(board, selectedCell, readPosition());
    move.ifPresent(m -> {
      LOG.info("Move {} selected", m.getName());
      executeMove(this, new Choice(selectedCell, m));
    });
  }

  private Position readPosition() {
    try {
      return new Position(socketInput.readInt(), socketInput.readInt());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void onCellSelect(int i, int j) {
    chooseMove();
  }

}
