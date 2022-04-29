## Description

If the user tries to draw a board without X_dim = Y_dim it throws an ArrayOutOfBoundsException

## Expected Behavior

I expect the board to be able to be drawn no matter what dimensions are chosen

## Current Behavior

The clearBoard() command in boardCanvas switches the x and y dimensions when looping over
ClearPieceCallback()

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. Choose Non-equal board dimensions
1. click on Draw Board

## Failure Logs

ERROR o.b.m.b.RectangularBoard [JavaFX Application Thread] Throwing
java.lang.ArrayIndexOutOfBoundsException: Index 7 out of bounds for length 7

## Hypothesis for Fixing the Bug

If I switch i and j in the BoardCanvas clearBoard() it should fix this problem.