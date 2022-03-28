package oogasalad.engine.model;

@FunctionalInterface
public interface LegalMove {

public Boolean legalMove(Board board, Action action, Player currentPlayer, Rule[] rules);

}
