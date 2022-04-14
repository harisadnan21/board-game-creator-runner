package oogasalad.engine.model.setup.parsing;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import oogasalad.engine.model.board.misc.Direction;
import oogasalad.engine.model.setup.Constants;
import oogasalad.engine.model.setup.Delta;

public class DeltaLoader {

  // Reference: https://zetcode.com/java/resourcebundle/
  public static Map<Direction, Delta> loadDirectionDeltas() {
    // TODO: Make better
    EnumMap<Direction, Delta> ret = new EnumMap<> (Direction.class);
    ResourceBundle directionDeltas = Constants.DIRECTIONDELTAS_RESOURCES;
    for(String direction: directionDeltas.keySet()){
      Direction directionEnum = Direction.valueOf(direction.toUpperCase());
      int[] arry = Arrays.stream(directionDeltas.getString(direction).split(", ")).mapToInt(Integer::parseInt).toArray();
//      System.out.println(directionEnum);
      Delta delta = new Delta(arry[0], arry[1]);
//      System.out.println(delta);
      ret.put(directionEnum, delta);
    }
    return ret;
  }
}
