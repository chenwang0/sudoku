package com.sudoku;

public class SudokuSolver {

    public final int[][] sudoku;

    public SudokuSolver(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public boolean solve() {
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                if (sudoku[row][col] == 0) {
                    for (int idx = 1; idx <= 9; ++idx) {
                        if (isValid(row, col, idx)) {
                            sudoku[row][col] = idx;
                            if (solve()) {
                                return true;
                            } else {
                                sudoku[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int row, int col, int fillNum) {
        for (int i = 0; i < 9; i++) {
            int rowBlock = 3 * (row / 3) + i / 3;
            int colBlock = 3 * (col / 3) + i % 3;
            if (sudoku[row][i] == fillNum || sudoku[i][col] == fillNum || sudoku[rowBlock][colBlock] == fillNum) {
                return false;
            }
        }
        return true;
    }

    public void printSolution() {
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                System.out.print(sudoku[row][col] + " ");
            }
            System.out.println();
        }
    }

}
