package oogasalad.engine.model.setup;

import java.util.Map;
import java.util.ResourceBundle;
import oogasalad.engine.model.board.Direction;
import oogasalad.engine.model.setup.parsing.DeltaLoader;

public class Constants {
  public static final String DIRECTIONDELTAPATH = "engine-resources.direction_deltas";
  public static final ResourceBundle DIRECTIONDELTAS_RESOURCES = ResourceBundle.getBundle(DIRECTIONDELTAPATH);

  public static final Map<Direction, Delta> DIRECTIONDELTAS = DeltaLoader.loadDirectionDeltas();


  public static final String HEIGHT = "height";
  public static final String PIECE_CONFIGURATION = "pieceConfiguration";
  public static final String PLAYER_CONFIGURATION = "playerConfiguration";
  public static final String BOARD = "board";
  public static final String WIDTH = "width";
  public static final String CHECKERS_FILE = "data/checkers/checkers_test.json";
  public static final String TIC_TAC_TOE_FILE = "data/tictactoe/tictactoe_test.json";
  public static final String CONDITION_RESOURCES_PATH = "engine-resources.conditions";
  public static final ResourceBundle CONDITION_RESOURCES = ResourceBundle.getBundle(CONDITION_RESOURCES_PATH);
  public static final String ACTION_RESOURCES_PATH = "engine-resources.actions";
  public static final ResourceBundle ACTION_RESOURCES = ResourceBundle.getBundle(ACTION_RESOURCES_PATH);

  //private static Logger log = LoggerFinder.getLoggerFinder().getLogger("Logger", "oogasald.engine");

  private Constants() {
    throw new IllegalStateException("Doesn't Support Instantiation");
  }

}
