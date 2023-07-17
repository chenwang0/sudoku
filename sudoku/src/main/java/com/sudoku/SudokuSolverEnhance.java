package com.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuSolverEnhance extends SudokuSolver {

	private final Random rand = new Random();

	public SudokuSolverEnhance(int[][] sudoku) {
		super(sudoku);
	}

	public static SudokuSolverEnhance init() {
		int[][] sudoku = new int[9][9];
		return new SudokuSolverEnhance(sudoku);
	}

	// 生成初始模型
	public SudokuSolverEnhance generateShape() {
		List<Integer> tempList = new ArrayList<>(9);

		// start generate the first row
		for (int i = 1; i <= 9; i++) {
			tempList.add(i);
		}
		Collections.shuffle(tempList);
		for (int i = 0; i < 9; i++) {
			super.sudoku[0][i] = tempList.get(i);
		}
		// complete generate
		super.solve();
		return this;
	}


	public SudokuSolverEnhance upset(int removeNum) {
		int x = 0, y = 0;
		for (int i = 0; i < removeNum; i++) {

			do {
				x = rand.nextInt(super.sudoku.length);
				y = rand.nextInt(super.sudoku[x].length);
			} while (super.sudoku[x][y] == 0);

			// insure index is not `0`
			super.sudoku[x][y] = 0;
		}
		return this;
	}

	// 刷新模型(每次求解)
	public boolean refresh(int row, int col, int fillNum) {
		return !super.isValid(row, col, fillNum) || (super.sudoku[row][col] != 0);
	}



	public static void main(String[] args) {
		SudokuSolverEnhance sudokuSolverBuild = SudokuSolverEnhance.init().generateShape();
//		sudokuSolverBuild.printSolution();
		sudokuSolverBuild.upset(0);
		sudokuSolverBuild.printSolution();
		System.out.println(sudokuSolverBuild.isValid(0, 0, 1));

	}

}
