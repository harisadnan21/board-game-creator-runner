oogasalad
====

This project implements an authoring environment and player for multiple related games.

<img width="597" alt="Screen Shot 2022-08-17 at 6 09 11 PM" src="https://user-images.githubusercontent.com/91027112/185253098-53b1c585-5495-4a8a-8bca-8619fdca837e.png">


<img width="892" alt="Screen Shot 2022-08-17 at 6 11 36 PM" src="https://user-images.githubusercontent.com/91027112/185253063-11bbd4c3-125c-49d8-bb27-d2a4ddc8f7b7.png">

<img width="795" alt="Screen Shot 2022-08-17 at 6 22 55 PM" src="https://user-images.githubusercontent.com/91027112/185254146-967698c4-9300-4ffe-b4ae-6ff9f82d5b64.png">

<img width="795" alt="Screen Shot 2022-08-17 at 6 22 44 PM" src="https://user-images.githubusercontent.com/91027112/185254169-d08ed61c-2423-4d2c-b25b-18d18ec9edb0.png">

<img width="792" alt="Screen Shot 2022-08-17 at 6 23 58 PM" src="https://user-images.githubusercontent.com/91027112/185254195-7e8bc802-0f84-4d81-b641-5b6f8509df03.png">



Builder:

<img width="574" alt="builder" src="https://user-images.githubusercontent.com/91027112/185253649-2aafb512-27f7-41f1-8103-89d0ef32a4a2.png">

Engine:

<img width="582" alt="engine" src="https://user-images.githubusercontent.com/91027112/185253683-149a8b18-6ade-4bfa-ba28-8369871ec8eb.png">


### Timeline

Start Date: 3/17/22

Finish Date: 4/25/22



### Resources Used

* [JSON Parsing](https://kodejava.org/how-do-i-read-json-file-using-json-java-org-json-library/)
* [JSON Serialization](https://www.baeldung.com/java-org-json)
* [Log4j2 Logging](https://www.baeldung.com/java-system-out-println-vs-loggers)
* [Function Memoization](https://github.com/ben-manes/caffeine/wiki/Population#loading)


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
* AI capable of playing any game created in the builder, with customizable difficulty levels, including the ability to adapt its strength to match the player
* Localization support for multiple languages
* Help page that describes all possible Actions, Conditions, Win Conditions, and other parameters of games during creation
* Support for multiple themes
* Support for multiple langauges
* user-interactive sounds
* Support for multiple/no sounds


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

### Cheat Codes

O -> RemovePlayer1Piece(),

Z -> new RemovePlayer0Piece(),

S -> ShuffleBoard(),

1 -> PlayerOneWins(),

2 -> PlayerTwoWins(),

R -> Reset(),

L -> Player1Turn(),

K -> Player2Turn(),

I -> IncrementPlayer(),

P -> CopyRandomPiece());

### Impressions

This project was difficult, and it took a lot of teamwork and communication to make sure we were able to 
implement the features we originally planned to. It covered all of the principles that we've learned
in class up to this point, and served as a challenging and rewarding project to end this course.
