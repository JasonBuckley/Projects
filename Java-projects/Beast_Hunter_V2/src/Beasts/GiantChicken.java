package Beasts;

import Player.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Game.Game;

public class GiantChicken extends Beast {
	
	private JButton img = new JButton(new ImageIcon(this.getClass().getResource("/GiantChicken.png")));
	private String name;
	private static int stomp = 1;
	
	public GiantChicken (int tar, int dmg, int hp, int maxHp, int ag, int exp ){ // initializes the giant chicken
		super(tar, dmg, hp, maxHp, ag, exp);
		name = "Giant Chicken";
		this.add(img);
		this.setSize(120,120);
		img.addActionListener(this);
	}
	
	public String getName(){ // returns the giant chickens name
		return name;
	}
	
	public void attack(Player player){ // attacks the player
		if(stomp%4 == 0){ // stomps the player every fourth turn
			player.setCurrentHealth(this.getDamage()*5);
			Game.updateCombatLog(this.getName() + " stomps player");
		}else{ // normal attack
			player.setCurrentHealth(this.getDamage()*2);
			Game.updateCombatLog(this.getName() + " attcks player");
		}
		
		stomp++;
	}
	
	public void setHealth(int x){
		if(getHealth() - x <= 0){
			super.setHealth(x);
			Game.removeDeadBeasts();
			Game.createWave(1);
		}else{
			super.setHealth(x);
		}
		
	}
	
	public void attack(Player player, int x){ // used when the defend action by the player is used
		
		if(stomp%4 == 0){ //stomps the player every fourth turn
			player.setCurrentHealth((this.getDamage()*2)/x);
			Game.updateCombatLog(this.getName() + " stomps player");
		}else{ // normal attack
			player.setCurrentHealth((this.getDamage()*2)/x);
			Game.updateCombatLog(this.getName() + " attcks player");
		}
		
		stomp++;
	}
	
	public int specialCount(){ // returns the stomp count
		return stomp;
	}
	
	public int giveGold(){ // gives the player gold
		return (int)(Math.random()*6 + 8);
	}
	
	
	public void paintComponent(Graphics g) { // look at beast description.

		Graphics2D g2 = (Graphics2D) g;

		Rectangle rect1 = new Rectangle(140, 140);
		g2.setColor(Color.RED);
		g2.draw(rect1);
		g2.fill(rect1);

	}
	
}
