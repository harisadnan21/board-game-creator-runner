package oogasalad.engine.model.board;

/**
 * Record that defines a OldPiece
 */
@Deprecated
public record PieceRecord(int type, int player, int rowNum, int colNum) {
}
