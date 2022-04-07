package oogasalad.engine.model.rules;

import java.util.ArrayList;
import java.util.List;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Board;

public class Validity {
  private List<Rule> myRules;

  public Validity(List<Rule> rulesInPlay){
    myRules = rulesInPlay;
  }

  //public List<int[]> checkValidity(Piece piece, Board board){
    //myRules.stream.filter(rule -> rule
        //.pieces.contains(piece));
  //}
}
