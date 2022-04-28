## Description

RemoveRay class has indexing error

## Expected Behavior

RemoveRay class takes in size 5 array

## Current Behavior

RemoveRay class tries to access index 7 in array

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. Create instance of RemoveRay with any size 5 int array
1. See that IndexOutOfBoundsException thrown

## Failure Logs

2022-04-28 02:00:19,993 ERROR o.e.m.p.AbstractParser [JavaFX Application Thread] error for input type oogasalad.engine.model.logicelement.actions.RemoveRay with parameters [I@380ad77f and resource null

2022-04-28 02:00:19,994 ERROR o.e.m.p.AbstractParser [JavaFX Application Thread] Error class: class java.lang.ArrayIndexOutOfBoundsException, message Index 7 out of bounds for length 6

2022-04-28 02:00:19,999 ERROR o.e.m.p.AbstractParser [JavaFX Application Thread] oogasalad.engine.model.parser.exception.ReferenceNotFoundException
2022-04-28 02:00:19,999 ERROR o.e.m.p.AbstractParser [JavaFX Application Thread] Throwing

## Hypothesis for Fixing the Bug
The error is in the constructor's last line:
```java
  /**
   *
   * @param parameters size 5 array [startRow, startColumn, rowDirection, columnDirection, length, isAbsolute]
   */
  public RemoveRay(int[] parameters) {
    super(parameters);
    startRow = getParameter(0);
    startColumn = getParameter(1);
    rowDirection = getParameter(2);
    columnDirection = getParameter(3);
    length = getParameter(4);
    isAbsolute = getParameter(7) != 0;
  }
```

it needs to be changed to


```java
isAbsolute = getParameter(5) != 0;
```