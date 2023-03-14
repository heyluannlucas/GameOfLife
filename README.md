<h1 align="center"> Game Of Life </h1>

 This program is a graphical implementation of Conway's Game of Life, a cellular automaton where a cell can be either "alive" or "dead" on a two-dimensional grid. The program presents a grid of cells and allows the user to interact with it by clicking on cells to make them alive or dead. The program also follows the four rules of the game to automatically update the status of each cell on the grid.

The program is written in Java and uses the Swing library for the GUI.

#
## Usage ✍🏻 
<br>
To use this program, simply run the Start class. The program will display a window with a grid of gray cells. Click on any cell to toggle its status between "alive" and "dead". Press the "Start" button to start the simulation and see how the cells evolve based on the four rules of the game. Press the "Stop" button to stop the simulation.
The "Restart" button resets the grid to its initial state with all cells set to "dead".

#
## Implementation 👩‍🏫 
<br>
The program is implemented as a JFrame that contains three JButtons and a JPanel with a grid layout. The grid is implemented as a two-dimensional boolean array, where true represents an "alive" cell and false represents a "dead" cell. Each cell is displayed as a JPanel with a border and a gray or yellow background depending on its status.

The program follows the four rules of the game by computing the number of "living" neighbors for each cell and updating the status of the cell based on its current status and the number of neighbors. The simulation is run in a separate thread to avoid blocking the GUI.
>* Any live cell with fewer than two live neighbors dies, as if by underpopulation.
>* Any live cell with two or three live neighbors lives on to the next generation.
>* Any live cell with more than three live neighbors dies, as if by overpopulation.
>* Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The program also displays the number of cell updates that have occurred since the simulation was started.

#
## Dependencies 🛠
<br>
This program requires Java 8 or later and uses the Swing library for the GUI. No additional dependencies are required.

#
Created by [Luann Lucas](https://github.com/heyluannlucas) 👨🏼‍💻

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)