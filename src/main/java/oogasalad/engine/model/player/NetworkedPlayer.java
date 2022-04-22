package oogasalad.engine.model.player;

import javafx.application.Platform;
import javafx.geometry.Pos;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.parser.BoardSaver;
import oogasalad.engine.model.rule.Move;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

  private DataInputStream socketRead;
  private DataOutputStream socketWrite;

  public NetworkedPlayer(Oracle oracle, Game game, BiConsumer<Player, Choice> executeMove, Socket socket) throws IOException {
    super(oracle, game, executeMove);
    this.socketRead = new DataInputStream(socket.getInputStream());
    this.socketWrite = new DataOutputStream(socket.getOutputStream());
  }

  @Override
  public void chooseMove(Choice lastChoice) {
    LOG.info("Player asked to choose move");
    try {
      System.out.println(lastChoice);
      if(lastChoice != null) {
        sendPosition(lastChoice.position());
        socketWrite.writeUTF(lastChoice.move().getName());
      }
      // Get back the results in another thread to avoid freezing everything
      new Thread(() -> {
        System.out.println("Waiting for network");
        Position choicePos = receivePosition();
        System.out.println("received " + choicePos);
        String moveName;
        try {
          moveName = socketRead.readUTF();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        System.out.println("received " + moveName);
        Move move = getOracle().getValidMovesForPosition(getGameBoard(), choicePos).filter(m -> m.getName().equals(moveName)).findFirst().orElseThrow();
        LOG.info("Move {} selected", move.getName());
        Platform.runLater(() -> executeMove(this, new Choice(choicePos, move)));
      }).start();
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void sendPosition(Position position) {
    try {
      socketWrite.writeInt(position.row());
      socketWrite.writeInt(position.column());
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Position receivePosition() {
    try {
      return new Position(socketRead.readInt(), socketRead.readInt());
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void onCellSelect(int i, int j) {
  }

}
