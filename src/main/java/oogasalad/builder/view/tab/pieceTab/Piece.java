package oogasalad.builder.view.tab.pieceTab;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.util.*;

/**
 *
 */
public class Piece extends StackPane {

    private static final String firstColor = "RED";
    private static final String secondColor = "BLACK";
    private String type;
    private double mouseX, mouseY;
    private double oldX, oldY;
    private double rectWidth;
    private double rectHeight;

    public String getType() {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    /**
     * Default constructor
     */
    public Piece(String type, int xSize, int ySize) {
        this.type = type;

        move(xSize, ySize);

        Ellipse bg = new Ellipse(rectWidth * 0.3125, rectHeight * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(rectWidth * 0.03);

        bg.setTranslateX((rectWidth - rectWidth * 0.3125 * 2) / 2);
        bg.setTranslateY((rectHeight - rectHeight * 0.26 * 2) / 2 + rectHeight * 0.07);

        Ellipse ellipse = new Ellipse(rectWidth * 0.3125, rectHeight * 0.26);
        ellipse.setFill(type == Piece.firstColor
                ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(rectWidth * 0.03);

        ellipse.setTranslateX((rectWidth - rectWidth * 0.3125 * 2) / 2);
        ellipse.setTranslateY((rectHeight - rectHeight * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

    }

    /**
     * Default constructor
     */
    public void move(int x, int y) {
        oldX = x * rectWidth;
        oldY = y * rectHeight;
        relocate(oldX, oldY);
    }


}