## Description

In both IsInRow and IsInColumn, an instance variable is incremented when it should stay the same.

## Expected Behavior

The isTrue method should return the same value every time on the same input.

## Current Behavior

The method's return value increases on every call.

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. Create instance of Condition
1. Run in twice on the same input
1. Observe different results

## Failure Logs

Include any relevant print/LOG statements, error messages, or stack traces.

## Hypothesis for Fixing the Bug

```java
  // in IsInColumn
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    relativeColumn += referencePoint.column();
    return invertIfTrue(relativeColumn == absoluteColumn, invert);
  }

  // in IsInRow
  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    relativeRow += referencePoint.row();
    return invertIfTrue(relativeRow == absoluteRow, invert);
  }
```

relativeColumn and relativeRow are instance variables which should not be changed.