package Beasts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Player.Player;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Game.Game;

public class Dragon extends Beast {
	
	private JButton img = new JButton(new ImageIcon(this.getClass().getResource("/dragon.jpg")));
	private String name;
	private static int breathFire = 1;
	
	public Dragon (int tar, int dmg, int hp, int maxHp, int ag, int exp ){ // initializes the dragon
		super(tar, dmg, hp, maxHp, ag, exp);
		name = "Beasts.Dragon";
		this.add(img);
		this.setSize(120,140);
		img.addActionListener(this);
	}
	
	public String getName(){ // gets the dragons name
		return name;
	}
	
	public void attack(Player player){ // attacks the player
		if(breathFire%3 == 0){ // breathes fire
			player.setCurrentHealth(this.getDamage()*4);
			Game.updateCombatLog(this.getName() + " breaths fire");
		}else{ // normal attack
			player.setCurrentHealth(this.getDamage()*2);
			Game.updateCombatLog(this.getName() + " attcks player");
		}
		
		
		breathFire++;
	}
	
	public void attack(Player player, int x){ // used if the defend action is used by the player
		
		if(breathFire%3 == 0){ // if the dragon breaths fire
			player.setCurrentHealth((this.getDamage()*2)/x);
			Game.updateCombatLog(this.getName() + " breaths fire");
		}else{ // normal attack
			player.setCurrentHealth((this.getDamage()*2)/x);
			Game.updateCombatLog(this.getName() + " attcks player");
		}
		
		breathFire++;
	}
	
	public int specialCount(){ // gets breathfire count for the dragon
		return breathFire%3;
	}
	
	public int giveGold(){ // gives the player gold
		return (int)(Math.random()*6 + 8);
	}
	
	
	public void paintComponent(Graphics g) { // look at Beasts.Beast class for description

		Graphics2D g2 = (Graphics2D) g;

		Rectangle rect1 = new Rectangle(140, 140);
		g2.setColor(Color.RED);
		g2.draw(rect1);
		g2.fill(rect1);

	}
}
