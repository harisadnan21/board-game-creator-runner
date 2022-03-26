# Design Plan

## Goals

In our project, we hope to create an application which allows users
to create games in the following domain:
* the board is 2d and “checkerboard-like” and convex
* There are no more than 2 players, which can be AI or human
* Rules are deterministic
* Sequential & not “real-time” turns
* Perfect information

We intend two create two functionally separate application, a builder and a game engine

### Builder

