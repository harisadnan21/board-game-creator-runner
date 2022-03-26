// User places a piece on the board from the sidebar in the board tab
// In BoardTab
// parameters all set based on what the user clicked on in the sidebar
private void placePieceCallback(int x, int y, String piece) {
    // called from a callback passed when being created by the view, which got it from the controller
    model.placeBoardPiece(x, y, piece);
    // do whatever needs to be done internally to show that piece on the board
}

// In BuilderModel
public void placeBoardPiece(int x, int y, String name) {
    gameConfiguration.placeBoardPiece(x, y, name);
}

// In GameConfiguration
public void placeBoardPiece(int x, int y, String name) {
    board.placePiece(x, y, name);
}