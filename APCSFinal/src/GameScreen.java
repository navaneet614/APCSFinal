import java.awt.Color;

import processing.core.PApplet;

public class GameScreen extends PApplet
{
	private static final float ORIGINAL_WIDTH = 800, ORIGNAL_HEIGHT = 600;
	StartMenu startMenu;
	Menu currentMenu;
	Player guy;
	God dog;
	Line ground;
	
	public GameScreen() 
	{
		startMenu = new StartMenu();
		currentMenu = null;
		guy = new Player(50,50,50,50);
		dog = new God();
		ground = new Line(0, 600, 800, 600);
	}
	
	public void setup() 
	{
		guy.setup(this);
	}
	
	public void draw() 
	{
		scale(width / ORIGINAL_WIDTH, height / ORIGNAL_HEIGHT);
		background( Color.WHITE.getRGB() );
		//currentMenu = startMenu;
		if(currentMenu!=null) {
			currentMenu.draw(this);
		}
		
		if(currentMenu == null) {
		guy.draw(this);
		
		if ( ! guy.intersects(ground) )
			guy.fall();
		
		}
	}
	
	public void keyPressed() {
		/*char v = key;
		if(Character.toUpperCase(v) == 'A') {
			guy.moveDirection(-6);
		}
		if(Character.toUpperCase(v) == 'D') {
			guy.moveDirection(6);
		}
		if(Character.toUpperCase(v)== 'W') {
			guy.jump(50);
			
		}*/
		if (key == CODED) {
		    if (keyCode == UP) {
		      guy.jump(100);;
		    } else if (keyCode == DOWN) {
		    
		    } else if (keyCode == LEFT) {
		      guy.moveDirection(-20);
		    } else if (keyCode == RIGHT) {
		      guy.moveDirection(20);;
		    }  
		  } 
		
			
	}
}	
