package Items;

import Game.Game;
import Game.Shop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sword extends Item implements ActionListener {
	private double damageModifier;
	private int throwDamage;
	private static int cost = 11;
	private static int weight = 3;
	private static String name = "Items.Sword";
	
	public Sword (){ // initializes the sword
		damageModifier = Math.random()*1 + 1.2;
		Game.getPlayer().setAttackItemModifier(damageModifier);
		throwDamage = (int)(Math.random()*3 + 1);
		this.setText(name);
		Game.getItems().add(this);
		this.addActionListener(this);
	}
	
	public static int getCost(){ // gets the items cost
		return cost;
	}
	
	public static int getWeight(){ // gets the items weight
		return weight;
	}
	
	public static String getItemName(){ // gets the items name
		return name;
	}
	
	public int getThrowDamage(){ // returns throw damage
		return throwDamage;
	}
	
	public void actionPerformed(ActionEvent e){ // throws the player's sword at the targeted beast, and removes the item from the player
		Game.getPlayer().throwItem(Game.getBeasts().get(Game.getPlayer().getCurrentTarget()), throwDamage, damageModifier);
		Game.removeDeadBeasts();
		Game.getPlayer().setAttackItemModifier(-this.damageModifier);
		Game.getPlayer().remove(this);
		Game.getItems().remove(this);
		Game.getPlayer().setCarryWeight(-this.getWeight());
		
		if (Game.getBeasts().size() == 0) {
			Shop.showShop(true);
		}
	}
}
