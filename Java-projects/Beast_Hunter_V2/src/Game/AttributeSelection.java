package Game;

import Game.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AttributeSelection {
	private static JPanel levelingUpScreen = new JPanel(null);
	//------------------------------------------- hp stat variables
	private static JButton increaseHp = new JButton("+");
	private static JLabel hpLabel = new JLabel();
	private static JPanel hpPanel = new JPanel();
	//------------------------------------------- str stat variables
	private static JPanel strPanel = new JPanel();
	private static JLabel strLabel = new JLabel();
	private static JButton increaseStr = new JButton("+");
	//------------------------------------------- mp stat variables
	private static JPanel mpPanel = new JPanel();
	private static JLabel mpLabel = new JLabel();
	private static JButton increaseMp = new JButton("+");
	//------------------------------------------- carryWeight variables
	private static JPanel carryWeightPanel = new JPanel();
	private static JLabel carryWeightLabel = new JLabel();
	private static JButton increaseCarryWeight = new JButton("+");
	//------------------------------------------- agility stat variabels
	private static JPanel agilityPanel = new JPanel();
	private static JLabel agilityLabel = new JLabel();
	private static JButton increaseAgility = new JButton("+");
	//------------------------------------------- turnPoint stat variables
	private static JPanel turnPointPanel = new JPanel();
	private static JLabel turnPointLabel = new JLabel();
	private static JButton increaseTurnPoints = new JButton("+");
	//-------------------------------------------
	private static int attributePoints = 9; // holds how many free attribute points there are.
	private static JLabel attributePointsLabel = new JLabel(); // displays how many free attribute points there are.
	private static JButton confirm = new JButton("Confirm"); // confirms attributes
	private static JButton reset = new JButton("Reset"); // resets temporary level attributes 
	private static int[] tempStats = new int[6]; // element 0 is for hp, element 1 is for mp, element 2 is for str, element 3 is for agility
	// element 4 is for carry weight, and element 5 is for turn points.
	
	public static void levelUpScreen() {  // adds all the components to the attribution selection screen
		// and sets there sizes accordingly.
		Game.optionsvisible(false);
		levelingUpScreen.setBounds(0, 0, 720, 480);
		levelingUpScreen.setBackground(Color.GREEN);
		JLabel levelingUpLabel = new JLabel("LEVELED UP");
		levelingUpLabel.setBounds(280, 5, 120, 15);
		levelingUpScreen.add(levelingUpLabel);
		
		attributePointsLabel.setBounds(280, 25, 150, 25);
		attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
		levelingUpScreen.add(attributePointsLabel);
		
		// hp stat
		hpPanel.setBounds(280, 50, 150, 25);
		levelingUpScreen.add(hpPanel);
		
		hpLabel.setText(Game.getPlayer().getMaxHealth() + " Health ");
		hpPanel.add(hpLabel);

		increaseHp.setSize(20, 20);
		hpPanel.add(increaseHp);
		
		//strength stat
		strPanel.setBounds(280, 75, 150, 25);
		levelingUpScreen.add(strPanel);
		
		strLabel.setText(Game.getPlayer().getStrength() + " Strength ");
		strPanel.add(strLabel);
		
		increaseStr.setSize(20,20);
		strPanel.add(increaseStr); 
		
		//magicPoint stat
		mpPanel.setBounds(280,100,150,25);
		levelingUpScreen.add(mpPanel);
		
		mpLabel.setText(Game.getPlayer().getMagicPoints() + " Magic ");
		mpPanel.add(mpLabel);
		
		increaseMp.setSize(20,20);
		mpPanel.add(increaseMp);
		
		//agility stat
		agilityPanel.setBounds(280,125,150,25);
		levelingUpScreen.add(agilityPanel);
		
		agilityLabel.setText(Game.getPlayer().getAgility() + " Agility ");
		agilityPanel.add(agilityLabel);
		
		increaseAgility.setSize(20,20);
		agilityPanel.add(increaseAgility);
		
		//carryWeight stat
		carryWeightPanel.setBounds(280,150,150,25);
		levelingUpScreen.add(carryWeightPanel);
		
		carryWeightLabel.setText(Game.getPlayer().getMaxCarryWeight() + " Carry Weight");
		carryWeightPanel.add(carryWeightLabel);
		
		increaseCarryWeight.setSize(20,20);
		carryWeightPanel.add(increaseCarryWeight);
		
		//turnPoints stat
		turnPointPanel.setBounds(280,175,150,25);
		levelingUpScreen.add(turnPointPanel);
		
		turnPointLabel.setText(Game.getPlayer().getTurnPoints() + " Turn Points ");
		turnPointPanel.add(turnPointLabel);
		
		increaseTurnPoints.setSize(20,20);
		turnPointPanel.add(increaseTurnPoints);
		
		//confirm button
		confirm.setBounds(280, 210, 100, 30);
		levelingUpScreen.add(confirm);
		
		reset.setBounds(280, 240, 100, 30);
		levelingUpScreen.add(reset);
		
		//button actionListeners
		increaseHp.addActionListener(new incrementHp());
		increaseMp.addActionListener(new incrementMp());
		increaseStr.addActionListener(new incrementStr());
		increaseAgility.addActionListener(new incrementAgility());
		increaseCarryWeight.addActionListener(new incrementCarryWeight());
		increaseTurnPoints.addActionListener(new incrementTurnPoints());
		reset.addActionListener(new resetAction());
		confirm.addActionListener(new confirmAction());
	}

	public static void addlevelingUpScreen(JPanel panel) { // used to add the attribution selection panel to the game panel.
		panel.add(levelingUpScreen);
	}
	
	public static void showAttributeScreen(boolean a){ // used to bring up the attribute panel.
		levelingUpScreen.setVisible(a);
		Game.optionsvisible(!a);
	}

	static class incrementHp implements ActionListener { // increases the players health

		public void actionPerformed(ActionEvent event) {
			if(attributePoints > 0) {
			tempStats[0]++;
			if(Game.getPlayer().getLevel() >= 5){
				Game.getPlayer().setCurrentHealth(-50);
				Game.getPlayer().setMaxHealth(2);
			}else{
				Game.getPlayer().setMaxHealth(1);
				Game.getPlayer().setCurrentHealth(-25);
			}
			hpLabel.setText(Game.getPlayer().getMaxHealth() + " Health ");
			attributePoints--;
			attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
			}
		}
	}
	
	static class incrementStr implements ActionListener { // increases the players strength.

		public void actionPerformed(ActionEvent event) {
			if(attributePoints > 0) {
			tempStats[2]++;
			Game.getPlayer().setStrength(1);
			strLabel.setText(Game.getPlayer().getStrength() + " Strength ");
			attributePoints--;
			attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
			}
		}
	}
	
	static class incrementMp implements ActionListener { // increases the players magic points

		public void actionPerformed(ActionEvent event) {
			if(attributePoints > 0) {
			tempStats[1]++;
			Game.getPlayer().setMaxMagicPoints(1);
			Game.getPlayer().setMagicPoints(-1);
			mpLabel.setText(Game.getPlayer().getMaxMagicPoints() + " Magic ");
			attributePoints--;
			attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
			}
		}
	}
	
	static class incrementAgility implements ActionListener { // increases the player's agility
	
		public void actionPerformed(ActionEvent event) {
			if(attributePoints > 0) {
			tempStats[3]++;
			Game.getPlayer().setAgility(1);
			agilityLabel.setText(Game.getPlayer().getAgility() + " Agility ");
			attributePoints--;
			attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
			}
		}
	}
	
	static class incrementCarryWeight implements ActionListener { // increase the player's carry weight
		
		public void actionPerformed(ActionEvent event) {
			if(attributePoints > 0){
				tempStats[4]++;
				Game.getPlayer().setMaxCarryWeight(1);
				carryWeightLabel.setText(Game.getPlayer().getMaxCarryWeight() + " Carry Weight");
				attributePoints--;
				attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
			}
		}
	}
	
	static class incrementTurnPoints implements ActionListener { // increases the turn point attribute
		
		public void actionPerformed(ActionEvent event) {
			if(attributePoints > 0 && Game.getPlayer().getTurnPoints() < 4){
				tempStats[5]++;
				Game.getPlayer().setTurnPoints(1);
				turnPointLabel.setText(Game.getPlayer().getTurnPoints() + " Turn Points ");
				attributePoints--;
				attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
				
				if(Game.getPlayer().getTurnPoints() == 4){
					increaseTurnPoints.setVisible(false);
					turnPointLabel.setText(Game.getPlayer().getTurnPoints() + " Turn Points (Maxed)");
				}
			}
		}
	}
	
	static class resetAction implements ActionListener { // resets attribute points entered when leveling up, does not reset all attribute points.
		
		public void actionPerformed(ActionEvent event){
			if(Game.getPlayer().getLevel() >= 5){
				Game.getPlayer().setMaxHealth(-1*tempStats[0]*2);
				Game.getPlayer().setCurrentHealth(25*tempStats[0]*2);
			}else{
				Game.getPlayer().setMaxHealth(-1*tempStats[0]);
				Game.getPlayer().setCurrentHealth(25*tempStats[0]);
			}
			hpLabel.setText(Game.getPlayer().getMaxHealth() + " Health ");
			
			Game.getPlayer().setMaxMagicPoints(-1*tempStats[1]);
			Game.getPlayer().setMagicPoints(tempStats[1]);
			mpLabel.setText(Game.getPlayer().getMaxMagicPoints() + " Magic ");
			
			Game.getPlayer().setStrength(-1*tempStats[2]);
			strLabel.setText(Game.getPlayer().getStrength() + " Strength ");
			
			Game.getPlayer().setAgility(-1*tempStats[3]);
			agilityLabel.setText(Game.getPlayer().getAgility() + " Agility ");
			
			Game.getPlayer().setMaxCarryWeight(-1*tempStats[4]);
			carryWeightLabel.setText(Game.getPlayer().getMaxCarryWeight() + " Carry Weight");
			
			Game.getPlayer().setTurnPoints(-1*tempStats[5]);
			if(Game.getPlayer().getTurnPoints() != 4)
				turnPointLabel.setText(Game.getPlayer().getTurnPoints() + " Turn Points ");
			
			if(Game.getPlayer().getTurnPoints() < 5){
				increaseTurnPoints.setVisible(true);
			}
			
			for(int i = 0; i < tempStats.length; i++){
				tempStats[i] = 0;
			}
			
			if(Game.getPlayer().getLevel() == 1){
					attributePoints = 9;
			}else{
					attributePoints = 4;
			}
			
			attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
		}
	}
	
	static class confirmAction implements ActionListener { // makes the attribute points that you have selected final.
									 // In order to make the attribute points final you must spend all the free points
		public void actionPerformed(ActionEvent event){
			if(attributePoints == 0){
			for(int i = 0; i < tempStats.length; i++){
				tempStats[i] = 0;
			}
			
			Game.setTurnPoints(Game.getPlayer().getTurnPoints());
			Game.getPlayer().updateHpLabel();
			Game.getPlayer().updateMpLabel();
			Game.getPlayer().updateTurnPoints(0);
			attributePoints = 4;
			
			attributePointsLabel.setText("Free Attribute Points: " + attributePoints);
			
			levelingUpScreen.setVisible(false);
			Game.optionsvisible(true);
			Game.createWave();
			
			}
		}
	}
}








