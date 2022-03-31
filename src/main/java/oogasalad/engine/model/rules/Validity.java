package oogasalad.engine.model.rules;

import java.util.ArrayList;
import java.util.List;
import oogasalad.engine.model.Piece;
import oogasalad.engine.model.board.Board;

public class Validity {
  private List<Rule> myRules;

  public Validity(List<Rule> rulesInPlay){
    myRules = rulesInPlay;
  }

  public List<int[]> checkValidity(Piece piece, Board board){
    Piece[][] boardGrid = board.getMyBoard();
    Rule currentRule;
    List<int[]> validSpaces = new ArrayList<>();
    for(Rule rule : myRules){
      if(!rule.checkValidity(piece, board)) return null;
    }

    return validSpaces;
  }
}
