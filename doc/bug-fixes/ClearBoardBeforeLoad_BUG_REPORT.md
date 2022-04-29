## Description

The load for board doesn't clear the previous grid and pieces before creating the new board.

## Expected Behavior

I expect all of the canvases to clear before the board is ever drawn including for loading

## Current Behavior

Right now we can see previous pieces and the grid overlayed over the loaded board

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

 1. draw a board (set size and click draw board)
 1. load a game (like checkers)

## Failure Logs

Its only a viual error so no logs

## Hypothesis for Fixing the Bug

If I just call clearBoard() and clearGrid() when loading, it should solve the problem.