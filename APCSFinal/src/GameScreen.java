import java.awt.Color;

import processing.core.PApplet;

public class GameScreen extends PApplet
{
	public final float ORIGINAL_WIDTH = 800, ORIGNAL_HEIGHT = 600;
	StartMenu startMenu;
	Menu currentMenu;
	Player guy;
	God god;
	Line ground;
	
	public GameScreen() 
	{
		startMenu = new StartMenu();
		currentMenu = startMenu;
		guy = new Player(50,50,50,50);
		god = new God(0, 375, 50, 70);
		ground = new Line(0, 600, 800, 600);
	}
	
	public void setup() 
	{
		guy.setup(this);
		god.setup(this);
	}
	
	public void draw() 
	{
		scale(width / ORIGINAL_WIDTH, height / ORIGNAL_HEIGHT);
		background( Color.WHITE.getRGB() );
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
		    	  god.throwObstacle(new LightningBolt(), 0, (float)Math.random()*601 );	
		    } else if (keyCode == LEFT) {
		      guy.moveDirection(-20);
		    } else if (keyCode == RIGHT) {
		      guy.moveDirection(20);;
		    }  
		  } 
		
			
	}
	
	public void mousePressed() {
		if(currentMenu!=null) {
			String buttonText = currentMenu.checkIfButtonsPressed((int)(mouseX / (width / ORIGINAL_WIDTH)), (int)(mouseY/ (height / ORIGNAL_HEIGHT)));
			if(buttonText==null) {
				return;
			}
			currentMenu.doButtonAction(buttonText, this);
		}
		
	}
	
	public void changeMenuShowing(String menu) {
		if(menu==null) {
			currentMenu = null;
			return;
		}
		if(menu.equals("start")) {
			currentMenu = startMenu;
		} else {
			currentMenu = null;
		}
	}
}	
