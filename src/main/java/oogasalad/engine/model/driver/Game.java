package oogasalad.engine.model.driver;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import oogasalad.engine.model.board.Board;
import org.w3c.dom.Node;

/**
 * Game class that sets up the current board and contains history of all the previous boards.
 * @author: Jake Heller, Haris Adnan
 */
public class Game {

  private Board myBoard;
  private Consumer<Board> myUpdateView;
  private List<Board> myBoardHistory;
  private int backInHistory;


  public Game(Board startingBoard, Consumer<Board> updateView) {
    myBoard = startingBoard;
    myBoardHistory = new LinkedList<Board>();
    myUpdateView = updateView;
    backInHistory =0;
  }

  public void setBoard(Board board) {
    myBoardHistory.add(myBoard);
    myBoard = board;
    myUpdateView.accept(board);
    backInHistory = 0;
  }

  public Board getBoard() {
    return myBoard;
  }

  /**
   * function saves the current board and sets the previous board as the current board
   */
  public void back(){


    if (getLinkedListLength(myBoardHistory) == 0){
      throw new BoardHistoryException("You have gone too far back in the history, no board to show");
    }
    else{

    }
    myBoard = myBoardHistory.getLast();
    myBoardHistory.add(myBoard);
    myUpdateView.accept(myBoard);

  }

  /**
   * function sets the current board as the previous booard (adds it to the history of boards ) and
   * then makes the new board the current board.
   */
  public void forward(){
    myBoardHistory.push(myBoard);
  }
  private int getLinkedListLength(List list){
    return list.size();
  }
  private Node getNode(int indexOfNode, List list){

    for (int i = 0; i < getLinkedListLength(list) ; i++){
      if( i == indexOfNode){
        return null;
      }
    }
  }



}
