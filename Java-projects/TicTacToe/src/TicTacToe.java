import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TicTacToe {
	// char[] board;
	private static Button[] board;
	// private JFrame game;
	private static boolean foundMove;
	//private static int turn;
	private ComputerPlayer comp;
	//private JButton restartButton;
	private JTextArea text;
	private Font font;

	public TicTacToe() { // constructor
		// board = new char[9];
		board = new Button[9];
		setButtons();
		
		foundMove = false;
		
		text = new JTextArea();
		font = new Font(Font.SERIF, 40, 40);
		text.setFont(font);
		text.setVisible(false);
		text.setBounds(50,10,300,75);
		text.setEditable(false);
		
		comp = new ComputerPlayer();
	}

	public boolean isEmpty(int position) { // checks if a Button (square) is empty
		return board[position].getButtonText().equals("");
	}
	
	public JTextArea sendText(){
		return text;
	}
	
	

	public static void setFoundMove(boolean bool) {
		foundMove = bool;
	}
	
	public static boolean getFoundMove(){ 
		return foundMove;
	}

	private void setButtons() { // sets the button all to "" indicating no move has been made.
		int placeRight = 1;
		int placeDown = 1;
		for (int i = 0; i < 9; i++) {
			board[i] = new Button(i, 100 * placeRight - 50, 100 * placeDown, 100, 100);
			board[i].setButton("");
			placeRight++;

			if (placeRight > 3) {
				placeRight = 1;
				placeDown++;
			}
		}

	}
	

	public Button[] sendBoard() { // sends the board, use to add the board in the runner class.
		return board;
	}

	private static String boardToString() { // converts board to String
		String str = "";

		for (int i = 0; i < board.length; i++) {
			if (!board[i].getButtonText().equals(""))
				str += board[i].getButtonText();
			else
				str += '-';
		}

		return str;
	}

	public void game() { // runs the game
		foundMove = false;
		int turn = 0;
		
		while (!isEnd(turn)) {
			if (turn % 2 == 0) {
				while (!foundMove)
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				foundMove = false;
			} else {
				board[comp.getMove(boardToString())].setButton("X");
			}
			turn++;
			
		}
		foundMove = true; //using foundMove here to prevent the player from placing their symbol after the game is over
		
		if (whoIsWinner() == 'X') {
			text.setText("  Computer wins");
		} else if (whoIsWinner() == 'O') {
			text.setText("  Player wins");
		} else {
			text.setText("            Tie");
		}
		
		text.setVisible(true);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}

	public void computersMove() { // does the computers move
		int n = comp.getMove(boardToString());
		board[n].setButton("X");
		board[n].getButtonText();

	}

	public static boolean isMoveLegal(int move) { // checks if the move is within bounds, and not rewriting a move already made
		if (move < 0 || move > 8) {
			return false;
		} else if (board[move].getButtonText().equals("")) {
			return true;
		}

		return false;
	}

	public boolean isEnd() { // if there is a win it ends the game
		return checkForWin("X") || checkForWin("O");
	}
	
	public boolean isEnd(int turn) { // same as previous, but now looks for if there out of turns
		return checkForWin("X") || checkForWin("O") || turn == 9;
	}

	public char whoIsWinner() { // finds out who is the winner
		if (checkForWin("X")) {
			return 'X';
		}
		if (checkForWin("O")) {
			return 'O';
		}

		return '-';
	}

	public boolean checkForWin(String symbol) { // checks if a player or the computer wins
		return (board[0].getButtonText().equals(symbol) && board[1].getButtonText().equals(symbol)
				&& board[2].getButtonText().equals(symbol))
				|| (board[3].getButtonText().equals(symbol) && board[4].getButtonText().equals(symbol)
						&& board[5].getButtonText().equals(symbol))
				|| (board[6].getButtonText().equals(symbol) && board[7].getButtonText().equals(symbol)
						&& board[8].getButtonText().equals(symbol))
				|| (board[0].getButtonText().equals(symbol) && board[3].getButtonText().equals(symbol)
						&& board[6].getButtonText().equals(symbol))
				|| (board[1].getButtonText().equals(symbol) && board[4].getButtonText().equals(symbol)
						&& board[7].getButtonText().equals(symbol))
				|| (board[2].getButtonText().equals(symbol) && board[5].getButtonText().equals(symbol)
						&& board[8].getButtonText().equals(symbol))
				|| (board[0].getButtonText().equals(symbol) && board[4].getButtonText().equals(symbol)
						&& board[8].getButtonText().equals(symbol))
				|| (board[2].getButtonText().equals(symbol) && board[4].getButtonText().equals(symbol)
						&& board[6].getButtonText().equals(symbol));
	}

	
}
