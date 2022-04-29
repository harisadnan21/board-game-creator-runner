## Description

getRayOfMaxLength has bug

## Expected Behavior

Ray stops at given length

## Current Behavior

counter is not incremented so ray stops when it reaches invalid board position

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. call method with length equals 2
2. check size of returned collection

## Failure Logs

Include any relevant print/LOG statements, error messages, or stack traces.

## Hypothesis for Fixing the Bug

```java
  /**
   * Returns a ray of given length
   * If reaches invalid board state, ray cuts off early
   * @param board
   * @param startPosition
   * @param direction
   * @param length
   * @return
   */
  public static Collection<PositionState> getRayOfMaxLength(Board board, Position startPosition, Position direction, int length) {
    Collection<PositionState> ray = new ArrayList<>();

    Position currentPosition = startPosition;
    int currentLength = 0;
    while(board.isValidPosition(currentPosition) && currentLength < length){
      ray.add(board.getPositionStateAt(currentPosition));
      currentPosition = currentPosition.add(direction);
    }
    return ray;
  }
```

I need to add this to the end of the while loop

```java
currentLength++;
```