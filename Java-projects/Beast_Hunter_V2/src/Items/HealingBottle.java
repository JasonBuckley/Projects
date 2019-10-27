package Items;

import Game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class HealingBottle extends Item implements ActionListener{
	private int healing;
	private static int cost = 3;
	private static int weight = 1;
	private static String name = "Healing Bottle";
	
	public HealingBottle(){
		healing = Game.getPlayer().getMaxHealth()/4;
		this.setText(name);
		Game.getItems().add(this);
		this.addActionListener(this);
	}
	
	public static int getCost(){
		return cost;
	}
	
	public static int getWeight(){
		return weight;
	}
	
	public static String getItemName(){
		return name;
	}
	
	public JMenuItem getMenuItem(){
		return this;
	}

	public void actionPerformed(ActionEvent arg0) {
		Game.getPlayer().setCurrentHealth(-healing);
		Game.getPlayer().updateHpLabel();
		Game.getPlayer().remove(this);
		Game.getItems().remove(this);
		Game.getPlayer().setCarryWeight(-this.getWeight());
		Game.updateCombatLog("Player.Player uses Healing Bottle");
		
	}
}
