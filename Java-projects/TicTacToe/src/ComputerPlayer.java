import java.util.Map;
import java.util.HashMap;

public class ComputerPlayer {
	Map<String, Integer> allPermuations;
	char computerSymbol;
	char playerSymbol;

	public ComputerPlayer() {
		allPermuations = new HashMap<String, Integer>();
		computerSymbol = 'X';
		playerSymbol = 'O';
		generateAllPermuations();
	}
	
	public int getMove(String str){
		return allPermuations.get(str);
	}
	
	private void generateAllPermuations() { // generates all permutations (O(3^9))
		String temp = "";
		int count = 0;
		char[] symbols = { '-', 'X', 'O' };
		char[] permutations = new char[9];

		for (int a = 0; a < 3; a++) { // 1
			permutations[0] = symbols[a];
			for (int b = 0; b < 3; b++) { // 2
				permutations[1] = symbols[b];
				for (int c = 0; c < 3; c++) { // 3
					permutations[2] = symbols[c];
					for (int d = 0; d < 3; d++) { // 4
						permutations[3] = symbols[d];
						for (int e = 0; e < 3; e++) { // 5
							permutations[4] = symbols[e];
							for (int f = 0; f < 3; f++) { // 6
								permutations[5] = symbols[f];
								for (int g = 0; g < 3; g++) { // 7
									permutations[6] = symbols[g];
									for (int h = 0; h < 3; h++) { // 8
										permutations[7] = symbols[h];
										for (int j = 0; j < 3; j++) { // 9
											permutations[8] = symbols[j];
											allPermuations.put(convertPermuationToString(permutations),associateBestMove(permutations));
										}
									}
								}
							}
						}
					}
				}
			}

		}
	}

	private String convertPermuationToString(char[] arr) { // converts permuations to a String 
		String str = "";								  // O(n);

		for (int i = 0; i < arr.length; i++) {
			str += arr[i];
		}
		
		return str;
	}

	private int associateBestMove(char[] arr) { // associates the permutation with the next best move
		return bestMove(arr);
	}
	
	/* https://en.wikipedia.org/wiki/Minimax
	 * Uses a minimax algorithm, which can be applied to turn based games. It rates
	 * permutations based on whether the following moves will lead to a win for
	 * the computer or player. Permutations that lead to a player's win receive
	 * negative ratings, and permutations that lead to a computer win receive
	 * positive ratings. Checks preceding turns until their is a win, or no more
	 * turns
	 */
	public int bestMove(char[] arr) { //O(n)*O(n^m) = O(n^(m+1))
		int value = Integer.MIN_VALUE, bestMove = -1;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '-') {
				arr[i] = computerSymbol;
				int tempValue = minValue(arr);
				if (value < tempValue) {
					value = tempValue;
					bestMove = i;
				}
				arr[i] = '-';
			}
		}

		return bestMove;
	}
	
	// O(n^m) n being how many more calls need to be made, and m being how many '-' spots
	public int minValue(char[] arr) { // minimizer in minimax algorithm
		int value = Integer.MAX_VALUE;

		if (hasWin(playerSymbol, arr)) {
			return -1;
		} else if (hasWin(computerSymbol, arr)) {
			return 1;
		} else if (outOfTurns(arr)) {
			return 0;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '-') {
				arr[i] = playerSymbol;
				int temp = maxValue(arr);
				value = Integer.min(value, temp);
				arr[i] = '-';
			}
		}
		return value;

	}
	
	// O(n^m) n being how many more calls need to be made, and m being how many '-' spots
	public int maxValue(char[] arr) { // maximizer in minimax algorithm
		int value = Integer.MIN_VALUE;

		if (hasWin(computerSymbol, arr)) {
			return 1;
		} else if (hasWin(playerSymbol, arr)) {
			return -1;
		} else if (outOfTurns(arr)) {
			return 0;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '-') {
				arr[i] = computerSymbol;
				int temp = minValue(arr);
				value = Integer.max(value, temp);
				arr[i] = '-';
			}
		}

		return value;

	}
	
	private boolean hasWin(char symbol, char[] arr) { // determines if there is a win
		return (arr[0] == symbol && arr[1] == symbol && arr[2] == symbol)
				|| (arr[3] == symbol && arr[4] == symbol && arr[5] == symbol)
				|| (arr[6] == symbol && arr[7] == symbol && arr[8] == symbol)
				|| (arr[0] == symbol && arr[3] == symbol && arr[6] == symbol)
				|| (arr[1] == symbol && arr[4] == symbol && arr[7] == symbol)
				|| (arr[2] == symbol && arr[5] == symbol && arr[8] == symbol)
				|| (arr[0] == symbol && arr[4] == symbol && arr[8] == symbol)
				|| (arr[2] == symbol && arr[4] == symbol && arr[6] == symbol);
	}

	private boolean outOfTurns(char[] arr) { // checks if there are turns left
		for (int i = 0; i < arr.length; i++) { // O(n);
			if (arr[i] == '-')
				return false;
		}

		return true;
	}
}
