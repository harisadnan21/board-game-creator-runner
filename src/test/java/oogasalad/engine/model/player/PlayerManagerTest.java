package oogasalad.engine.model.player;

import oogasalad.engine.model.ai.AIPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerManagerTest {

  PlayerManager myManager = new PlayerManager();
  @BeforeEach
  void setup() {

  }

  @Test
  void testAddPlayer() {
    AIPlayer player1 = new AIPlayer(0, null);
    Player player2 = new AIPlayer(1, null);

    myManager.addPlayer(0, player1);
    myManager.addPlayer(1, player2);
  }
}
