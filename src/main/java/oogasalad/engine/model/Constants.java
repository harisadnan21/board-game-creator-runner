package oogasalad.engine.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import oogasalad.engine.model.board.Direction;

public class Constants {
  public static final String RESOURCEBUNDLEPATH = "resources/";
  public static final String DIRECTIONDELTASUBPATH = "direction_deltas";
  public static final HashMap<Direction, Delta> DIRECTIONDELTAS = loadDirectionDeltas();

  // Reference: https://zetcode.com/java/resourcebundle/
  private static HashMap<Direction, Delta> loadDirectionDeltas() {
    // TODO: Make better
    HashMap<Direction, Delta> ret = new HashMap<>();
    ResourceBundle directionDeltas = ResourceBundle.getBundle(RESOURCEBUNDLEPATH+DIRECTIONDELTASUBPATH);
    for(String direction: directionDeltas.keySet()){
      Direction directionEnum = Direction.valueOf(direction.toUpperCase());
      int[] arry = Arrays.stream(directionDeltas.getString(direction).split(", ")).mapToInt(Integer::parseInt).toArray();
      Delta delta = new Delta(arry[0], arry[1]);
      ret.put(directionEnum, delta);
    }
    return ret;
  }


}
