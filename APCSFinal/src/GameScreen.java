import java.awt.Color;

import processing.core.PApplet;

public class GameScreen extends PApplet
{
	private static final float ORIGINAL_WIDTH = 800, ORIGNAL_HEIGHT = 600;
	private StartMenu startMenu;
	private Player guy ;
	
	public GameScreen() 
	{
		startMenu = new StartMenu();
		guy = new Player(50,50,50,50);
	}
	
	public void setup() 
	{
		guy.setup(this);
	}
	
	public void draw() 
	{
		scale(width / ORIGINAL_WIDTH, height / ORIGNAL_HEIGHT);
		background( Color.WHITE.getRGB() );
		startMenu.draw(this);
		guy.draw(this);
	}
}
