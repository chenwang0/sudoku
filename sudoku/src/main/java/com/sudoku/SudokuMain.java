package com.sudoku;

public class SudokuMain {

    public static void main(String[] args) {
        int[][] sudoku = new int[9][9];
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
        if (sudokuSolver.solve()) {
            sudokuSolver.printSolution();
        } else {
            System.err.println("No solution found.");
        }

        sudokuSolver.sudoku[0][0] = 0;
        System.out.println(sudokuSolver.isValid(0, 0, 1));
    }
}
