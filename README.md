# MagicBoardSolver
A Magic Board looks like this: 
![image](https://user-images.githubusercontent.com/65039814/150510966-6856dcee-780c-4615-8105-cb95a6688eff.png)
The goal is to get to the 0 from one of the four corners. Each cell can take it a value from 1 to size-1.
A player is only allowed to move North, South, West or East. The value of the cell is the allowed number of steps.
For example, the above board would be solvable from the upper right corner by going south, west and south.

This programs generates a Magic Board of size s between 5 and 20 and takes in a start position. The program then returns whether or not the board is solvable, either recursively, or iteratively.

<i>This code was initally submitted in the context of COMP 352 Data Structures and Algorithm taught by Dr Nora Houari at Concordia University, fall 2020.</i>
