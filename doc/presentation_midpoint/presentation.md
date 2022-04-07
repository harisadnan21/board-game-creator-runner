# Team OOGABOOGA Midpoint Demo

## Goals

### Strategy Games

In our project, we hope to create an application which allows users
to create strategy games in the following domain:
* the board is 2d and “checkerboard-like” and convex
* There are no more than 2 players, which can be AI or human
* Rules are deterministic
* Sequential & not “real-time” turns
* Perfect information

We intend two create two functionally separate application, a builder and a game engine. The builder
will be responsible for allowing users to create game configurations that specify the board, pieces,
rules, and conditions of a game. The engine is responsible for loading these configurations and
allowing users to play the games.

### Features

#### Builder

Users should be able to create a new game configuration, which involves:
- Creation of new Pieces
  - Images
  - Names
  - Behavior
- Creation of new Game Rules
  - Conditions
  - Actions
- Creation of win conditions
- Modification of the Board
  - Dimensions
  - Background
  - Piece Configuration

Users should be able to save and load game configurations so that they can be played on the engine.

#### Engine


### Sprint Priorities

#### Test Sprint
Creation of all APIs and basic implementations of the board, pieces, and view elements with a focus 
on writing tests for all code.

#### Sprint 1

Support for:
- Custom Pieces
- Custom Actions
- Custom Conditions
- Custom Rules
- Custom Board Configurations

In both the builder and the engine.

#### Sprint 2

Adding support for custom win conditions, as well as the basic implementation of extension features 
like artificial agents and networked games. Polishing the builder UI to make it more intuitive to 
users who wish to create games.

#### Complete Sprint

Finish implementation of extension features, as well as improving the UI for the builder and engine.
Adding more options for action, condition, and win condition creation.

### Project Management

Weekly team-wide meetings, with smaller meetings happening more often between the engine and builder
sub-teams. 

Use of Gitlab issues and code reviews to keep track of progress and future plans.

## Design

### Design and Framework Goals

#### Open


#### Closed

### Module Layout

- builder
  - controller - Defines the Controller that interfaces between the view and model
  - model - Contains the modules and API for configuring a game and converting it to JSON
    - board - Defines the Board
    - element - Defines the API for creating Game Elements (pieces, actions, rules, conditions)
    - exception - Custom exceptions that are thrown in errant cases
    - property - Defines an API for creating and accessing arbitrary properties of game elements.
  - view - Contains all UI elements of the builder
    - tab - Describes the different tabs that are present in the Builder View.
- engine


### APIs

#### API #1
what service does it provide?
how does it provide for extension?
how does it support users (your team mates) to write readable, well design code?


#### API #2

what service does it provide?
how does it provide for extension?
how does it support users (your team mates) to write readable, well design code?

### Use cases

### Alternative Design & Tradeoffs



## Current Functionality

### Builder Demo

### Engine Demo

### Example Data Files

### Tests