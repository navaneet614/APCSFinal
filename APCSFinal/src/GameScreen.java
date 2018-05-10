
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import processing.core.PApplet;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * The GameScreen class is the class that enables the GUI. It
 * uses the Java Processing library in order to perform various
 * actions such as drawing, moving, scrolling, and animating. Quite
 * simply, GameScreen is the "backbone" of the game.
 *
 */
public class GameScreen extends PApplet {
	public final float ORIGINAL_WIDTH = 800, ORIGINAL_HEIGHT = 600;
	private int levelLength = 2000 - 50, densityOfBlocks = 2;
	private StartMenu startMenu;
	private Menu currentMenu;
	private Player guy;
//	private God god;
	private HashSet<Integer> keys;
	private ArrayList<Obstacle> obstacles;
	private int distanceTranslated;
	private enum gameModes {singleplayer, localMultiplayer}
	private gameModes gameMode;

	public GameScreen() {
		startMenu = new StartMenu();
		currentMenu = startMenu;
		distanceTranslated = 0;
		guy = new Player(50, 50, 50, 50);
//		god = new God(450, 100, 120, 140);
		keys = new HashSet<Integer>();
		obstacles = new ArrayList<Obstacle>();
	}

	public void setup() {
		ImageLoader.loadAllImages(this, "");
		guy.setup(this);
//		god.setup(this);
		 for(int i = 0;i<=(ORIGINAL_WIDTH+levelLength+50);i+=50)
		 {
			 obstacles.add(new Block(i, ORIGINAL_HEIGHT - 50, 50, 50));
			 int y = (int)(Math.random()*(ORIGINAL_HEIGHT-50))/50*50+50;
			 for(int j = 0;j<Math.random()*densityOfBlocks;j++) 
			 {
				 obstacles.add(new Block(i + j*50, y, 50, 50));
			 }
			 if ( i % 200 == 0 ) 
			 {
				 obstacles.add( new Spike( i, y-25, 50, 25 ) );
			 }
		 }
//		obstacles.add(new Block(0, 550, 50, 50));
//		obstacles.add(new Block(50, 550, 50, 50));
//		obstacles.add(new Block(100, 550, 50, 50));
//		
//		obstacles.add(new Block(200, 450, 50, 50));
//		obstacles.add(new Block(250, 450, 50, 50));
//		obstacles.add(new Block(300, 450, 50, 50));
//		
//		obstacles.add(new Block(400, 400, 50, 50));
//		obstacles.add(new Block(450, 400, 50, 50));
//		obstacles.add(new Block(500, 400, 50, 50));
//		
//		obstacles.add(new Block(600, 250, 50, 50));
//		obstacles.add(new Block(650, 250, 50, 50));
//		obstacles.add(new Block(700, 250, 50, 50));
//		
//		obstacles.add(new Block(800, 150, 50, 50));
//		obstacles.add(new Block(850, 150, 50, 50));
//		obstacles.add(new Block(900, 150, 50, 50));
//		
//		obstacles.add(new Block(1100, 250, 50, 50));
//		obstacles.add(new Block(1150, 250, 50, 50));
//		obstacles.add(new Block(1200, 250, 50, 50));
//		
//		obstacles.add(new Block(1300, 400, 50, 50));
//		obstacles.add(new Block(1350, 400, 50, 50));
//		obstacles.add(new Block(1400, 400, 50, 50));
//		
//		obstacles.add(new Block(1500, 550, 50, 50));
//		obstacles.add(new Block(1550, 550, 50, 50));
//		obstacles.add(new Block(1600, 550, 50, 50));
//		
//		obstacles.add(new Turret(400, 150, 50, 50, 5 * Math.PI / 4));

		
		for (Obstacle o : obstacles) {
			o.setup(this);
		}
	}

	public void draw() {
		scale(width / ORIGINAL_WIDTH, height / ORIGINAL_HEIGHT);
		background(Color.WHITE.getRGB());
		if (currentMenu != null) {
			currentMenu.draw(this);
		} else if (currentMenu == null) {
			hitDetection();
			guy.update(keys, this);
			guy.draw(this);
			for (Obstacle o : obstacles) {
				o.draw(this);
			}
			guy.draw(this);
//			god.draw(this);
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
			currentMenu.updateButtons((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGINAL_HEIGHT)));
		}
	}

	public void changeMenuMode(String menumode) {
		
		if(menumode.equals("singleplayer")) {
			gameMode = gameModes.singleplayer;
			currentMenu = null;
		} else if(menumode.equals("localmultiplayer")) {
			gameMode = gameModes.localMultiplayer;
			currentMenu = null;
		} else if(menumode.equals("main")) {
			currentMenu = startMenu;
		} else if(menumode.equals("options")) {
//			currentMenu = optionsMenu;
		}
		else {
			currentMenu = null;
		}
	}

	public void hitDetection() {
		Rectangle gRect = guy.getBoundingRect();
		Rectangle oRect;

		for (int i = 0; i < obstacles.size(); i++) {
			oRect = obstacles.get(i).getBoundRect();
			if (gRect.intersects(oRect)) {
				guy.takeDamage(obstacles.get(i).getDamage());

				if (obstacles.get(i) instanceof Glue) {
					guy.setSlow(true);
				}

				else if (obstacles.get(i) instanceof Block) 
				{
					int offset = 20;
					if (gRect.intersectsLine(oRect.getX() + offset, oRect.getY(), oRect.getMaxX() - offset,
							oRect.getY())) {
//						System.out.println("top");
						guy.setY(oRect.getY() - guy.getHeight());
						guy.setVY(0);
						guy.cancelJump();
					}
					else if (gRect.intersectsLine(oRect.getX(), oRect.getY() + offset, oRect.getX(),
							oRect.getMaxY() - offset)) {
//						System.out.println("left");
						guy.cancelJump();
						guy.setX(oRect.getX() - guy.getHeight());
					}
					else if (gRect.intersectsLine(oRect.getMaxX(), oRect.getY() + offset, oRect.getMaxX(),
							oRect.getMaxY() - offset)) {
//						System.out.println("right");
						guy.cancelJump();
						guy.setX(oRect.getMaxX());
					}
					if (gRect.intersectsLine(oRect.getX() + offset, oRect.getMaxY(), oRect.getMaxX() - offset, oRect.getMaxY())) {
//						System.out.println("bottom");
						guy.cancelJump();
						guy.setY(oRect.getMaxY());
					}
				}
				else if ( obstacles.get(i) instanceof Spike ) 
				{
					//System.out.println( "Hit spike!" );
				}
			}
		}
		guy.setSlow(false);
		if (gRect.intersectsLine(0, ORIGINAL_HEIGHT, ORIGINAL_WIDTH, ORIGINAL_HEIGHT)) {
			guy.setVY(0);
			guy.setY(ORIGINAL_HEIGHT - guy.getHeight());
			guy.cancelJump();
		}
//		System.out.println(guy.getX() + guy.getWidth());
	}

	public boolean translate(int x) {
//		System.out.println(distanceTranslated);
		if(x<0 && distanceTranslated<=0) {
			return false;
		}
		else if(x>0 && distanceTranslated>=levelLength) {
//			System.out.println("here");
			return false;
		}
		for (Obstacle o : obstacles) {
			o.setX((float) (o.getX() - x));
			if(o instanceof Turret) {
				for(Bullet b:((Turret) o).bullets()) {
					b.setX((float) (b.getX() - x));
				}
			}
		}
		distanceTranslated += x;
		return true;
	}
}
