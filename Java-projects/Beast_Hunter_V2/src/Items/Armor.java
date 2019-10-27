package Items;

import Game.Game;
import Game.Shop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Armor extends Item implements ActionListener {
	private double ArmorAbsorption;
	private int throwDamage;
	private static int cost = 9;
	private static int weight = 4;
	private static String name = "Items.Armor";
	private static boolean isThereArmor = false;
	
	public Armor (){ // initializes the armor
		ArmorAbsorption = Math.random()*0.1 + 0.45;
		Game.getPlayer().setDamageResistence(ArmorAbsorption);
		throwDamage = (int)(Math.random()*3 + 1);
		this.setText(name);
		Game.getItems().add(this);
		this.addActionListener(this);
		isThereArmor = true;
	}
	
	public static int getCost(){ // returns the cost of the item
		return cost;
	}
	
	public static int getWeight(){ // returns the weight of the item
		return weight;
	}
	
	public static String getItemName(){ // gets the items name
		return name;
	}
	
	public int getThrowDamage(){ // returns the throw damage of the item
		return throwDamage;
	}
	
	public static boolean getIsThereArmor(){ // returns if there is already a set of armor
		return isThereArmor;                // you can only wear one set of armor
	}
	
	
	public void actionPerformed(ActionEvent e){ // throws the player's armor at the targeted beast, and removes the item from the player
		Game.getPlayer().throwItem(Game.getBeasts().get(Game.getPlayer().getCurrentTarget()), throwDamage, 1/ArmorAbsorption);
		Game.removeDeadBeasts();
		Game.getPlayer().setDamageResistence(-this.ArmorAbsorption);
		Game.getPlayer().remove(this);
		Game.getItems().remove(this);
		Game.getPlayer().setCarryWeight(-this.getWeight());
		isThereArmor = false;
		
		if (Game.getBeasts().size() == 0) {
			Shop.showShop(true);
		}
	}
}
