package com.iiikn.sudoku.view.component;

import com.iiikn.sudoku.view.SudokuView;
import com.iiikn.sudoku.view.SudokuViewComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

/**
 * 系统操作区域组件
 */
public class SudokuSysExitButtonComponent extends SudokuViewComponent {

 	private final JButton closeButton = new JButton();
	public SudokuSysExitButtonComponent(JFrame sudokuFrame) {
		super(sudokuFrame);
	}

	@Override
	public void initialize() {
		URL resource = SudokuView.class.getClassLoader().getResource("btn_close.png");
		try {
			assert resource != null;
			closeButton.setIcon(new ImageIcon(ImageIO.read(resource)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// closeButton.setFocusPainted(false);
		// closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		closeButton.setBounds(10,10, 15, 15);
		closeButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void addToFrame() {
		sudokuFrame.add(closeButton);
	}
}
