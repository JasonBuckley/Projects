package Player;

import Beasts.Beast;

import java.util.*;
import Game.*;
import Items.Item;

import javax.swing.*;

public class Player extends JPanel {
	private int maxHealth;
	private int currentHealth; 
	private int experience; // experience points, used for leveling up.
	private int experienceToLevel; // determines how much experience is need for the next level.
	private int level;
	private int gold;
	private double attackItemModifier; // modifies the player's attack damage.
	private double damageResistence;
	private int strength; // used to determine the player's amount of damage ( primary damage stat).
	private int magicPoints; // attribute (determines if you can use a spell)
	private int MaxMagicPoints; // determines the max amount of magic points you can have.
	private int agility; // attribute (decides who hits first)
	private int carryWeight; // attribute (used to determine how many items you
								// can hold)
	private int maxCarryWeight; // determines the max amount of items you can carry.
	private int turnPoints; // attribute (how many moves you can make during a
							// turn). tp for short
	
	private ArrayList<Item> bag = new ArrayList<Item>(); // holds the items
	private JLabel hp = new JLabel(); // displays player hp
	private JLabel img = new JLabel(new ImageIcon(this.getClass().getResource("/knight1.png")));
	private JLabel tp = new JLabel(); // displays player tp
	private static int currentTarget = 0;
	private JLabel mp = new JLabel(); // displays player mp

	public Player() { // initializes the player
		maxHealth = 100;
		currentHealth = 100;
		experience = 0;
		level = 1;
		strength = 1;
		magicPoints = 1;
		agility = 1;
		carryWeight = 0;
		turnPoints = 1;
		MaxMagicPoints = 1;
		experienceToLevel = 50;
		maxCarryWeight = 1;
		attackItemModifier = 0;
		damageResistence = 0;
		
		this.setSize(100,150);
		
		tp.setText(turnPoints + " TP");
		tp.setBounds(0,0,100,15);
		hp.setText(currentHealth + " / " +  maxHealth + " HP");
		hp.setBounds(0, 15, 100, 15);
		mp.setText(magicPoints + " / " + MaxMagicPoints + " MP");
		mp.setBounds(0, 30, 100, 15);
		img.setLocation(48, 45);
		
		this.add(hp);
		this.add(mp);
		this.add(img);
		this.add(tp);
		this.setVisible(true);
		
	}

	public void setCurrentHealth(int hp) { // sets the players health
		if (this.currentHealth - hp <= 0)
			currentHealth = 0;
		else if (this.currentHealth - hp > maxHealth)
			currentHealth = maxHealth;
		else if(damageResistence > 0)
			currentHealth-= (int)(hp * damageResistence);
		else 
			currentHealth-= hp;
	
	}

	public void setMaxHealth(int x) { // sets the players max health.
		maxHealth = maxHealth + 25 * x;
	}
	
	public int getMaxMagicPoints(){ // used to get the players max damage.
		return MaxMagicPoints;
	}
	
	public void setMaxMagicPoints(int x){ // sets the players max magic
		MaxMagicPoints+= x;
	}
	
	public int getCurrentHealth() { // gets the players current health.
		return currentHealth;
	}

	public int getMaxHealth() { // gets the players max health
		return maxHealth;
	}

	public void addExperience(int x) { // gives the player experience points
		experience += x;
	}
	
	public int getExperience(){ // gets the players current amount of experience points
		return experience;
	}

	private void increaseExperienceToLevel() { // increases the amount of experience to level
		if(level <= 5){
			experienceToLevel = (int) (experienceToLevel * 1.75);
		}else if(level <=10){
			experienceToLevel = (int) (experienceToLevel * 2);
		}else{
			experienceToLevel = (int) (experienceToLevel * 2.15);
		}
		
	}

	public void setStrength(int x) { // sets the player's strength
		strength += x;
	}

	public int getStrength() { // gets the player's strength
		return strength;
	}

	public int getMagicPoints() { // gets the player's current magic points
		return magicPoints;
	}

	public void setMagicPoints(int x) { // sets the player's current magic points
		if(magicPoints - x < 0){
			magicPoints = 0;
		}else{
			magicPoints -= x;
		}
	}

	public int getAgility() { // gets the player's agility
		return agility;
	}

