package oogasalad.engine.model.board;

import java.util.Optional;

/**
 * 
 */
public class Piece {
    private int myType;
    private int myOwner;
    private int rowVal;
    private int colVal;

    public Piece(int type, int player, int rowNum, int colNum){
        myType = type;
        myOwner = player;
        rowVal = rowNum;
        colVal = colNum;

    }
    public void movePiece(int newRow, int newCol)  {
        setPieceRow(newRow);
        setPieceColumn(newCol);
    }

    private void setPieceRow(int rowNum){
        rowVal = rowNum;
    }

    private void setPieceColumn(int ColumnNum) {
        colVal = ColumnNum;
    }

    /**
     * @param newType: new Piece Type
     */
    public void changeType(int newType) {
        myType = newType;
    }

//    public int getI() {
//        return rowVal;
//    }
//
//    public int getJ() {
//        return colVal;
//    }
//
//    public int getOwner() {
//        return myOwner;
//    }
//
//    public int getType() {
//        return myType;
//    }

    public Piece deepCopy() {
        Piece piece = new Piece(myType, myOwner, rowVal, colVal);
        return piece;
    }
    public PieceRecord getPieceRecord(){
        return new PieceRecord(myType, myOwner, rowVal, colVal);
    }
}