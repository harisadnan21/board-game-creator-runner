// Saves a tic-tac-toe game configuration to a given file.

File saveFile = new File();
GameConfiguration gameConfig = new GameConfiguartion();
gameConfig.put(new PieceElement(/*Args for X pieces*/));
gameConfig.put(new PieceElement(/*Args for O pieces*/));

GameSaver saver = new GameSaver(); // Object that handles saving
saver.save(saveFile, gameConfig); // Saves the configuration to the file