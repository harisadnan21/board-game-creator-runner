oogasalad
====

This project implements an authoring environment and player for multiple related games.

Names:

* Ricky Weerts
* Jake Heller
* Robert Cranston
* Haris Adnan
* Alexander Bildner
* Thivya Sivarajah
* Cynthia France
* Mike Keohane
* Shaan Gondalia


### Timeline

Start Date: 3/17/22

Finish Date: 4/25/22

Hours Spent:

* Ricky - 70 Hours
* Jake -
* Robert -
* Haris -
* Alexander -
* Thivya -
* Cynthia - 
* Mike - 65 Hours
* Shaan - 80 Hours

### Primary Roles

#### Engine
* Cynthia -
* Robert -
* Haris -
* Alexander -
* Jake -


#### Builder

* Ricky - Initially worked on builder model, then moved to doing whatever needed to be done across the builder
* Thivya -
* Mike - Implemented the Tabs and worked everywhere in the builer view
* Shaan - Implemented much of the builder model and game configuration parsing/serialization for the builder and engine.


### Resources Used

* [JSON Parsing](https://kodejava.org/how-do-i-read-json-file-using-json-java-org-json-library/)
* [JSON Serialization](https://www.baeldung.com/java-org-json)
* [Log4j2 Logging](https://www.baeldung.com/java-system-out-println-vs-loggers)
* 


### Running the Program

Main class: oogasalad.Main.java

Data files needed: 

* `data/images`: Contains common images for the engine and builder, such as the back error key and save button
* `data/games`: Contains preset game configurations that can be loaded into the builder or engine. A game is composed of a directory that contains a `config.json` file, a `resources` folder containing piece images, and an icon for the game.
* `data/sounds`: Contains sound effects for the builder and engine
* `data/tests`: Needed for running unit tests

Features implemented:

* Games:
  * Checkers
  * Othello
  * Tic-Tac-Toe
  * Minesweeper
* Game Builder that allows creation and modification of customizable 2-dimensional board strategy games
* Game Engine that allows the playing of 2-dimensional board strategy games
* Saving and loading of custom JSON game configurations
* AI players with multiple difficulty levels that are not game-specific
* Localization support for multiple languages
* Help page that describes all possible Actions, Conditions, Win Conditions, and other parameters of games during creation
* Support for multiple themes


### Notes/Assumptions

Assumptions or Simplifications:

* Game configurations must be composed of a JSON file named `config.json` and a `resources` folder with piece images.
* Used common naming conventions throughout the builder in order to write to the JSON correctly.

Interesting data files:

* `data/minesweeper`: Minesweeper built in our game engine

Known Bugs:

Extensions completed:
* AI Players
* Fully customizable game layout


### Impressions

This project was difficult, and it took a lot of teamwork and communication to make sure we were able to 
implement the features we originally planned to. It covered all of the principles that we've learned
in class up to this point, and served as a challenging and rewarding project to end this course.
