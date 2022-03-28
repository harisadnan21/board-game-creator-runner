package oogasalad.engine.model;

import java.util.List;
import javafx.beans.InvalidationListener;


public class Board extends Observable<Piece[][]>{

  private int myHeight;
  private int myWidth;
  private Piece[][] myBoard;

  public Board(int width, int height) {
    myHeight = height;
    myWidth = width;
    myBoard = new Piece[height][width];
  }

  public void selectCell(int x, int y){
    Piece[][]oldBoard = myBoard;
    place(x, y);
    notifyListeners("UPDATE", oldBoard, myBoard);
  }

  private void place(int x, int y){
    myBoard[x][y] = new Piece("Knight", 1);
  }





}
