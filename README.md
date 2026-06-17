# Sudoku Solver

Java Swing application that solves Sudoku puzzles using recursive backtracking.

## Requirements

- JDK 22+

## Run

From the repository root:

```bash
javac -d out src/*.java
java -cp out Main
```

Enter the known puzzle values in the grid, then click `Solve`.

## Project Layout

- `src/Main.java` starts the Swing UI.
- `src/GUI.java` renders the puzzle grid and controls.
- `src/Solution.java` contains the backtracking solver.