	public void setAgility(int x) { // sets the player's agility
		agility += x;
	}

	public int getCarryWeight() { // gets the player's amount of carry weight
		return carryWeight;
	}

	public void setCarryWeight(int x) { // sets the player's carry weight
		carryWeight += x;
	}
	
	public int getMaxCarryWeight() { // gets the player's max carry weight
		return maxCarryWeight;
	}

	public void setMaxCarryWeight(int x) { // sets the player's max carry weight
		maxCarryWeight += x;
	}

	public int getTurnPoints() { // gets the player's amount of turn points allowed.
		return turnPoints;
	}

	public void setTurnPoints(int x) { // sets the player's amount of turn points allowed.
		turnPoints += x;
	}
	
	public int getCurrentTarget(){ // gets the player's current target
		return currentTarget;
	}
	
	public void changeCurrentTarget(int x){ // sets the player's current target.
		currentTarget = x;
	}
	
	public int getLevel(){ // gets the player's level.
		return level;
	}
	
	public ArrayList<Item> getBag(){ // gets the player's bag
		return bag;
	}
	
	public void attack(Beast beast){ // attacks the beasts
		if(attackItemModifier == 0){
			beast.setHealth(this.getStrength()*3);
			Game.updateCombatLog("Player.Player attacks " + beast.getName());
		}else{
			beast.setHealth((int)(this.getStrength()*3*attackItemModifier)); // multiplies the damage modifier if the player
			Game.updateCombatLog("Player.Player slashes " + beast.getName()); // has a sword.
		}
	}
	
	public void attack(Beast beast, int multiplier){ // attacks with the appropriate multiplier (used with the magic moves).
		beast.setHealth(this.getStrength()*multiplier*3);
		beast.updateHpLabel();
	}
	
	public void throwItem(Beast beast, int throwDamage, double modifier){ // throws the player's item if a sword, or armor is clicked
		this.attack(beast, (int)(throwDamage + modifier)); // a way to discard items.
		Game.updateCombatLog("Player.Player throws sword at " + beast.getName());
	}
	
	public void setAttackItemModifier(double x){ // sets the amount of attack item modifier the player has.
		attackItemModifier += x;
	}
	
	public void setDamageResistence(double x){ // sets how much damage resistance the player has.
		damageResistence += x;
	}
	
	public void updateHpLabel(){  // updates the player's displayed hp.
		hp.setText(currentHealth + " / " +  maxHealth + " HP");
	}
	
	public void updateMpLabel(){ // updates the player's displayed mp.
		mp.setText(magicPoints + " / " + MaxMagicPoints + " MP");
	}
	
	public void updateTurnPoints(int x){ // updates the player's displayed tp.
		tp.setText(turnPoints - x + " TP");
	}
	
	public Boolean checkExperienceToLevel(){ // checks if the player levels up
		if(experience >= experienceToLevel){
			level++;
			increaseExperienceToLevel();
			return true;
		}
		
		return false;
	}
	
	public int getGold(){ // gets the player's gold
		return gold;
	}
	
	public void addGold(int x){ // adds gold 
		gold+= x;
	}
	
	public void order66(ArrayList<Beast> beasts){ // hits every enemy with massive amounts of damage, but uses all
												 // magic points and turn points.
		for(int i = 0; i < beasts.size(); i++){
			this.attack(beasts.get(i), (int)(strength*magicPoints*0.8));
			beasts.get(i).updateHpLabel();
		}
		
		Game.updateCombatLog("Player.Player excutes Order 66");
		
		magicPoints = 0;
		this.updateMpLabel();
		Game.setTurnPoints(0);
	}
	
	public void fireOrb(Beast beast){ // throws a fire orb at a beast 
		magicPoints-= 2; // can do significant damage if the player has high agility and strength.
		
		Game.updateCombatLog("Player.Player tosses a Fire Orb at " + beast.getName());
		
		if(beast.getName().equals("Beasts.Tiger") || beast.getName().equals("Beasts.Dragon")){ // does half damage to enemies that are resistant to fire
			this.attack(beast, (strength + agility)/2);
			beast.updateHpLabel();
			this.updateMpLabel();
		}else{
			this.attack(beast, (strength + agility));
			beast.updateHpLabel();
			this.updateMpLabel();
		}
	}
	
}





