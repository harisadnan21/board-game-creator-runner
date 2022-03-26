# OOGASalad Team Contract

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

## Implementation Plan

### General Plan

We split the project into the Builder and the Engine, each having a model, view and controller. The 
only form of "communication" between the Builder and the Engine is the JSON file that represents a
game configuration. Once this schema is well-defined, both teams can work independently to implement
the desired features.

The extension features we plan to implement are artificial players and networked games. These 
extensions will be worked on throughout the development process.

In the first week, we expect to have basic game configurations working. This means that the builder
can configure a board with pieces and save it to a configuration file, and the engine can load 
configuration files and display pieces.

In the second week, we expect to implement more complicated pieces, rules, and winning conditions in
both the builder and engine. In this week, we will make the game "playable" and "winnable". We will
also complete the implementation of simple artificial players.

In the third week, we expect to polish the UI surrounding game creation and playing, as well as 
creating the finalized pieces, rules, and winning conditions (more complex options for the users).
We will continue working on artificial players and begin work on the networked games.

In the fourth week, we will complete our extension changes, implementing all networking and 
artificial players. We will also spend time refactoring our code and polishing our design, as well
as thoroughly testing the entire functionality of the project.

### Test Sprint

#### Builder

* Ricky - Build basic game Elements, Property, and ElementFactory
* Thivya - Create view for ElementTabs that allow the customization of pieces, rules, and winning conditions
* Mike - Create view for board layout (piece placement and board dimensions)
* Shaan - Create Board, GameSaver (json serializer), Back-end manager class, and controller

#### Engine

* Jake - Create Rules that dictate piece movement, collisions, and actions
* Robert - Create Piece and Board class
* Haris - Create Controller and JSON Loader
* Alexander - Create artificial players
* Cynthia - Create view for Board and Pieces


### Sprint 1

#### Builder

* Ricky - Implement Editing of Game elements and assigning rules to players (Back-end)
* Thivya - Continue work on ElementTab views, allowing the creation of win conditions
* Mike - Implement Editing of Game elements and assigning rules to players (UI)
* Shaan - Maintain JSON saver with newly implemented options and implement Game Loading in the builder

#### Engine

* Jake - Integrate Rule checking for pieces
* Robert - Implement win conditions and integrate wins/losses with the controller.
* Haris - Maintain JSON loader with newly implemented options and implement Game Saving in the engine
* Alexander - Continue working on artificial players
* Cynthia - Add Splash Screens for winning, losing, and draws. Add features to board view such as active player, undo buttons, etc.

### Sprint 2

#### Builder

* Ricky - Implement multiple rules for pieces, game-wide rules, and piece-specific winning conditions
* Thivya - Polish UI for Tabs
* Mike - Polish UI for Board Layout, including creating more customization options such as background image
* Shaan - Implement configuration loading (and editing) in builder. Maintain JSON saver.


#### Engine

* Jake - Work on networked players
* Robert - Continue work on more complex win conditions
* Haris - Maintain JSON loader and work on networked players
* Alexander - Finalize work on artificial players
* Cynthia - Polish UI for saving, loading, and playing games (display possible moves, etc.)

### Complete Sprint

As a whole, we will complete our extension changes, polish UIs, and refactor code. We will 
thoroughly test our project during this week, finalizing the implementation of any incomplete
features.