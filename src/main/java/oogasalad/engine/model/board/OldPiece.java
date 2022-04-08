package oogasalad.engine.model.board;

/**
 * Class that defined a piece on the board.
 */
@Deprecated
public class OldPiece {
    private int myType;
    private int myOwner;
    private int rowVal;
    private int colVal;

    public OldPiece(int type, int player, int rowNum, int colNum){
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
     * @param newType: new OldPiece Type
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

    public OldPiece deepCopy() {
        OldPiece piece = new OldPiece(myType, myOwner, rowVal, colVal);
        return piece;
    }
    public PieceRecord getPieceRecord(){
        return new PieceRecord(myType, myOwner, rowVal, colVal);
    }
}