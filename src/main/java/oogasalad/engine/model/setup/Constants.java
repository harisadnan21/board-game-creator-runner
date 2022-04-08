package oogasalad.engine.model.setup;

import java.util.HashMap;
import java.util.ResourceBundle;
import oogasalad.engine.model.board.Direction;
import oogasalad.engine.model.setup.parsing.deltaLoader;

public class Constants {
  public static final String RESOURCEBUNDLEPATH = "resources/";
  public static final String DIRECTIONDELTASUBPATH = "direction_deltas";
  public static final HashMap<Direction, Delta> DIRECTIONDELTAS = deltaLoader.loadDirectionDeltas();


  public static final String HEIGHT = "height";
  public static final String PIECE_CONFIGURATION = "pieceConfiguration";
  public static final String PLAYER_CONFIGURATION = "playerConfiguration";
  public static final String BOARD = "board";
  public static final String WIDTH = "width";
  public static String CHECKERS_FILE = "data/checkers/checkers_test.json";
  public static String TIC_TAC_TOE_FILE = "data/tictactoe/tictactoe_test.json";
  public static String CONDITION_RESOURCES_PATH = "engine-resources.conditions";
  public static ResourceBundle CONDITION_RESOURCES = ResourceBundle.getBundle(CONDITION_RESOURCES_PATH);
  public static String ACTION_RESOURCES_PATH = "engine-resources.actions";
  public static ResourceBundle ACTION_RESOURCES = ResourceBundle.getBundle(ACTION_RESOURCES_PATH);

  //private static Logger log = LoggerFinder.getLoggerFinder().getLogger("Logger", "oogasald.engine");
}
