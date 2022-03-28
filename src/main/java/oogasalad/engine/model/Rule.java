package oogasalad.engine.model;

@FunctionalInterface
public interface Rule {

public Boolean AbstractRule(Board board, Action action, Player currentPlayer);

}
