## Description

Summary of the feature's bug (without describing implementation details)

## Expected Behavior

When a player has no moves, the engine automatically skips
their turn.

## Current Behavior

When the active player has no moves, the engine gets stuck
because players have no valid moves to submit

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. run Main
 2. select upload game
 3. navigate to src/test/resources/games/AI-Bug-Checkers
 4. select this folder and press open
 5. select continue
 6. select multiplayer
 7. choose a move for a white piece
 8. choose a move for the purple piece
 9. repeat 7

Now you can see that the engine is stuck. It is player 2's turn, but there are no
moves to execute.

## Failure Logs

No errors caused

## Hypothesis for Fixing the Bug\

The bug is here

```java
  /**
   * Increments player
   * If game is at a draw, the function should go back to original player
   * @param board
   * @return
   */
  public Board incrementPlayer(Board board) {
    int nextPlayer = (board.getPlayer() + 1) % myNumPlayers;
    int counter = 0;
    while (!getChoices(board, nextPlayer).findAny().isPresent() && counter < myNumPlayers + 1) {
      nextPlayer = (board.getPlayer() + 1) % myNumPlayers;
      counter++;
    }
    return board.setPlayer(nextPlayer);
  }
```

the next player should be incremented with every iteration of the while loop,
but here it gets evaluated to the same thing every time. The solution is to use the counter to
actually increment the player for every iteration of the loop.