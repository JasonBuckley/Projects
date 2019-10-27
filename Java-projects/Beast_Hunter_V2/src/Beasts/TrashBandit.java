package Beasts;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TrashBandit extends Beast {
	private JButton img = new JButton(new ImageIcon(this.getClass().getResource("/trashBandit.jpg")));
	private String name;
	
	public TrashBandit(int tar, int dmg, int hp, int maxHp, int ag, int exp ){
		super(tar, dmg, hp, maxHp, ag, exp);
		name = "Trash Bandit";
		this.add(img);
		img.addActionListener(this);
	}
	
	public String getName(){
		return name;
	}
	
	public int specialCount(){ // doesnt have a special so it return 0.
		return -1;
	}
	
}
