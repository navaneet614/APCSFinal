
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScreen extends PApplet {
	public final float ORIGINAL_WIDTH = 800, ORIGINAL_HEIGHT = 600;
	public final float LEFT_BOUND = 0;
	private StartMenu startMenu;
	private  Menu currentMenu;
	private Player guy;
	private God god;
	private HashSet<Integer> keys;
	private ArrayList<Platform> platforms;
	private ArrayList<Obstacle> obstacles;
	private PImage background;
	

	public GameScreen() {
		startMenu = new StartMenu();
		currentMenu = startMenu;
		guy = new Player(50, 50, 50, 50);
		god = new God(width/2, height/4, 80, 100);
		keys = new HashSet<Integer>();
		obstacles = new ArrayList<Obstacle>();
		platforms = new ArrayList<Platform>();
		platforms.add(new Platform(0, 200, 200, 40, "platform.png"));
	}

	public void setup() {
		guy.setup(this);
		god.setup(this);
		for(Platform p:platforms) {
			p.setup(this);
		}
	}

	public void draw() 
	{
		
		scale(width / ORIGINAL_WIDTH, height / ORIGINAL_HEIGHT);
		background(Color.WHITE.getRGB());
		if(currentMenu!=null) {
			currentMenu.draw(this);
		}
		
		if(currentMenu == null) {
			guy.update(keys,this);
			guy.draw(this);	
			for(Obstacle o : obstacles) {
				o.draw(this);
			}
			guy.draw(this);	
			for(Platform p:platforms) {
				p.draw(this);
			}
		}
	}

	public void keyPressed() {
		/*
		 * char v = key; if(Character.toUpperCase(v) == 'A') { guy.moveDirection(-6); }
		 * if(Character.toUpperCase(v) == 'D') { guy.moveDirection(6); }
		 * if(Character.toUpperCase(v)== 'W') { guy.jump(50);
		 * 
		 * }
		 */
		keys.add(this.keyCode);
	}
	
	public void keyReleased() {
		keys.remove(this.keyCode);

	}

	public void mousePressed() {
		if (currentMenu != null) {
			String buttonText = currentMenu.checkIfButtonsPressed((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGINAL_HEIGHT)));
			if (buttonText == null) {
				return;
			}
			currentMenu.doButtonAction(buttonText, this);
		}

	}

	public void mouseMoved() {
		if (currentMenu != null) {
			currentMenu.updateButtons((int)(mouseX / (width / ORIGINAL_WIDTH)), (int)(mouseY / (height / ORIGINAL_HEIGHT)));
		}
	}

	public void changeMenuShowing(String menu) {
		if (menu == null) {
			currentMenu = null;
			return;
		}
		if (menu.equals("start")) {
			currentMenu = startMenu;
		} else {
			currentMenu = null;
		}
	}
	
	public void hitDetection() {
		Rectangle gRect = guy.getBoundingRect();
		Rectangle oRect;
		
		for(int i = 0; i < obstacles.size(); i++) {
			oRect = obstacles.get(i).getBoundRect();
			if(gRect.intersects(oRect)) {
				guy.takeDamage(obstacles.get(i).getDamage());
				
				if(obstacles.get(i) instanceof Glue) {
					guy.setSlow(true);
				}
				
				if(obstacles.get(i) instanceof Block) {
					
				}
			}
			guy.setSlow(false);
		}
	}
	
	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}
}

	public void draw() 
	{
		
		scale(width / ORIGINAL_WIDTH, height / ORIGNAL_HEIGHT);
		background( Color.WHITE.getRGB() );
		if(currentMenu!=null) {
			currentMenu.draw(this);
		}
		
		
		
		if(currentMenu == null) {
			guy.update(keys,this);
			guy.draw(this);	
			
			t.draw(this);
		}
	}

	public void keyPressed() {
		/*
		 * char v = key; if(Character.toUpperCase(v) == 'A') { guy.moveDirection(-6); }
		 * if(Character.toUpperCase(v) == 'D') { guy.moveDirection(6); }
		 * if(Character.toUpperCase(v)== 'W') { guy.jump(50);
		 * 
		 * }
		 */
		keys.add(this.keyCode);
	}
	
	public void keyReleased() {
		keys.remove(this.keyCode);

	}

	public void mousePressed() {
		if (currentMenu != null) {
			String buttonText = currentMenu.checkIfButtonsPressed((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGNAL_HEIGHT)));
			if (buttonText == null) {
				return;
			}
			currentMenu.doButtonAction(buttonText, this);
		}

	}

	public void mouseMoved() {
		if (currentMenu != null) {
			currentMenu.updateButtons((int)(mouseX / (width / ORIGINAL_WIDTH)), (int)(mouseY / (height / ORIGNAL_HEIGHT)));
		}
	}

	public void changeMenuShowing(String menu) {
		if (menu == null) {
			currentMenu = null;
			return;
		}
		if (menu.equals("start")) {
			currentMenu = startMenu;
		} else {
			currentMenu = null;
		}
	}
}
