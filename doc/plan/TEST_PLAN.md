# OOGASalad Test Plan

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


## Testing Strategies

There are a few strategies our team discussed to make our code more testable.

1. The first strategy discussed was to make clear what objects different methods mutated the states of (either directly or indirectly) in the program and also to make clear which object's states those methods relied on. Speaking more concretely, we discussed how passing paremeters into methods from which they can obtain necessary data to do their functionality rather than relying on the object that method is using to have its own references to objects makes for a more testable code base because it makes it easier to ensure that a given method call will lead to a given return value. This also helps our code adhere to the principle of dependency injection.

2. Another strategy to make our code more testable is to think about making sure our code obeys certain properties, instead of just passes certain unit tests. For instance, when I (Alex) write the AI Player, part of my testing will be to test that when I add Alpha-Beta pruning to the search tree that this never changes to result of what move to make, because Alpha-Beta pruning should only make the AI more efficient, and not change its decision. Thinking in terms of these "properties" allows me to more rigorously test to try to ensure the correctness of my code rather than an approach where I just tried to make sure that Alpha-Beta pruning returned the correct value for a few test cases.

## Specific Test Cases for Features

### Ricky - Creating Game Elements in the Builder (ElementFactory)
1. Make sure that a piece is created with the correct properties based on the input
2. Verify that an exception is thrown when an unknown element type is given
3. Verify that missing parameters are replaced by defaults when elements are created
### Jake - Apply Rules to Pieces
1. Verify that a rule can be applied to a piece
2. Verify that a rule accurately restricts moves that a piece can make
3. Verify that applying conflicting rules to a piece throws an exception
### Robert - Managing Pieces on the Board
1. Verify that pieces can be placed on cells of the board
2. Verify that pieces can be moved on a board, clearing the old cell and occupying a new one
3. Verify that an exception is thrown when a piece moves out of bounds of the board 
### Mike - Board Configuration in Builder View
1. Verify that pieces can be placed on cells of the board (in the view)
2. Verify that pieces can be moved on a board, clearing the old cell and occupying a new one (in the view)
3. Verify that an error message appears when a piece is attempted to be placed on an occupied cell (in the view)
### Haris - JSON Loading in Builder
1. Verify that the loader properly parses a board configuration
2. Verify that the loader properly parses a piece and its rules
3. Verify that loading a JSON file missing a required element will throw an exception
### Alexander - Simple Artificial Player
1. Verify that any artificial player can play a move
2. Verify that an artificial player can recognize when no moves can be made (and potentially throw an exception in this case)
3. Verify that an optimal artificial player always plays the optimal move
### Thivya - Allow New Piece Creation in Piece Tab
1. Verify that the Piece Creation Button works as expected
2. Verify that a created piece is added to the view in the pieces tab
3. Verify that an exception is thrown when the user attempts to create a new piece with an existing name
### Cynthia - Display Board and Pieces in View
1. Verify that board is displayed properly based off of an initial configuration
2. Verify that the board updates correctly based on a new configuration
3. Verify that an exception is thrown when the new configuration of the board is "incompatible" with the old configuration (different board size, etc.)
### Shaan - Board Configuration in Builder Model
1. Verify that pieces can be placed on cells of the board
2. Verify that pieces can be moved on a board, clearing the old cell and occupying a new one
3. Verify that an exception is thrown when a piece is attempted to be placed on an occupied cell.