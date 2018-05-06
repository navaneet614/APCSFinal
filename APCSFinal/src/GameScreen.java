import java.awt.Color;

import processing.core.PApplet;

public class GameScreen extends PApplet
{
	private static final float ORIGINAL_WIDTH = 800, ORIGNAL_HEIGHT = 600;
	StartMenu startMenu;
	
	public GameScreen() 
	{
		startMenu = new StartMenu();
	}
	
	public void setup() 
	{
		
	}
	
	public void draw() 
	{
		scale(width / ORIGINAL_WIDTH, height / ORIGNAL_HEIGHT);
		background( Color.WHITE.getRGB() );
		startMenu.draw(this);
	}
}
