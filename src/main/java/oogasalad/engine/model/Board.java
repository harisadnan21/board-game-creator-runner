package oogasalad.engine.model;

import java.util.List;
import javafx.beans.InvalidationListener;


public class Board extends Observable<Integer[][]>{

  private int myHeight;
  private int myWidth;
  private int[][] myBoard;

  public Board(int width, int height) {
    myHeight = height;
    myWidth = width;
    myBoard = new int[height][width];
  }




}
