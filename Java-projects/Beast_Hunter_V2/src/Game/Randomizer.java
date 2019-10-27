package Game;

import Beasts.*;
import Game.Game;

public class Randomizer {

	public static Beast random(int i){ // selects a random beast to be added to the list
		int random = (int)(Math.random()*5 + 1);
		
		if(i == -1){
			random = (int)(Math.random()*2 + 1);
			// 50-50 chance of getting either boss
			if(random == 1){
				return createDragon(0);
			}else{
				return createGiantChicken(0);
			}
		
		}
		
		switch(random){ // probability distribution of the normal beasts
			case 1: return createTrashBandit(i);
			case 2: return createTrashBandit(i);
			case 3: return createTiger(i);
			case 4: return  createAngryOffSpringOfAChicken(i);
			case 5: return createBear(i);
		
			
			default: return createTrashBandit(i);
		}
	}
	
	public static Beast random(int i, int x){ // sole purpose is to add the angry offspring to the giant chicken boss fight
		
		if(x == 1)
			return createAngryOffSpringOfAChicken(i);
		else{ // made in case of further additions in the future. 
			return createTrashBandit(i);
		}
	}
	
	public static Tiger createTiger(int i){ // creates a tiger
		//tar, dmg, hp, maxHp, ag, exp
		int tar = i;
		int hp = (int)(50*scaleBasedOnLevel());
		int dmg = (int)(2*scaleBasedOnLevel());
		int maxHp = (int)(50*scaleBasedOnLevel());
		int ag = (int)(3*scaleBasedOnLevel());
		int exp = (int)(25*scaleBasedOnLevel());
		
		return new Tiger(tar,dmg,hp,maxHp,ag,exp);
	}
	
	public static TrashBandit createTrashBandit(int i){ // creates a trash bandit
		//tar, dmg, hp, maxHp, ag, exp
		int tar = i;
		int hp = (int)(25*scaleBasedOnLevel());
		int dmg = (int)(1*scaleBasedOnLevel());
		int maxHp = (int)(25*scaleBasedOnLevel());
		int ag = (int)(2);
		int exp = (int)(15*scaleBasedOnLevel());
		
		return new TrashBandit(tar,dmg,hp,maxHp,ag,exp);
	}
	
	public static AngryOffSpringOfAChicken createAngryOffSpringOfAChicken(int i){ // creates an angry offspring
		int tar = i;
		int hp = (int)(30*scaleBasedOnLevel());
		int dmg = (int)(1*scaleBasedOnLevel());
		int maxHp = (int)(30*scaleBasedOnLevel());
		int ag = (int)(5);
		int exp = (int)(16*scaleBasedOnLevel());
		
		return new AngryOffSpringOfAChicken(tar,dmg,hp,maxHp,ag,exp);
	}
	
	private static Bear createBear(int i){ // creates a bear
		int tar = i;
		int hp = (int)(90*scaleBasedOnLevel());
		int dmg = 1;
		int maxHp = (int)(90*scaleBasedOnLevel());
		int ag = (int)(1);
		int exp = (int)(30*scaleBasedOnLevel());
		
		return new Bear(tar,dmg,hp,maxHp,ag,exp);
	}
	
	public static Dragon createDragon(int i){ // creates a dragon
		int tar = i;
		int hp = (int)(150*scaleBasedOnLevel());
		int dmg = (int)(4 * scaleBasedOnLevel());
		int maxHp = (int)(150*scaleBasedOnLevel());
		int ag = (int)(1);
		int exp = (int)(60*scaleBasedOnLevel());
		
		return new Dragon(tar,dmg,hp,maxHp,ag,exp);
	}
	
	public static GiantChicken createGiantChicken(int i){ // creates a giant chicken
		int tar = i;
		int hp = (int)(200*scaleBasedOnLevel());
		int dmg = (int)(3 * scaleBasedOnLevel());
		int maxHp = (int)(200*scaleBasedOnLevel());
		int ag = (int)(1);
		int exp = (int)(65*scaleBasedOnLevel());
		
		return new GiantChicken(tar,dmg,hp,maxHp,ag,exp);
	}
	
	public static double scaleBasedOnLevel(){ // scales the beasts stats.
		
		if(Game.getPlayer().getLevel() == 1) return 1;
		if(Game.getPlayer().getLevel() <= 5)
			return  Math.pow(1.2, Game.getPlayer().getLevel());
		if(Game.getPlayer().getLevel() <= 10)
			return  Math.pow(1.4, Game.getPlayer().getLevel());
		
		return  Math.pow(1.6, Game.getPlayer().getLevel());
	}
}
