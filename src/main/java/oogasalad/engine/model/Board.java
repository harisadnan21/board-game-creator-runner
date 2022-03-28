package oogasalad.engine.model;

import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Board extends Observable<List>{

  private int myHeight;
  private int myWidth;
  private int[][] myBoard;

  public Board(int width, int height) {
    myHeight = height;
    myWidth = width;
    myBoard = new int[height][width];
  }

  public Board selectCell(int x, int y){

  }


}
