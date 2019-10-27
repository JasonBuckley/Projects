package Game;

import Items.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Shop {
	private static ArrayList<Item> items = new ArrayList<Item>();
	private static JPanel shopPanel = new JPanel(null);
	private static JButton exitShop = new JButton("Exit Game");
	private static JLabel playerGold = new JLabel();
	private static JLabel playerCarryWeight = new JLabel();
	private static JLabel shopTitle = new JLabel("Hunter Game");
	//----------------------------------------------------------- Healing Bottle components
	private static JPanel healingBottlePanel = new JPanel();
	private static JLabel healingBottleLabel = new JLabel(HealingBottle.getCost() + "$ and weighs " + HealingBottle.getWeight() + "kg " + HealingBottle.getItemName());
	private static JButton healingBottleButton = new JButton("Buy");
	//----------------------------------------------------------- Magic Bottle components
	private static JPanel magicBottlePanel = new JPanel();
	private static JLabel magicBottleLabel = new JLabel(MagicBottle.getCost() + "$ and weighs " + MagicBottle.getWeight() + "kg " + MagicBottle.getItemName());
	private static JButton magicBottleButton = new JButton("Buy");
	
	//----------------------------------------------------------- Items.Sword components
	private static JPanel swordPanel = new JPanel();
	private static JLabel swordLabel = new JLabel(Sword.getCost() + "$ and weighs " + Sword.getWeight() + "kg " + Sword.getItemName());
	private static JButton swordButton = new JButton("Buy");
	
	//----------------------------------------------------------- Items.Armor components
	private static JPanel armorPanel = new JPanel();
	private static JLabel armorLabel = new JLabel(Armor.getCost() + "$ and weighs " + Armor.getWeight() + "kg " + Armor.getItemName());
	private static JButton armorButton = new JButton("Buy");
		
	public static void shop(){ // creates the shop and all of its components, and adds it to the game panel
		shopPanel.setSize(720,480);
		shopPanel.setBackground(Color.ORANGE);
		shopPanel.setVisible(false);
		shopPanel.add(playerGold);
		shopPanel.add(playerCarryWeight);
		shopPanel.add(shopTitle);
		shopTitle.setFont(new Font("Serif", Font.PLAIN, 28));
		shopTitle.setBounds(300, 5, 150, 55);
		
		playerGold.setBounds(20, 60, 170, 30);
		playerCarryWeight.setBounds(20, 90, 170, 30);
		
		exitShop.setBounds(300, 400, 150, 30);
		shopPanel.add(exitShop);
		
		//-------------------------------------- Healing Bottle components
		shopPanel.add(healingBottlePanel);
		healingBottlePanel.setBounds(300, 55, 275, 30);
		healingBottlePanel.add(healingBottleLabel);
		healingBottlePanel.add(healingBottleButton);
		
		//-------------------------------------- Magic Bottle components
		shopPanel.add(magicBottlePanel);
		magicBottlePanel.setBounds(300, 90, 275, 30);
		magicBottlePanel.add(magicBottleLabel);
		magicBottlePanel.add(magicBottleButton);
		
		//-------------------------------------- Items.Sword components
		shopPanel.add(swordPanel);
		swordPanel.setBounds(300, 125, 275, 30);
		swordPanel.add(swordLabel);
		swordPanel.add(swordButton);
		
		//-------------------------------------- Items.Armor components
		shopPanel.add(armorPanel);
		armorPanel.setBounds(300, 160, 275, 30);
		armorPanel.add(armorLabel);
		armorPanel.add(armorButton);
		
		//actionListeners
		exitShop.addActionListener(new ExitShopAction());
		healingBottleButton.addActionListener(new HealingBottleButtonAction());
		magicBottleButton.addActionListener(new MagicBottleButtonAction());
		swordButton.addActionListener(new SwordButtonAction());
		armorButton.addActionListener(new ArmorButtonAction());
	}
	
	public static void addShopScreen(JPanel panel) { // adds the shop screen to the game panel
		panel.add(shopPanel);
	}
	
	public static void showShop(boolean a){ // makes the shop visible and makes all other panels false in the game panel.
		shopPanel.setVisible(a);
		updatePlayersGoldAndCarryWeight();
		Game.optionsvisible(!a);
	}
	
	public static void updatePlayersGoldAndCarryWeight(){ // updates the amount of gold displayed, and carry weight.
		playerGold.setText("Player.Player's Gold: $ " + Game.getPlayer().getGold());
		playerCarryWeight.setText("Player.Player's Carry Weight: " + Game.getPlayer().getCarryWeight() + " /" + Game.getPlayer().getMaxCarryWeight());
	}
	
	static class ExitShopAction implements ActionListener { // exits the shop
		public void actionPerformed(ActionEvent e){
			if(Game.getPlayer().checkExperienceToLevel() == true){ // goes to the level up screen if the player is ready to level
				AttributeSelection.showAttributeScreen(true);
				shopPanel.setVisible(false);
			}else{ // else it creates another wave.
				Game.createWave();
				Game.optionsvisible(true);
				shopPanel.setVisible(false);
				
				Game.getPlayer().updateHpLabel();
				Game.getPlayer().updateMpLabel();
				Game.setTurnPoints(Game.getPlayer().getTurnPoints());
				Game.getPlayer().updateTurnPoints(0);
			}
			
		}
	}
	
	static class HealingBottleButtonAction implements ActionListener{ // adds a healing bottle to the player's bag, and takes away the alloted amount of gold
		
		public void actionPerformed(ActionEvent event){
			if(Game.getPlayer().getCarryWeight() + HealingBottle.getWeight() <= Game.getPlayer().getMaxCarryWeight() && Game.getPlayer().getGold() >= HealingBottle.getCost()){
				Game.getPlayer().getBag().add(new HealingBottle());
				Game.getPlayer().setCarryWeight(HealingBottle.getWeight());
				Game.getPlayer().addGold(-HealingBottle.getCost());
				updatePlayersGoldAndCarryWeight();
			}
		
		}
	}
	
	static class MagicBottleButtonAction implements ActionListener { // adds a magic bottle to the player's bag, and takes away the alloted amount of gold
		
		public void actionPerformed(ActionEvent e){
			if(Game.getPlayer().getCarryWeight() + MagicBottle.getWeight() <= Game.getPlayer().getMaxCarryWeight() && Game.getPlayer().getGold() >= MagicBottle.getCost()){
				Game.getPlayer().getBag().add(new MagicBottle());
				Game.getPlayer().setCarryWeight(MagicBottle.getWeight());
				Game.getPlayer().addGold(-MagicBottle.getCost());
				updatePlayersGoldAndCarryWeight();
			}
		}
	}
	
	static class SwordButtonAction implements ActionListener { // adds a sword to the player's bag, and takes away the alloted amount of gold
		
		public void actionPerformed(ActionEvent e){
			if(Game.getPlayer().getCarryWeight() + Sword.getWeight() <= Game.getPlayer().getMaxCarryWeight() && Game.getPlayer().getGold() >= Sword.getCost()){
				Game.getPlayer().getBag().add(new Sword());
				Game.getPlayer().setCarryWeight(Sword.getWeight());
				Game.getPlayer().addGold(-Sword.getCost());
				updatePlayersGoldAndCarryWeight();
			}
		}
	}
	
	static class ArmorButtonAction implements ActionListener { // adds a armor to the player's bag, and takes away the alloted amount of gold
		
		public void actionPerformed(ActionEvent e){
			if(Game.getPlayer().getCarryWeight() + Armor.getWeight() <= Game.getPlayer().getMaxCarryWeight() && Game.getPlayer().getGold() >= Armor.getCost() 
					&& Armor.getIsThereArmor() == false){
				Game.getPlayer().getBag().add(new Armor());
				Game.getPlayer().setCarryWeight(Armor.getWeight());
				Game.getPlayer().addGold(-Armor.getCost());
				
				updatePlayersGoldAndCarryWeight();
			}
		}
	}
	
}



