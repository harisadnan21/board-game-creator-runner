package oogasalad.engine.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ExtraPiece {

  private BorderPane root;
  private int pieceCount;
  private Text countText;
  private ImageView pieceImage;

  public ExtraPiece(String imagePath) {
    root = new BorderPane();
    pieceCount = 10;
    pieceImage = new ImageView(new Image(imagePath));
    pieceImage.setFitHeight(50);
    pieceImage.setFitWidth(50);
    pieceImage.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {removePiece();});
    countText = new Text(Integer.toString(pieceCount));
    root.getChildren().addAll(pieceImage, countText);
  }

  public void addPiece() {
    pieceCount++;
    countText.setText(Integer.toString(pieceCount));
  }

  public void removePiece() {
    pieceCount--;
    countText.setText(Integer.toString(pieceCount));
  }

  public BorderPane getRoot() {
    return root;
  }
}
