package oogasalad.builder.view.tab.pieceTab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.PropertyEditor;
import oogasalad.builder.view.tab.GameElementTab;
import oogasalad.builder.view.tab.TitlePane;
import oogasalad.builder.view.tab.boardTab.BoardCanvas;
import oogasalad.builder.view.tab.pieceTab.Piece;

import java.awt.event.ActionEvent;
import java.util.*;

/**
 * 
 */
public class PiecesTab extends GameElementTab {
    public static String PIECE = "piece";

    public PiecesTab(BuilderController controller) {
        super(controller, PIECE);
    }

}