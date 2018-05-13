
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;

import processing.core.PApplet;

/**
 * 
 * @author Navaneet Kadaba
 * 
 *         The GameScreen class is the class that enables the GUI. It uses the
 *         Java Processing library in order to perform various actions such as
 *         drawing, moving, scrolling, and animating. Quite simply, GameScreen
 *         is the "backbone" of the game.
 *
 */
public class GameScreen extends PApplet {
	public final float ORIGINAL_WIDTH = 800, ORIGINAL_HEIGHT = 600;
	private int levelLength = 2000 - 50, densityOfBlocks = 2;
	private StartMenu startMenu;
	private Menu currentMenu;
	private PauseMenu pauseMenu;
	private DeathMenu deathMenu;
	private GodScreen godScreen;
	private Player guy;
	 private God god;
	private HashSet<Integer> keys;
	private ArrayList<Obstacle> obstacles;
	private int distanceTranslated;
	private Point mouseP;

	private enum gameModes {
		singleplayer, localMultiplayer
	}

	private gameModes gameMode;
	private Thread thread;
	private boolean running;

	public GameScreen() {
		startMenu = new StartMenu();
		pauseMenu = new PauseMenu();
		deathMenu = new DeathMenu();
		currentMenu = startMenu;
		distanceTranslated = 0;
		guy = new Player(50, 450, 50, 50);
		god = new God(450, 100, 120, 140, 30);
		keys = new HashSet<Integer>();
		obstacles = new ArrayList<Obstacle>();
		godScreen = new GodScreen(0,0,800,100,god);
		mouseP = new Point(0,0);
	}

	public void reset() {
		distanceTranslated = 0;
		guy = new Player(50, 450, 50, 50);
		guy.setup(this);
		// god = new God(450, 100, 120, 140);
		keys.clear();
		obstacles.clear();
		doLvl(0);
	}

	public void setup() {
		this.frameRate(60);
//		noLoop();
		ImageLoader.loadAllImages(this, "");
		guy.setup(this);
		// god.setup(this);
		for (Obstacle o : obstacles) {
			o.setup(this);
		}
		doLvl(3);
	}

