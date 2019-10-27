package Beasts;

import Player.Player;
import Game.Game;
import javax.swing.*;

public class Bear extends Beast {
	
	private JButton img = new JButton(new ImageIcon(this.getClass().getResource("/bear.jpg")));
	private String name;
	private int multiply; // doubles the damage each turn
	private int count; // stops the beast when it gets to 6, or 2 higher than the players level.
	
	public Bear(int tar, int dmg, int hp, int maxHp, int ag, int exp ){ // initializes a bear.
		super(tar, dmg, hp, maxHp, ag, exp);
		name = "Beasts.Bear";
		multiply = 1;
		count = 1;
		this.add(img);
		img.addActionListener(this);
	}
	
	public String getName(){
		return name;
	}
	
	public void attack(Player player){
		player.setCurrentHealth(this.getDamage()*multiply); // progressively doubles its damage. The amount of times it doubles its damage
		// is 2 greater than the players level or 6 times. (max damage 64)
		if(count < player.getLevel() + 2 || count <= 6){
			 multiply = multiply*2;
		}
		
		Game.updateCombatLog(this.getName() + " attcks player");
		
		count++;
	}
	
	public int specialCount(){ // doesn't have a special so it return 0.
		return -1;
	}
	
	public void attack(Player player, int x){ // used while the defense option is pressed.
		player.setCurrentHealth((this.getDamage()*multiply)/x);
		
		if(count < player.getLevel() + 2){ // doubles the damage of the move (max damage 64)
			 multiply = multiply*2;
		}
		
		Game.updateCombatLog(this.getName() + " attcks player");
		
		count++;
	}

}
