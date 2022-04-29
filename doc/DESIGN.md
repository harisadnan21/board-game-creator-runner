# DESIGN Document for OOGASalad
## NAMES

* Ricky Weerts
* Jake Heller
* Robert Cranston
* Haris Adnan
* Alexander Bildner
* Thivya Sivarajah
* Cynthia France
* Mike Keohane
* Shaan Gondalia

## Role(s)

#### Engine

* Cynthia - Worked entirely on the engine view, including all View classes with the exception of Dashboard
* Robert - Worked a lot on linking the front end and backend as well as designing elements in each
* Haris - Engine model
* Alexander - Engine model, mostly Board (Board itself, Board utilities, Ray, etc), AI package, and some Win Conditions
* Jake - Engine model

#### Builder

* Ricky - Initially worked on builder model, then moved to doing whatever needed to be done across the builder
* Thivya -
* Mike - Implemented the Tabs and worked everywhere in the builder view
* Shaan - Implemented much of the builder model (elements, properties, board, and game configuration) and game configuration parsing/serialization for the builder and engine.

## Design Goals

The goal of this project was to create a game engine and authoring environment that could support any
two-dimensional grid-based strategy game. The engine would have to be able to support games with any
possible combination of rules and pieces, which required the creation of abstractions that allowed 
generalizations of game configurations. 

The authoring environment would have to allow users to use these generalizations to configure their 
own games with arbitrary sets of rules, boards, and pieces. The user interface for this authoring 
environment would have to be friendly for non-technical users, so that anyone could create and 
modify games however they wanted to.

The original games that we were aiming to support were Checkers, Tic-Tac-Toe, and Othello. We felt 
that if these games were supported, our design could reasonably support any number of alterations 
of these games. Also, we wished to support games that have more than 2-players.

As an extension, we aimed to create an AI player that could play any game that a user configures.

## High-Level Design

The implementation of this project was split into two sub-projects, the Engine (game player) and the
Builder (authoring environment). The goal was to have these two programs function independently, 
with the only link between the two being the JSON configuration format for defining a game and a 
single splash screen that would allow users to navigate to the Builder or Engine accordingly. This
separation meant that we could parallelize the work on the player and game authoring environment.

### Engine


### Builder
The builder implements an MVC architecture. The view dispatches callbacks to the controller, which
calls the model to update the game configuration accordingly.

#### Model

The model is composed of a few major APIs that dictate how information is stored in the back-end of
the builder. The `BuilderModel` is a single API that provides access methods for modifying all data in
the model (so that the controller has a single external API to call). 

The `Property` API describes how properties are stored in the builder. A Property is just some 
information about a game element (such as the name of a piece or rule). Properties are immutable, 
but were opted to be a class instead of a record because we felt that an `AbstractProperty` class 
would reduce code repetition.

The `Element` API represents a single game element in the configuration (i.e. an entire piece or 
rule). Elements are essentially collections of properties. The `ElementFactory` API has methods for 
creating elements, including retrieving the required properties for certain types of elements. The 
`FactoryProvider` implements the [Abstract Factory Pattern](https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm), 
providing methods for creating elements without the need for concrete declarations of ElementFactories.

The `Board` API stores information about the board. This includes size, shape, piece configuration,
and cell colors. While the cell colors are only needed in the view for display purposes, they must
be stored in the model in order to be serialized to JSON.

The `JSONParseable` and `JSONSerializable` interfaces each provide a single method that determines how
a class is parsed or serialized (reading or loading from JSON). Using this interface standardized 
the process of parsing and serialization, and meant that there did not need to be a single 
Parser/Serializer that held all information about saving and loading a game configuration from JSON.
We felt that this design better adhered to the Single Responsibility Principle and active classes.

#### View

The view is composed of several basic JavaFX view classes as well as several abstractions. The view
is split into Tabs, which are each responsible for the creation of a single game element. These tabs
all implement the `BasicTab`, which is the internal API for creating a tab in the builder.

Each tab has a `GameElementList` (lists existing game elements) and PropertySelectors.
`PropertySelector` is an API that defines general methods for allowing users to set the values of 
certain properties. For instance, an `IntegerSelector` prompts users to select integers and a 
`BooleanSelector` prompts users to select a boolean value.

The view communicates with the controller by dispatching `Callback`s, which are handled by the 
controller using a Listener based system. The view obtains information about the existing game
configuration from the model using these Callbacks. The view does not store any information about 
the game elements other than their names, and retrieves the necessary information if users wish
to edit existing elements.

## Assumptions or Simplifications

* Game configurations must be composed of a JSON file named `config.json` and a `resources` folder with piece images.
* Used common naming conventions throughout the builder in order to write to the JSON correctly.
* When developers create new types of Actions, Conditions, or WinDecisions (or change parameters) in
the engine, they must also make those changes to properties files in the builder if they should be supported.

## Changes from the Plan

### Builder

The `GameLoader` and `GameSaver` APIs were replaced with `JSONParseable` and `JSONSerializable`. 
This change was justified as it made classes more active and eliminated the need for a single object
that had too many responsibilities.

The `PropertySelector` internal API was created to allow the view to have multiple ways of allowing
the user to input values into properties.

The `Callback` API was added to eliminate the need for each view tab to have access to the controller.

Otherwise, all APIs stayed the same, except for some methods that were added (none were removed) as 
we implemented more features (such as cell background coloring, etc.).

### Engine


## How to Add New Features

