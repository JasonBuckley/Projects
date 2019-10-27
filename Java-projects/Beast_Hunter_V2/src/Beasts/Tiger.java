package Beasts;

import Player.Player;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Game.Game;

public class Tiger extends Beast {

	private JButton img = new JButton(new ImageIcon(this.getClass().getResource("/Tiger.png")));
	private String name;
	private int biteCount = 1;
	
	public Tiger(int tar, int dmg, int hp, int maxHp, int ag, int exp ){ // initializes the tiger
		super(tar, dmg, hp, maxHp, ag, exp);
		name = "Beasts.Tiger";
		this.add(img);
		img.addActionListener(this);
	}
	
	public void attack(Player player){ // attacks the player
		
		if(biteCount%4 == 0 ){ // tiger may randomly bite the player
			player.setCurrentHealth(this.getDamage()*3);
			biteCount+= (int)(Math.random()*3 + 1);
			Game.updateCombatLog(this.getName() + " bites player");
		}else{ // normal attack
			player.setCurrentHealth(this.getDamage()*2);
			Game.updateCombatLog(this.getName() + " attcks player");
		}
		
	}
	
	public String getName(){ // returns the name
		return name;
	}
	
	public int specialCount(){  // returns bite count
		return biteCount%4;
	}
}