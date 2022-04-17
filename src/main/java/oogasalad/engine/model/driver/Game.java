package oogasalad.engine.model.driver;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import oogasalad.engine.model.board.Board;

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
  public void back() throws BoardHistoryException{
    if (getLinkedListLength(myBoardHistory) == 0){
      throw new BoardHistoryException("You have gone too far back in the history, no board to show");
    }
    else{

    }
    backInHistory++;
    myBoard = (Board) getNode(getLinkedListLength(myBoardHistory)-1-backInHistory, myBoardHistory);
    myBoardHistory.add(myBoard);
    myUpdateView.accept(myBoard);
  }
  /**
   * function sets the current board as the previous booard (adds it to the history of boards ) and
   * then makes the new board the current board.
   */
  public void forward() throws BoardHistoryException{
    if (backInHistory == 0){
      throw new BoardHistoryException("you have gone too far up in history, no board to show");
    }
    else{
      myBoardHistory.add(myBoard);
      backInHistory--;
      myBoard = (Board) getNode(getLinkedListLength(myBoardHistory)-1-backInHistory, myBoardHistory);
      myUpdateView.accept(myBoard);

    }

  }

  /**
   * function goes Number boards back in History of Boards and makes this the current board
   * @param Number
   */
  public void backByAmount(int Number) throws BoardHistoryException{
    for(int i = 0; i< Number; i++){
      back();
    }
  }
  public void forwardByAmount(int Number) throws BoardHistoryException{
    for(int i = 0; i< Number; i++){
      forward();
    }
  }
  private int getLinkedListLength(List list){
    return list.size();
  }

  private Object getNode(int indexOfNode, List list){
    for (int i = 0; i < getLinkedListLength(list) ; i++){
      if( i == indexOfNode){
        return list.get(i);
      }
    }
    return null;
  }



}
