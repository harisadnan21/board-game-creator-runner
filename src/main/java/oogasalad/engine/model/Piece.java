package oogasalad.engine.model;

import java.util.List;

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
     * @param newType: new Piece Type
     */
    public void changeType(String newType) {
        // TODO implement here
    }

    public Piece deepCopy() {
        Piece piece = new Piece(myType, myOwner);
        return piece;
    }
}