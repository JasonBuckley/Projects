package Beasts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Player.Player;
import javax.swing.*;
import Game.Game;

public abstract class Beast extends JPanel implements ActionListener{
	private int damage;
	private int health;
	private int MaxHealth;
	private int agility;
	private int experiencePoints;
	private JLabel hpLabel = new JLabel();
	private int target;
	
	public Beast(int tar, int dmg, int hp, int maxHp, int ag, int exp ){ // initializes the beast
		damage = dmg;
		health = hp;
		MaxHealth = maxHp;
		agility = ag;
		experiencePoints = exp;
		target = tar;
		
		this.add(hpLabel);
		hpLabel.setText(health + " / " + MaxHealth);
		this.setSize(80,80);
	
	}
	
	public int getExperiencePoints() { // gets experience points that the beast gives
		return experiencePoints;
	}
	
	public void updateHpLabel(){ // updates the beasts displayed health.
		hpLabel.setText(health + " / " + MaxHealth);
	}
	
	public void setHealth(int x){ // sets the beasts health
		if(health - x <= 0){
			health = 0;
		}else{
			health-= x;
		}
		
	}
	
	public int getAgility(){ // gets the beasts agility
		return agility;
	}
	
	public int getHealth(){ // gets the beasts health
		return health;
	}
	
	public int getDamage(){ // gets the beasts damage.
		return damage;
	}
	
	public void attack(Player player){ // attacks the player
		player.setCurrentHealth(this.getDamage()*2);
		Game.updateCombatLog(this.getName() + " attcks player");
	}
	
	public void attack(Player player, int x){ // attacks a quarter or half damage based on the move defended against/
		player.setCurrentHealth((this.getDamage()*2)/x);
		Game.updateCombatLog(this.getName() + " attcks player");
	}
	
	public int getTarget(){ // gets the target number
		return target;
	}
	
	public int giveGold(){ // gives the player gold
		return (int)(Math.random()*3 + 1);
	}
	
	public void paintComponent(Graphics g) { // creates a red background behind the beast
											// so you know it is an enemy
		Graphics2D g2 = (Graphics2D) g;

		Rectangle rect1 = new Rectangle(80, 80);
		g2.setColor(Color.RED);
		g2.draw(rect1);
		g2.fill(rect1);

	}
	
	public void actionPerformed(ActionEvent e){ // makes this beast the players current target
		Game.getPlayer().changeCurrentTarget(this.getTarget());
	}
	
	public void setTarget(int x){ // sets the target number of the beast.
		target = x;
	}
	
	abstract public String getName(); // gets beasts name in child classes
	
	abstract public int specialCount(); // gets beasts special move count in child classes.
}



