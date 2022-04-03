package oogasalad.engine.model;

import java.util.List;

/**
 * 
 */
public class Piece {
    private String myType;
    private int myOwner;
    private int rowVal;
    private int colVal;

    public Piece(String type, int player, int rowNum, int colNum){
        myType = type;
        myOwner = player;
        rowVal = rowNum;
        colVal = colNum;

    }
    public void movePiece(int newRow, int newCol)  {
        setPieceRow(newRow);
        setPeiceColumn(newCol);
    }

    private void setPieceRow(int rowNum){
        rowVal = rowNum;
    }
    private void setPeiceColumn(int ColumnNum) {

        colVal = ColumnNum;
    }

    /**
     * @param newType: new Piece Type
     */
    public void changeType(String newType) {
        // TODO implement here
    }

    public Piece deepCopy() {
        Piece piece = new Piece(myType, myOwner, rowVal, colVal);
        return piece;
    }
}