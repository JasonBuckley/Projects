package Items;

import Game.Game;
import Items.Item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MagicBottle extends Item implements ActionListener{
	private int magicRestoring;
	private static int cost = 3;
	private static int weight = 1;
	private static String name = "Magic Bottle";
	
	public MagicBottle(){
		magicRestoring = Game.getPlayer().getMaxMagicPoints()/2;
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
	
	public void actionPerformed(ActionEvent e){
	
		Game.getPlayer().setMagicPoints(-magicRestoring);
		Game.getPlayer().updateMpLabel();
		Game.getPlayer().remove(this);
		Game.getItems().remove(this);
		Game.getPlayer().setCarryWeight(-this.getWeight());
		Game.updateCombatLog("Player.Player uses Magic Bottle");
	}
}
