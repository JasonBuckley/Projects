import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button {
	private int number;
	private JButton button;

	public Button(int num, int x, int y, int width, int height) { // constructor
		number = num;
		button = new JButton();
		button.setBounds(x, y, width, height);
		button.addActionListener(new change());
	}

	public JButton getJButton() {
		return this.button;
	}

	public void setButton(String str) {
		button.setText(str);
	}

	public String getButtonText() { // sends the Buttons text
		return button.getText();
	}

	class change implements ActionListener { //Changes the Button to the players symbol if it is a legalMove

		public void actionPerformed(ActionEvent event) {
			if (TicTacToe.isMoveLegal(number) && !TicTacToe.getFoundMove()) {
				button.setText("O");
				TicTacToe.setFoundMove(true);
				
			} else {
				//System.out.println("not legal");
			}
			
		}
	}
}
