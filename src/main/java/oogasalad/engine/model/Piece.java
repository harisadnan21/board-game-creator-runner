package oogasalad.engine.model;

/**
 * 
 */
public class Piece {
    private String myType;
    private int myOwner;

    public Piece(String type, int player){
        myType = type;
        myOwner = player;
    }

    /**
     * @param i 
     * @param j
     */
    public void move(int i, int j) {
        // TODO implement here
    }

    /**
     * @param i
     */
    public void changeType(int i) {

        // TODO implement here
    }

    public Piece deepCopy() {
        Piece piece = new Piece(myType, myOwner);
        return piece;
    }
}