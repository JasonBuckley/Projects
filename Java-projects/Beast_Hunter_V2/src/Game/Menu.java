package Game;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
	public static JFrame window = new JFrame();
	private static JPanel menu = new JPanel(new BorderLayout());
	private static JPanel settingsPanel = new JPanel(null);
	//------------------------------------------------------ Settings Action
	private static JPanel settingsLabelPanel = new JPanel();
	private static JLabel settingsLabel = new JLabel("Settings");
	private static JLabel graphicSettingLabel = new JLabel("Graphic Settings");
	private static JPanel graphicSettingsLabelPanel = new JPanel();
	private static JPanel graphicSettings = new JPanel();
	private static ButtonGroup onOff = new ButtonGroup();
	private static JRadioButton graphicSetOn = new JRadioButton("On");
	private static JRadioButton graphicSetOff = new JRadioButton("Off");
	private static JPanel backPanel = new JPanel();
	private static JButton backButton = new JButton("Back");
	//------------------------------------------------------ offAction variables
	private static JPanel offActionPanel = new JPanel();
	private static JLabel offActionLabel = new JLabel("Do you want to go back? It is kind of hard to play without graphics on.");
	private static JPanel goBackPanel = new JPanel();
	private static JButton goBackButton = new JButton("Go Back");
	
	//------------------------------------------------------ info page
	private static JPanel infoPanel = new JPanel(null);
	private static JPanel infoButtonPanel = new JPanel();
	private static JTextArea infoText = new JTextArea();
	private static JButton infoButton = new JButton("Information");
	private static JButton backStart = new JButton("Back");
	
	//------------------------------------------------------ Hello World components
	private static JPanel helloWorldPanel = new JPanel(null);
	private static JLabel helloWorldLabel = new JLabel("Hello World");
	private static JButton helloWorldButton = new JButton("Hello World");
	private static JButton helloBackStart = new JButton("Bye cruel World");
	
	
	public static void menu() { // creates the start menu and all of its components
		settings();
		graphicsOff();
		
		window.setSize(720, 480);
		window.setTitle("Beasts.Beast Hunter");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
	
		
		menu.setBounds(0,0,720,480);
		
		JButton settings = new JButton("Settings");

		JButton start = new JButton("Start");

		JButton exit = new JButton("Exit");

		JLabel title = new JLabel("Beasts.Beast Hunter");
		title.setFont(new Font("Serif", Font.PLAIN, 28));

		JPanel startPanel = new JPanel();
		startPanel.setBounds(310, 60, 100, 40);
		startPanel.add(start);

		JPanel settingsPanel = new JPanel();
		settingsPanel.setBounds(310, 120, 100, 40);
		settingsPanel.add(settings);

		JPanel exitPanel = new JPanel();
		exitPanel.setBounds(310, 300, 100, 40);
		exitPanel.add(exit);

		JPanel titlePanel = new JPanel();
		titlePanel.add(title);
		
		helloWorldButton.setBounds(310, 240, 100, 40);
		menu.add(helloWorldButton);
		
		//----------------------------- information Panel
		infoPanel.setSize(720, 480);
		infoPanel.setVisible(false);
		infoPanel.add(backStart);
		infoPanel.add(infoText);
		infoText.setBounds(40, 10, 600, 300);
		infoText.setLineWrap(true);
		//------------------------------ the basics of the game
		infoText.setEditable(false);
		infoText.setText("The main objective of the game is to kill beasts to level up.  You can click on whatever beast you want to attack first.  "
				+ "Some beasts may have abilities that make them a priority target.  But before you get started killing beasts you must first select what attributes you want. "
				+ "Click the \"+\" symbol next to the desire attribute you want to \ninvest in, and confirm when you are ready to continue.  If you want to change attributes"
				+ " during a level up press \nreset. In game navigate what moves you want to make by using the moves panel at the bottom. Be careful with the items, "
				+ "some items may get thrown when you click them.  After the end of each turn you will have a chance \nto buy items, and possibly level up. "
				+ "Have fun. \n\n "
				+ "Tips: \n"
				+ "   1. Clicking on a Items.Item will consume it if it is a bottle, or throw if it is armor or a sword.\n"
				+ "   2. The Beasts.Bear doubles its damage each turn. \n"
				+ "   3. Order 66 uses all magic points and turn points.\n");
		
		
		backStart.setBounds(300, 400, 100, 50);
		
		infoButtonPanel.setBounds(310, 180, 100, 30);
		infoButtonPanel.add(infoButton);
		
		//----------------------------- Hello Wolrd
		helloWorldPanel.setSize(720,480);
		helloWorldPanel.setVisible(false);
		helloWorldPanel.add(helloBackStart);
		helloBackStart.setBounds(275, 400, 175, 35);
		helloWorldPanel.add(helloWorldLabel);
		helloWorldLabel.setBounds(40 , 30, 720, 200);
		helloWorldLabel.setFont(new Font("Serif", Font.PLAIN, 120));
		
		//----------------------------- adding panels to the window
		menu.add(startPanel);
		menu.add(settingsPanel);
		menu.add(exitPanel);
		menu.add(infoButtonPanel);
		menu.add(titlePanel);
		
		window.add(infoPanel);
		window.add(helloWorldPanel);
		window.add(menu);
		
		//------------------------------ actionListeners
		start.addActionListener(new StartAction());
		settings.addActionListener(new SettingsAction());
		exit.addActionListener(new ExitAction());
		infoButton.addActionListener(new InformationAction());
		backStart.addActionListener(new BackAction());
		helloWorldButton.addActionListener(new HelloWorldButtonAction());
		helloBackStart.addActionListener(new BackAction());

	}
	
	public static void settings(){ // creates the setting panel and adds it to the window
		settingsPanel.setSize(720,480);
		settingsPanel.setVisible(false);
		settingsPanel.setBackground(Color.blue);
		settingsLabelPanel.setBounds(310,20,100, 40);
		
		settingsLabel.setFont(new Font("Serif", Font.PLAIN, 28));
		settingsLabelPanel.add(settingsLabel);
		
		graphicSettingLabel.setSize(100,40);
		
		graphicSettingsLabelPanel.setBounds(310, 70, 100, 40);
		graphicSettingsLabelPanel.add(graphicSettingLabel);
		
		graphicSettings.setBounds(310,100,100,40);
		
		graphicSettings.add(graphicSetOn);
		onOff.add(graphicSetOn);
		graphicSetOn.setSelected(true);
		
		graphicSettings.add(graphicSetOff);
		onOff.add(graphicSetOff);
		
		backPanel.setBounds(310,150,100,40);
		
		backPanel.add(backButton);
		
		settingsPanel.add(graphicSettings);
		settingsPanel.add(graphicSettingsLabelPanel);
		settingsPanel.add(backPanel);
		settingsPanel.add(settingsLabelPanel);
		
		window.add(settingsPanel);
		
		graphicSetOff.addActionListener(new OffAction());
		backButton.addActionListener(new BackAction());
		
	}
	
	public static void graphicsOff(){ // turns the graphics off in the start menu, and prompts the user to go back.
		offActionPanel.setVisible(false);
		offActionPanel.setSize(720,480);
		offActionPanel.setBackground(Color.black);
		
		offActionLabel.setForeground(Color.WHITE);
		offActionPanel.add( offActionLabel);
		
		goBackPanel.setBounds(50, 50, 50, 50);
		offActionPanel.add(goBackPanel);
		
		goBackPanel.add(goBackButton);
		
		window.add(offActionPanel);
		
		goBackButton.addActionListener(new GoBackAction());
	}
	
	static class StartAction implements ActionListener{ // starts the game
	
		public void actionPerformed(ActionEvent event) {
			menu.setVisible(false);
			Game.addGame(window);
			Game.game();
			
		}
	}
	
	static class ExitAction implements ActionListener{ // exits the game
		
		public void actionPerformed(ActionEvent event) {
			
			window.dispose();
		}
	}
	
	static class SettingsAction implements ActionListener{ // makes the setting screen visible
		
		public void actionPerformed(ActionEvent event){
			menu.setVisible(false);
			settingsPanel.setVisible(true);
		}
		
		public static void setGraphicOnOn(){ // turns the graphics back on
			graphicSetOn.setSelected(true);
		}
		
	}
	
	static class OffAction implements ActionListener{ // turns the graphics off
		
		public void actionPerformed(ActionEvent event){
			settingsPanel.setVisible(false);
			offActionPanel.setVisible(true);
		}
	}
	
	static class GoBackAction implements ActionListener{ // go back to having the graphics on
		public void actionPerformed(ActionEvent event){
			offActionPanel.setVisible(false);
			
			settingsPanel.setVisible(true);
			
			SettingsAction.setGraphicOnOn();
		}
	}
	
	static class InformationAction implements ActionListener{ // brings the user to the info page, and tells them about the game.
		
		public void actionPerformed(ActionEvent e){
			infoPanel.setBackground(Color.WHITE);
			infoPanel.setVisible(true);
			menu.setVisible(false);
		}
	}
	
	static class HelloWorldButtonAction implements ActionListener{ // opens the hello world program.
		
		public void actionPerformed(ActionEvent e){
			helloWorldPanel.setBackground(Color.WHITE);
			helloWorldPanel.setVisible(true);
			menu.setVisible(false);
		}
	}
	
	static class BackAction implements ActionListener{ // goes back to the start menu.
		public void actionPerformed(ActionEvent e){
			settingsPanel.setVisible(false);
			infoPanel.setVisible(false);
			helloWorldPanel.setVisible(false);
			menu.setVisible(true);
		}
	}
	
	public static void MenuVisible(boolean a){ // set the visibility of the menu screen.
		menu.setVisible(a);
	}
}




