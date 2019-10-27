package Game;

import Beasts.Beast;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game {
	private static JPanel gameOverPanel = new JPanel(null); // lets the player know they lost
	private static JPanel game = new JPanel(null); // main game panel.
	private static Player player = new Player(); // creates a player
	private static int turnPoints; // used to determine how moves the player has left
	private static ArrayList<Beast> beasts = new ArrayList<Beast>(); // stores the beasts for the waves
	private static JMenuBar optionMenu = new JMenuBar(); // houses all the actions for the player.
	private static JMenu moves = new JMenu("Moves"); // holds all the attack moves
	private static JPanel optionPanel = new JPanel(); // holds the optionMenu
	private static JLabel gameOverLabel = new JLabel("Game Over"); // tells the player that they lost.
	private static JMenu items = new JMenu("Items"); // holds the players items actions
	private static int bossTurn = 1; // determines when a boss can spawn
	//----------------------------------------
	private static JTextArea combatLog = new JTextArea(); // displays battle actions
	private static String update = ""; // used to update the combat log.
	
	
	public static void game() { // runs the game.
		game.setSize(720, 480); // creates the game panel
		gameOverPanel.setVisible(false);
		game.setBackground(Color.gray);
		Shop.shop();                 // initializes and adds the shop to the game panel
		Shop.addShopScreen(game); 

		AttributeSelection.addlevelingUpScreen(game); // initializes and creates the attribution selection screen (leveling up screen).
		AttributeSelection.levelUpScreen();

		optionPanel.setBounds(270, 300, 300, 200); // creates the option panel that is the frame for the player moves
		game.add(optionPanel);
		optionMenu.setSize(300, 200);
		optionPanel.add(optionMenu);
		optionMenu.add(moves);

		JMenuItem attack = new JMenuItem("Attack"); // creates the attack option 
		attack.setSize(40, 20);
		moves.add(attack);

		JMenuItem order66 = new JMenuItem("Order 66 (5)"); // creates the order 66 option
		moves.add(order66);
		
		JMenuItem fireOrb = new JMenuItem("Fire Orb (2)"); // creates the fireorb option
		moves.add(fireOrb);
		
		JButton defend = new JButton("Defend"); // creates the defend option
		defend.setSize(40, 20);
		optionMenu.add(defend);

		items.setSize(100, 20); // creates the item bag option
		optionMenu.add(items);

		player.setLocation(0, 160); // places the player
		game.add(player);
		
		game.add(combatLog); // addes the combatLog to the game.
		combatLog.setBounds(225, 50, 200, 100);
		combatLog.setEditable(false);
		combatLog.setVisible(false);
		
		// actionListeners
		attack.addActionListener(new AttackAction());
		defend.addActionListener(new DefendAction());
		order66.addActionListener(new Order66Action());
		fireOrb.addActionListener(new FireOrbAction());
	}

	public static void createWave() { // creates a wave of beasts.
		int count = 10;
		int bossChance = (int)(Math.random()*6 + 1);
		
		if((bossChance == 1 && bossTurn >= 3) || bossTurn == 6){ // every three turns there is a chance of a boss, and every 6 turns there must be a boss.
			beasts.add(Randomizer.random(-1));
			beasts.get(0).setLocation(550, count);
			game.add(beasts.get(0));
			bossTurn = 1;
		}else{
			for (int i = 0; i < (int) (Math.random() * 4 + 1); i++) { // adds between 1 and 4 beasts each time.
				beasts.add(Randomizer.random(i));
				beasts.get(i).setLocation(600, count);
				game.add(beasts.get(i));
				count += 100;
			}
		}
		
		for(int i = 0; i < beasts.size(); i++){ // if the beast is faster it gets the first attack.
			if(beasts.get(i).getAgility() > player.getAgility()){
				beasts.get(i).attack(player);
			}
			
			player.updateHpLabel();
			check();
		}
		
		bossTurn++;
	}
	
	public static void createWave(int x){ // special method that creates beasts (specifically angry offsprings) when
		int count = 10;                   // a boss dies (specifically the giant chicken).
		
		for(int i = 0; i < (int) (Math.random() * 3 + 1); i++){ // adds 1-3 beasts
			beasts.add(Randomizer.random(i,x));
			beasts.get(i).setLocation(600, count);
			game.add(beasts.get(i));
			count += 100;
		}
		
		for(int i = 0; i < beasts.size(); i++){ // makes them visible
			beasts.get(i).setVisible(true);
		}
		
		for(int i = 0; i < beasts.size(); i++){ // if the beasts are faster they hit first.
			if(beasts.get(i).getAgility() > player.getAgility()){
				beasts.get(i).attack(player);
			}
			
			player.updateHpLabel();
			check();
		}
	}
	
	public static JMenu getItems(){ // used to add items to the item menu
		return items;
	}

	public static void optionsvisible(boolean a) { // hides the optionPanel when the shop, and attribute screen
		optionPanel.setVisible(a);                // is open.
		combatLog.setVisible(a);
	}

	public static void setTurnPoints(int x) { // sets the turnPoints
		turnPoints = x;
	}

	public static Player getPlayer() { // allows access of the player throughout the program
		return player;
	}
	
	public static ArrayList<Beast> getBeasts(){ // allows access of the beasts throughout the program.
		return beasts;
	}
	
	public static void addGame(JFrame window) { // adds the game panel to the window.
		window.add(game);
		window.add(gameOverPanel);
	}

	public static void showGameScreen(boolean a) { // makes the screen visible.
		game.setVisible(a);                       // used upon exiting the shop, or the attribute selection screen.
	}
	
	public static void updateCombatLog(String move){ // updates the beasts' and player's actions.
		update = "\n" + move + update ;
		combatLog.setText(update);
	
	}
	
	public static void clearCombatLog(){ // after the turn it clears the combat log for the next turn.
		update = "";
	}
	
	public static void removeDeadBeasts(){ // removes all beasts that are dead. Checks if a beast is dead after every player move.
		int temp = beasts.size();         
		
		for (int i = 0; i < beasts.size(); i++) {

			if (beasts.get(i).getHealth() <= 0) {
				if (player.getCurrentTarget() < beasts.size() && player.getCurrentTarget() >= 0) {
					player.addGold(beasts.get(i).giveGold());
					player.addExperience(beasts.get(i).getExperiencePoints());
					Container parent = beasts.get(i).getParent();
					game.remove(beasts.get(i));
					parent.remove(beasts.get(i));
					parent.repaint();
					beasts.remove(beasts.get(i));
					i--;
				}
			}
		}
		
		if(temp > beasts.size()){
		
			for (int i = 0; i < beasts.size(); i++) { // switches the beasts target numbers to avoid out of bounds errors.
				beasts.get(i).setTarget(i);
			}

			if (beasts.size() > 0) { // default target is set at the zero element.
				player.changeCurrentTarget(0);
			}
		
		}
	}
	
	static class AttackAction implements ActionListener { // actionListener that allows for the player to attack the beasts.

		public void actionPerformed(ActionEvent e) {
			if (turnPoints > 0 && beasts.size() > 0 && player.getCurrentHealth() > 0) { // subtracts a turn point for every attack.
				player.attack(beasts.get(player.getCurrentTarget()));

				beasts.get(player.getCurrentTarget()).updateHpLabel();
				turnPoints--;
			}

			removeDeadBeasts();

			if (turnPoints < 1 && beasts.size() > 0 && player.getCurrentHealth() > 0) { // if the player is out of turn points 
				for (int i = 0; i < beasts.size(); i++) { // the beasts attack the player.
					beasts.get(i).attack(player);
					player.updateHpLabel();
				}

				turnPoints = player.getTurnPoints(); // resets the turn points to full.
			}

			if (beasts.size() == 0) { // goes to the shop
				Game.clearCombatLog();
				Game.updateCombatLog("");
				Shop.showShop(true);
			}
			
			player.updateTurnPoints(player.getTurnPoints() - turnPoints);
			check();
		}
	}

	static class DefendAction implements ActionListener { // actionListener that allows for the player to defend
		
		public void actionPerformed(ActionEvent e) {
			
			updateCombatLog("Player.Player defends");
			
			for (int i = 0; i < beasts.size(); i++) { // automatically ends the players turn.
				if(beasts.get(i).specialCount() == 0){ // takes a quarter of the normal damage if the player blocks a special
					beasts.get(i).attack(player, 4);
				}else{                               // takes half of the normal damage
					beasts.get(i).attack(player, 2);
				}
				turnPoints = player.getTurnPoints();
				player.updateTurnPoints(player.getTurnPoints() - turnPoints);
			}
			player.updateHpLabel();
			
			check();
		}
	}

	static class Order66Action implements ActionListener { // actionListener that fires the player's strongest move order 66

		public void actionPerformed(ActionEvent e) {
			
			if(player.getMagicPoints() >= 5){ // makes sure that the player has 5 mp at minimum.
				player.order66(beasts);
				
				removeDeadBeasts();
				
				if (beasts.size() > 0 && player.getCurrentHealth() > 0) { // if the beasts survive they hit the player
					for (int i = 0; i < beasts.size(); i++) {
						beasts.get(i).attack(player);
						player.updateHpLabel();
					}

				}
				
				
				if (beasts.size() == 0) { // goes to the shop
					Game.clearCombatLog();
					Game.updateCombatLog("");
					Shop.showShop(true);
				}

				if(turnPoints < 1){ // resets the turn points to full.
					turnPoints = player.getTurnPoints();
				}
				
				player.updateTurnPoints(player.getTurnPoints() - turnPoints);
				game.repaint();
				
				check();
				}
		}

	}
	
	static class FireOrbAction implements ActionListener { //actionListener that fires the player's fire orb
		
		public void actionPerformed(ActionEvent event){
				if(player.getMagicPoints() >= 2){ // makes sure that the player has at least 2mp
					player.fireOrb(beasts.get(player.getCurrentTarget()));
				
				removeDeadBeasts();
				turnPoints--;
					
				if (beasts.size() > 0 && player.getCurrentHealth() > 0 && turnPoints < 1) { // if the player goes under 1p
					for (int i = 0; i < beasts.size(); i++) { // the beasts attack the player
						beasts.get(i).attack(player);
						player.updateHpLabel();
					}

				}
				
				if (beasts.size() == 0) { // goes to the shop
					Game.clearCombatLog();
					Game.updateCombatLog("");
					Shop.showShop(true);
				}

				if(turnPoints < 1){
					turnPoints = player.getTurnPoints(); // resets the turn points to full.
				}
				
				player.updateTurnPoints(player.getTurnPoints() - turnPoints);
				game.repaint();
				
				check();
				}
		}
	}
	
	public static void check(){ // checks if the player is still alive
		if(player.getCurrentHealth() <= 0){ // if not it queues the game over screen
			game.setVisible(false);
			gameOverPanel.setVisible(true);
			gameOverPanel.setSize(720,480);
			gameOverPanel.add(gameOverLabel );
			gameOverLabel.setSize(600,250);
			gameOverLabel.setLocation(60, 75);
			gameOverLabel.setFont(new Font("Serif", Font.PLAIN, 120));
		}
	}

}
