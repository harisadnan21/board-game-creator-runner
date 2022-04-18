
# OOGASalad Backlog

### TEAM OOGABOOGA

## Features Per Person

### Ricky
1. The player fills a row in tic-tac-toe and wins the game.
2. All opponent pieces are captured in checkers and the remaining player wins.
3. The user jumps a checkers piece and the jumped piece is removed.
4. The board is shaped as a rectangle with a long spoke.
5. A game can have only one player that just does turns until reaching an end condition.
6. A piece moves into the end of the board and is changed into a different type of piece.
7. A piece moves to a space, not affecting any pieces it passes over.
8. A piece is surrounded by the opponent's pieces and is destroyed.
9. A piece moves along an arbitrary path.
10. A piece reaches the end of the board and the players switch sides.

### Jake
1. Load in JSON file and start game
2. Generic reinforcement learner that can train agent for any given game
3. Create rule that allows piece to be placed if the four neighboring spaces are empty or controlled by player
4. Write JSON file based on state of game builder
5. Cells in backend listen to the front-end
6. Player gets shown all possible moves for a selected piece
7. Player can click on possible options and cause appropriate move to execute
8. Allow moves to apply to different sets of pieces (one type, all types, one roles, etc)
9. add new piece type in builder
10. make engine into application so that you can open json files with the engine
11. make gamehub
12. Open already created game in builder and edit it

### Robert
1. Create the multiple splash screen template that will be used in the builder.
2. The builder can create a JSON file with the moves/settings specified
3. Allow the player to customize the apperance of the game in game.
4. Allow the glayer to edit game moves mid game.
5. Allow for pass and play implementation.
6. Allow for networking between players on seperate computers.
7. Allow for a timer to be implemented into games
8. The builder can reopen json files for editing
9. Allow for single player strategy games
10. Allow for basic AI

### Haris
1. Let the user add the board into the builder canvas, and let them edit the board - splitting up the board into different blocks by letting them decide the number of columns and rows, also select different colors on the board.
2. Let the user log into an initial launcher
3. Launcher displays all of the games previously made
4. User clicks buttons to launch builder / launch engine.
5. When the user clicks the save game version, the current version of the game made / features is added to a version history.
6. The user is provided with a selector to choose which image they want as the component inside the game - like what to display as a pawn / king
7. allow the user to pause the game(show a pause screen in game)
8. allow the user to choose color themes.
9. allow users to save a game into a JSON file into a location of their AIChoice.
10. users define their moves through an interface

### Alexander
1. Artificial Intelligence using basic minimax approach to try to beat player.
2. Artificial Intelligence

### Thivya
1. Change the options of movement for one given piece
2. Change grid size
3. Pause and be able to access previous steps (redo button?)
4. Player stats for game boards displayed and updated as the player creates more games
5. Configurations saved to game history
6. Change type of piece on board mid game
7. Change color of piece (color wheel pops up? is this possible?)
8. Save piece types (piece library?)
9. Save moves (rule library?)
10. Delete grid squares

### Cynthia
1. User specifies grid size and design
2. Customize image, number, and type of game pieces
3. Define movement moves for each piece, win/lose states
4. Save and load game configurations
5. Make further changes in existing (loaded in) configurations
6. Identify "regions" on the board where specific moves apply
7. Theme & language options
8. Display possible move locations based on piece selected
9. Captured pieces (if applicable) are moved to side area to be used later (or not)
10. Instructions/splash screen based on user's input

### Mike
1. Specify and choose board dimensions in the builder and background options and colors. (checkers, GO, uploaded image)
2. Declare specifc piece types and choose what each looks like / their names. (Choose images from files and use the name to correlate it to its properties)
3. Specify the properties for a specific piece. Click on it and then a pop up shows that allows you to set specific properties:

    
4. Setting movement - Grid where you can choose movement type and select where a piece can move (clicking) and then the backend processors that information. 
5. Setting colision options - a drop down to select what pieces do when they collide
6. Set capturing options - both what capture means (flip like othello or take like checkers) and how it can be done
7. Set how each piece type can be added / removed from the board (connect 4 from top only and it falls, othello has to be a valid flip).


8. Declare win conditions for the game by choosing from a few options and then filling in parameters. (X in a row wins. Set 4 as x)
9. Specify the stargin positions of the pieces by clicking and adding each piece to the specific squares and then whenever you save, the shown board is the starting positions.
10. Upload a file and fill the builder with the correct parameters from the file. Should be able to upload a game and then be able to make adjustments to it.

### Shaan
1. Place a piece on the board at location (i, j)
2. Move a piece at (i, j) to (x, y)
3. Have a piece check for a collision with another piece when it moves
4. Display the active squares a piece can move to
5. Give a piece a rule determining how it can move or be placed (such as outflanking in othello)
6. Let the user select a piece and view its active moves
7. Piece makes multiple "moves" in one turn (some way to have a piece do multiple things in a turn)
8. Let pieces transform to other pieces if they meet certain conditions
9. Restart a game
10. Non-rectangular boards