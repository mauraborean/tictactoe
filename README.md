# TicTacToe 2.0

## Getting Started
This project was developed using Test Driven Development (TDD). The project is divided in two modules that represents the core and the implementation of the game. The *core* contains all the classes and interfaces necessary to create a *TicTacToe* game, and in the implementation module are all the classes needed to create a console delivery of the *2.0* version.

Some of the design patterns used in the development of the projects are: Dependency injection, Strategy and Adapter.
 
The following instructions will allow you to install and play the Tic Tac Toe 2.0 on your computer.

### Prerequisites
Please, make sure you have installed the following tools:
- Java JDK 8
- Maven 3.3

### Installing the game

Download the project
```
git clone https://github.com/mauraborean/tictactoe.git
```

Go to the folder that contains the game
```
cd tictactoe
```

Let's install the game
```
mvn install
```

You can optionally go to the `configuration.properties` file and customize your game. The file is in the path:
```
cd consoleimplementation\src\main\resources
```

And now, we can run our game! Make sure you are on the original directory before executing the next command. 
```
mvn exec:java -q -pl consoleimplementation 
```

## Authors
Maura Borean