package oogasalad.engine.model.rules;

import java.util.List;
import javafx.util.Pair;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.ArrayBoard;

public interface Rule {

  List<Pair<Integer, Integer>> checkValidity(Piece piece, ArrayBoard board);

}
