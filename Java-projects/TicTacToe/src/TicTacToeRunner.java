import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
	Author: Jason Buckley

	Abstract: A Unbeatable game of Tic Tac Toe in which the computer has all the possible moves, and the
	knows the next best move to make.  The user can tie with the computer; however, they will never win
	the game.

	Date: 4 December 2019
 */

public class TicTacToeRunner {
	static JFrame window = new JFrame();
	
	public static void main(String[] arg) {
		window.setLayout(null);
		window.setSize(425, 575);
		window.setVisible(true);
		window.setResizable(false);
		window.setTitle("TicTacToe");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}
	
	public static void initialize(){
		JPanel insideWindow = new JPanel(null);
		
		TicTacToe game = new TicTacToe();
		insideWindow.setSize(425,575);
		
		for (int i = 0; i < 9; i++) {
			insideWindow.add(game.sendBoard()[i].getJButton());
		}
		
		insideWindow.add(game.sendText());
		
		window.add(insideWindow);
		window.repaint();
		insideWindow.setVisible(true);
		
		game.game();
		restart();
	}

	public static void restart(){
		window.getContentPane().removeAll();
		window.repaint();
		initialize();
	}
	
	
}