	public void doLvl(int lvlNum) {
		obstacles.clear();
		for (int i = 0; i <= (ORIGINAL_WIDTH + levelLength + 50); i += 50)
			obstacles.add(new Block(i, ORIGINAL_HEIGHT - 50, 50, 50));
		if (lvlNum == 0) {
			// rando lvl generation
			for (int i = 0; i <= (ORIGINAL_WIDTH + levelLength + 50); i += 50) {
				obstacles.add(new Block(i, ORIGINAL_HEIGHT - 50, 50, 50));
				int y = (int) (Math.random() * (ORIGINAL_HEIGHT - 50)) / 50 * 50 + 50;
				for (int j = 0; j < Math.random() * densityOfBlocks; j++) {
					/*
					 * if ( j % 10 == 3 ) { obstacles.add( new FadingBlock( i + j*50, y, 50, 50 ) );
					 * } else
					 */
					{
						obstacles.add(new Block(i + j * 50, y, 50, 50));
					}
				}
				if (i % 200 == 0) {
					obstacles.add(new Spike(i, y - 25, 50, 25));
				}
			}
		}
		if (lvlNum == 1) {
			obstacles.add(new Block(0, 550, 50, 50));
			obstacles.add(new Block(50, 550, 50, 50));
			obstacles.add(new Block(100, 550, 50, 50));

			obstacles.add(new Block(200, 450, 50, 50));
			obstacles.add(new Block(250, 450, 50, 50));
			obstacles.add(new Block(300, 450, 50, 50));

			obstacles.add(new Block(400, 400, 50, 50));
			obstacles.add(new Block(450, 400, 50, 50));
			obstacles.add(new Block(500, 400, 50, 50));

			obstacles.add(new Block(600, 250, 50, 50));
			obstacles.add(new Block(650, 250, 50, 50));
			obstacles.add(new Block(700, 250, 50, 50));

			obstacles.add(new Block(800, 150, 50, 50));
			obstacles.add(new Block(850, 150, 50, 50));
			obstacles.add(new Block(900, 150, 50, 50));

			obstacles.add(new Block(1000, 250, 50, 50));
			obstacles.add(new Block(1050, 250, 50, 50));
			obstacles.add(new Block(1100, 250, 50, 50));

			obstacles.add(new Block(1200, 400, 50, 50));
			obstacles.add(new Block(1250, 400, 50, 50));
			obstacles.add(new Block(1300, 400, 50, 50));

			obstacles.add(new Block(1400, 450, 50, 50));
			obstacles.add(new Block(1450, 450, 50, 50));
			obstacles.add(new Block(1500, 450, 50, 50));
			
			obstacles.add(new Block(1600, 550, 50, 50));
			obstacles.add(new Block(1650, 550, 50, 50));
			obstacles.add(new Block(1700, 550, 50, 50));

			obstacles.add(new Block(1800, 450, 50, 50));
			obstacles.add(new Block(1850, 450, 50, 50));
			obstacles.add(new Block(1900, 450, 50, 50));

			obstacles.add(new Block(2000, 400, 50, 50));
			obstacles.add(new Block(2050, 400, 50, 50));
			obstacles.add(new Block(2100, 400, 50, 50));

			obstacles.add(new Block(2200, 250, 50, 50));
			obstacles.add(new Block(2250, 250, 50, 50));
			obstacles.add(new Block(2300, 250, 50, 50));

			obstacles.add(new Block(2400, 150, 50, 50));
			obstacles.add(new Block(2450, 150, 50, 50));
			obstacles.add(new Block(2500, 150, 50, 50));
			
//			obstacles.add(new Block(1600, 550, 50, 50));
//			obstacles.add(new Block(1650, 550, 50, 50));
//			obstacles.add(new Block(1700, 550, 50, 50));
		}
		else if (lvlNum == 2) {
			obstacles.add(new Block(200,ORIGINAL_HEIGHT-100, 50, 50));
			obstacles.add(new Block(350,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(500,ORIGINAL_HEIGHT-300, 50, 50));
			obstacles.add(new Block(650,ORIGINAL_HEIGHT-400, 50, 50));
			obstacles.add(new Block(800,ORIGINAL_HEIGHT-300, 50, 50));
			obstacles.add(new Block(950,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(1100,ORIGINAL_HEIGHT-100, 50, 50));
			obstacles.add(new Block(1250,ORIGINAL_HEIGHT-100, 50, 50));
			obstacles.add(new Block(1400,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(1550,ORIGINAL_HEIGHT-300, 50, 50));
			obstacles.add(new Block(1700,ORIGINAL_HEIGHT-400, 50, 50));
			obstacles.add(new Block(1850,ORIGINAL_HEIGHT-300, 50, 50));
			obstacles.add(new Block(2000,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(2150,ORIGINAL_HEIGHT-100, 50, 50));
			obstacles.add(new Block(2300,ORIGINAL_HEIGHT-100, 50, 50));
			obstacles.add(new Block(2450,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(2600,ORIGINAL_HEIGHT-300, 50, 50));
		} 
		else if(lvlNum == 3) {
			obstacles.add(new Block(200,ORIGINAL_HEIGHT-100, 50, 50));
			obstacles.add(new Block(100,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(200,ORIGINAL_HEIGHT-300, 50, 50));
			obstacles.add(new Block(100,ORIGINAL_HEIGHT-400, 50, 50));
			
			obstacles.add(new Block(300,ORIGINAL_HEIGHT-500, 50, 50));
			obstacles.add(new Block(350,ORIGINAL_HEIGHT-500, 50, 50));
			
			obstacles.add(new Block(550,ORIGINAL_HEIGHT-450, 50, 50));
			obstacles.add(new Block(600,ORIGINAL_HEIGHT-450, 50, 50));
			
			obstacles.add(new Block(800,ORIGINAL_HEIGHT-400, 50, 50));
			obstacles.add(new Block(850,ORIGINAL_HEIGHT-400, 50, 50));
			
			obstacles.add(new Block(1050,ORIGINAL_HEIGHT-450, 50, 50));
			obstacles.add(new Block(1100,ORIGINAL_HEIGHT-450, 50, 50));
			
			obstacles.add(new Block(1300,ORIGINAL_HEIGHT-500, 50, 50));
			obstacles.add(new Block(1350,ORIGINAL_HEIGHT-500, 50, 50));
			
			obstacles.add(new Block(1550,ORIGINAL_HEIGHT-450, 50, 50));
			obstacles.add(new Block(1600,ORIGINAL_HEIGHT-450, 50, 50));
			
			obstacles.add(new Block(1800,ORIGINAL_HEIGHT-400, 50, 50));
			obstacles.add(new Block(1850,ORIGINAL_HEIGHT-400, 50, 50));
			
			obstacles.add(new Block(2050,ORIGINAL_HEIGHT-450, 50, 50));
			obstacles.add(new Block(2100,ORIGINAL_HEIGHT-450, 50, 50));
			
			obstacles.add(new Block(2300,ORIGINAL_HEIGHT-500, 50, 50));
			obstacles.add(new Block(2350,ORIGINAL_HEIGHT-500, 50, 50));
			
			obstacles.add(new Block(350,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(500,ORIGINAL_HEIGHT-300, 50, 50));
			
			obstacles.add(new Block(650,ORIGINAL_HEIGHT-100, 50, 50));
			obstacles.add(new Block(650,ORIGINAL_HEIGHT-150, 50, 50));
			obstacles.add(new Block(650,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(650,ORIGINAL_HEIGHT-250, 50, 50));
			obstacles.add(new Block(650,ORIGINAL_HEIGHT-300, 50, 50));
			obstacles.add(new Block(650,ORIGINAL_HEIGHT-350, 50, 50));
			
			
			obstacles.add(new Block(1250,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(1300,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(1350,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(1400,ORIGINAL_HEIGHT-200, 50, 50));
			
			obstacles.add(new Block(2250,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(2300,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(2350,ORIGINAL_HEIGHT-200, 50, 50));
			obstacles.add(new Block(2400,ORIGINAL_HEIGHT-200, 50, 50));
			
			
			
			
		}
		
	}

	public void draw() {
		scale(width / ORIGINAL_WIDTH, height / ORIGINAL_HEIGHT);
		background(Color.WHITE.getRGB());
		if (guy.hearts() <= 0) {
			currentMenu = deathMenu;
		}
		if (currentMenu != null && !currentMenu.equals(godScreen)) {
			currentMenu.draw(this);
		} else if (currentMenu == null || currentMenu.equals(godScreen)) {
			
			if(god.canPlace()) {
			godScreen.draw(this);
			}
			else {
			translate(-distanceTranslated);
			hitDetection();
			guy.update(keys, this);
			guy.draw(this);
			//guy.draw(this);
			// god.draw(this);
			}
			
			for (Obstacle o : obstacles) {
				o.draw(this);
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
		int x = 0;
		if (key == 'p' && currentMenu == null) {
			currentMenu = pauseMenu;
		}
		else if(key == 'd' && god.canPlace()) {
			System.out.println("here");
			x+=10;
			translate(x);
		}else if(key == 'a' && god.canPlace()) {
			x-=10;
			translate(x);
		}
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
			System.out.println("DOTH THIS WORKETH?");
		}
	}
	
	public void mouseClicked() {
		mouseP.setLocation(mouseX, mouseY);
		System.out.println( " " + mouseP.getX() + " " + mouseP.getY() + godScreen.getDragging());
		addObstacle();
	}
	
	public void addObstacle() {
		if(currentMenu.equals(godScreen)&& godScreen.getDragging()) {
			Spike spike = null;
			Glue glue = null;
			Turret turret = null;
		for(Obstacle o : obstacles) {
			if(o.getBoundRect().contains(mouseP)) {
				System.out.println("SUP");
				System.out.println(o.getBoundRect().getX());
				godScreen.setDragging(false);
				god.place();
				String x = godScreen.getObstacleType();
				if(x.equals("spike")) {
					spike = new Spike((float)o.getX(), (float)o.getY()-50, 50, 50);
				}else if(x.equals("glue")) {
					glue = new Glue((float)o.getX(), (float)o.getY(), 50, 05);
				}else if(x.equals("turret")) {
					turret = new Turret((float)o.getX(), (float)o.getY()-50, 50, 50, Math.PI);
				}
			}
		}
		if(spike!=null) {
		obstacles.add(spike);
		spike = null;
		}
		else if(glue!= null) {
			obstacles.add(glue);
			glue = null;
		}else if(turret!=null) {
			obstacles.add(turret);
			turret = null;
		}
	}
	}
	
	

	public void mouseMoved() {
		if (currentMenu != null) {
			currentMenu.updateButtons((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGINAL_HEIGHT)));
		}
	}

	public void changeMenuMode(String menumode) {
//		System.out.println(menumode);
		if (menumode.equals("singleplayer")) {
			gameMode = gameModes.singleplayer;
			currentMenu = godScreen;
//			reset();
		} else if (menumode.equals("localmultiplayer")) {
			gameMode = gameModes.localMultiplayer;
			currentMenu = null;
		} else if (menumode.equals("main")) {
			reset();
			currentMenu = startMenu;
		} else if (menumode.equals("options")) {
			// currentMenu = optionsMenu;
		} else {
			currentMenu = null;
		}
	}

	public void hitDetection() {
		Rectangle gRect = guy.getBoundingRect();
		Rectangle oRect;

		for (int i = 0; i < obstacles.size(); i++) {
			oRect = obstacles.get(i).getBoundRect();
			if (gRect.intersects(oRect)) {

				if (obstacles.get(i) instanceof Glue) {
					guy.setSlow(true);
				}

				/*
				 * else if ( obstacles.get(i) instanceof FadingBlock ) {
				 * 
				 * }
				 */

				else if (obstacles.get(i) instanceof Block) {
					int offset = 20;
					if (gRect.intersectsLine(oRect.getX() + offset, oRect.getY(), oRect.getMaxX() - offset,
							oRect.getY())) {
						// System.out.println("top");
						guy.setY(oRect.getY() - guy.getHeight());
						guy.setVY(0);
						guy.cancelJump();
					} else if (gRect.intersectsLine(oRect.getX(), oRect.getY() + offset, oRect.getX(),
							oRect.getMaxY() - offset)) {
						// System.out.println("left");
						guy.cancelJump();
						guy.setX(oRect.getX() - guy.getHeight());
					} else if (gRect.intersectsLine(oRect.getMaxX(), oRect.getY() + offset, oRect.getMaxX(),
							oRect.getMaxY() - offset)) {
						// System.out.println("right");
						guy.cancelJump();
						guy.setX(oRect.getMaxX());
					}
					if (gRect.intersectsLine(oRect.getX() + offset, oRect.getMaxY(), oRect.getMaxX() - offset,
							oRect.getMaxY())) {
						// System.out.println("bottom");
						guy.cancelJump();
						guy.setY(oRect.getMaxY());
					}
				} else {

					guy.takeDamage(obstacles.get(i).getDamage());
				}
			} else {
				obstacles.get(i).resetNumTimesHit();
			}
			if (obstacles.get(i) instanceof Turret) {
				Turret t = (Turret) (obstacles.get(i));
				for (int j = 0; j < t.bullets().size(); j++) {
					Bullet b = t.bullets().get(j);
					if (b.getBoundingRect().intersects(guy.getBoundingRect())) {
						guy.takeDamage(t.getDamage());
						t.bullets().remove(j);
					}
				}
			}
		}

		guy.setSlow(false);
		if (gRect.intersectsLine(0, ORIGINAL_HEIGHT, ORIGINAL_WIDTH, ORIGINAL_HEIGHT)) {
			guy.setVY(0);
			guy.setY(ORIGINAL_HEIGHT - guy.getHeight());
			guy.cancelJump();
		}
		// System.out.println(guy.getX() + guy.getWidth());
	}

	public boolean translate(int x) {
		if (x < 0 && distanceTranslated <= 0) {
			return false;
		} else if (x > 0 && distanceTranslated >= levelLength) {
			// System.out.println("here");
			return false;
		}
		for (Obstacle o : obstacles) {
			o.setX((float) (o.getX() - x));
			if (o instanceof Turret) {
				for (Bullet b : ((Turret) o).bullets()) {
					b.setX((float) (b.getX() - x));
				}
			}
		}
		distanceTranslated += x;
		return true;
	}
	
	public void placeObjects() {
		
	}
}
