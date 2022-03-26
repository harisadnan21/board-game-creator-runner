# OOGASalad Example Games

### TEAM OOGABOOGA

### NAMES

* Ricky Weerts
* Jake Heller
* Robert Cranston
* Haris Adnan
* Alexander Bildner
* Thivya Sivarajah
* Cynthia France
* Mike Keohane
* Shaan Gondalia

## Tic-tac-toe

Tic-tac-toe involves placing Xs and Os on a 3x3 board. The goal of the game is to create a line
containing 3 of the same piece. For instance, player 1 wins when they place 3 Xs in a line and
player 2 wins when they place 3 Os in a line. The game can end in a draw if there are no more "open"
tiles left on the 3x3 board (i.e. 9 pieces have been placed and no "lines" have been created).

This game was chosen as it is very simple but is a very good starting point for making other games.
This will test the process of defining the board, pieces, piece placing, movement, teams, and
win-conditions. If we are able to make a functional form of tic-tac-toe, we should be able to
generalize our design to any number of games with arbitrarily complex boards, pieces, and win
conditions.

## Checkers

Checkers is played on an 8x8 board, where each player gets 12 pieces that are placed on the dark
squares of the first and last three rows. Pieces can move in two ways:

* Pieces can move diagonally to any unoccupied square one row in front of it
* Pieces can "jump" over an opponent's piece if the next square along the jump diagonal is
  unoccupied

Any piece that has been jumped over is "captured" and removed from the board. Players win when all
of the opponent's pieces are removed from the board.

This game was chosen because it has a fairly simple winning condition, but will test our design for
games where pieces "move" and "collide" instead of being placed (like tic-tac-toe). It introduces
the concepts of capturing, piece removal, and more complex moves.

Checkers also has some more complex rules that can be implemented once the basic implementation is
complete. These rules include:

* Multiple jumps per turn, allowing multiple pieces to get captured in a single move.
* Pieces turning into "kings" once they reach the end of the board. Kings can move backward as well,
  instead of only forward.

## Othello

Othello is played on an 8x8 board between white and black pieces. Players take turn placing their
pieces, with the goal of ending the game with the most of their colored pieces on the board. The
game is finished when there are no possible moves for either player.

During a player's turn, they must place a piece such that it "outflanks" at least one of the
opponents pieces. To outflank means to place a piece on the board so that your opponent's row (or
rows) of disc(s) is bordered at each end by a disc of your colour. (A "row" may be made up of one or
more discs). These "rows" can be horizontal, vertical, or at a 45 degree diagonal in any direction.

For a visual description of this ruleset,
see [this link](https://www.worldothello.org/about/about-othello/othello-rules/official-rules/english)
.

Othello was chosen because, while similar to tic-tac-toe, it demonstrates a more complex ruleset 
regarding how pieces are placed and the actual consequences of placing a piece. This game will 
challenge our abstractions of pieces (can pieces transform from black to white and vice versa),
rules (how can pieces be placed? only when they outflank the opponent), and winning conditions.