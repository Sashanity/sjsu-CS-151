package controller;


/**

 * Controls and respond to user actions (pressed buttons such as newgame, solutions etc)
 * event handling
 * @author Aleksandra, Ben, Jefferson
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Game;
import view.Board;
public class SudokuController implements ActionListener{
	
	private Board sudokuBoard;
	private Game game;
	
	/**
	 * 
	 * @param sudokuBoard the current board
	 * @param game is the numerical board situation
	 */
	 public SudokuController(Board sudokuBoard, Game game) {
		 this.sudokuBoard = sudokuBoard;
		 this.game = game;
		 
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	 
	 //events
		
		 

}
