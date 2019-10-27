package Beasts;

import Player.Player;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AngryOffSpringOfAChicken extends Beast {
	private JButton img = new JButton(new ImageIcon(this.getClass().getResource("/AngryChicken.jpg")));
	private String name;
	
	public AngryOffSpringOfAChicken (int tar, int dmg, int hp, int maxHp, int ag, int exp ){ // initializes angry offspring
		super(tar, dmg, hp, maxHp, ag, exp);
		name = "Angry OffSpring";
		this.add(img);
		img.addActionListener(this);
	}
	
	public String getName(){ // gets name
		return name;
	}
	
	public int specialCount(){ // doesnt have a special so it return 0.
		return -1;
	}
	
}
