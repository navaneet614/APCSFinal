package utilities;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import menus.DeathMenu;
import menus.DifficultyMenu;
import menus.FinishedLevelMenu;
import menus.GodScreen;
import menus.LevelMenu;
import menus.Menu;
import menus.MultiplayerMenu;
import menus.PauseMenu;
import menus.StartMenu;
import obstacles.Block;
import obstacles.Bullet;
import obstacles.FinishHouse;
import obstacles.Glue;
import obstacles.LandMine;
import obstacles.Obstacle;
import obstacles.Spike;
import obstacles.Turret;
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
	private int lvlNum = 0;
	public static final float ORIGINAL_WIDTH = 800, ORIGINAL_HEIGHT = 600;
	private static int levelLength = 2000 - 50, densityOfBlocks = 2;
	private StartMenu startMenu;
	private Menu currentMenu, inGameMenu;
	private PauseMenu pauseMenu;
	private MultiplayerMenu multiplayerMenu;
	private DeathMenu deathMenu;
	private GodScreen godScreen;
	private LevelMenu levelMenu;
	private DifficultyMenu difficultyMenu;
	private FinishedLevelMenu finishedLevelMenu;
	private Player guy;
	private God god;
	private HashSet<Integer> keys;
	private ArrayList<Obstacle> obstacles;
	private double distanceTranslated;
	private Rectangle mouseP;

	private enum gameModes {
		singleplayer, localMultiplayer, onlineMultiplayer
	}

	private gameModes gameMode;

	public GameScreen() {
		startMenu = new StartMenu();
		pauseMenu = new PauseMenu();
		deathMenu = new DeathMenu();
		multiplayerMenu = new MultiplayerMenu();
		levelMenu = new LevelMenu();
		difficultyMenu = new DifficultyMenu();
		finishedLevelMenu = new FinishedLevelMenu();
		distanceTranslated = 0;
		guy = new Player(50, 450, 50, 50);
		god = new God(450, 100, 120, 140, 15);
		keys = new HashSet<Integer>();
		obstacles = new ArrayList<Obstacle>();
		godScreen = new GodScreen(0, 0, 800, 100, god);
		mouseP = new Rectangle(0, 0, 1, 1);
		lvlNum = 3;
		currentMenu = startMenu;
		inGameMenu = null;
	}

	public void reset(boolean fullClear) {
		translate(-distanceTranslated);
		guy = new Player(50, 450, 50, 50);
		guy.setup(this);
		god = new God(450, 100, 120, 140, 15);
		keys.clear();
		currentMenu = null;
		inGameMenu = null;
		for (Obstacle o : obstacles) {
			if (o instanceof Turret) {
				Turret t = (Turret) o;
				t.bullets().clear();
			} else if (o instanceof LandMine) {
				LandMine tempMine = (LandMine) o;
				tempMine.turnOn();
			}
		}
		if (fullClear) {
			obstacles.clear();
			doLvl();
			godScreen = new GodScreen(0, 0, 800, 100, god);
			currentMenu = levelMenu;
			setupBlocks();
		}
	}

	public void setup() {
		// surface.setResizable(true);
		this.frameRate(60);
		ImageLoader.loadAllImages(this, "resources/");
		guy.setup(this);
		// god.setup(this);
		for (Obstacle o : obstacles) {
			o.setup(this);
		}
		doLvl();
		setupBlocks();
	}

	public void settings() {
		// size(800, 600);
		size(800, 600, FX2D);
		// size(800, 600, P2D);
		// this.getSurface().setResizable(true);
		// fullScreen(P2D);
	}

	private void setupBlocks() {
		for (Obstacle o : obstacles) {
			for (Obstacle ob : obstacles) {
				if (o instanceof Block && Math.abs(ob.getY() - o.getY() - o.getHeight()) < .1
						&& Math.abs(ob.getX() - o.getX()) < .1) {
					Block hu = (Block) ob;
					hu.setStuffOnTop(true);
				}
			}
		}
	}

	public void doLvl() {
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

			// obstacles.add(new Block(1600, 550, 50, 50));
			// obstacles.add(new Block(1650, 550, 50, 50));
			// obstacles.add(new Block(1700, 550, 50, 50));
		} else if (lvlNum == 2) {
			obstacles.add(new Block(200, ORIGINAL_HEIGHT - 100, 50, 50));
			obstacles.add(new Block(350, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(500, ORIGINAL_HEIGHT - 300, 50, 50));
			obstacles.add(new Block(650, ORIGINAL_HEIGHT - 400, 50, 50));
			obstacles.add(new Block(800, ORIGINAL_HEIGHT - 300, 50, 50));
			obstacles.add(new Block(950, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(1100, ORIGINAL_HEIGHT - 100, 50, 50));
			obstacles.add(new Block(1250, ORIGINAL_HEIGHT - 100, 50, 50));
			obstacles.add(new Block(1400, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(1550, ORIGINAL_HEIGHT - 300, 50, 50));
			obstacles.add(new Block(1700, ORIGINAL_HEIGHT - 400, 50, 50));
			obstacles.add(new Block(1850, ORIGINAL_HEIGHT - 300, 50, 50));
			obstacles.add(new Block(2000, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(2150, ORIGINAL_HEIGHT - 100, 50, 50));
			obstacles.add(new Block(2300, ORIGINAL_HEIGHT - 100, 50, 50));
			obstacles.add(new Block(2450, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(2600, ORIGINAL_HEIGHT - 300, 50, 50));
		} else if (lvlNum == 3) {
			obstacles.add(new Block(200, ORIGINAL_HEIGHT - 100, 50, 50));
			obstacles.add(new Block(100, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(200, ORIGINAL_HEIGHT - 300, 50, 50));
			obstacles.add(new Block(100, ORIGINAL_HEIGHT - 400, 50, 50));

			obstacles.add(new Block(300, ORIGINAL_HEIGHT - 500, 50, 50));
			obstacles.add(new Block(350, ORIGINAL_HEIGHT - 500, 50, 50));

			obstacles.add(new Block(550, ORIGINAL_HEIGHT - 450, 50, 50));
			obstacles.add(new Block(600, ORIGINAL_HEIGHT - 450, 50, 50));

			obstacles.add(new Block(800, ORIGINAL_HEIGHT - 400, 50, 50));
			obstacles.add(new Block(850, ORIGINAL_HEIGHT - 400, 50, 50));

			obstacles.add(new Block(1050, ORIGINAL_HEIGHT - 450, 50, 50));
			obstacles.add(new Block(1100, ORIGINAL_HEIGHT - 450, 50, 50));

			obstacles.add(new Block(1300, ORIGINAL_HEIGHT - 500, 50, 50));
			obstacles.add(new Block(1350, ORIGINAL_HEIGHT - 500, 50, 50));

			obstacles.add(new Block(1550, ORIGINAL_HEIGHT - 450, 50, 50));
			obstacles.add(new Block(1600, ORIGINAL_HEIGHT - 450, 50, 50));

			obstacles.add(new Block(1800, ORIGINAL_HEIGHT - 400, 50, 50));
			obstacles.add(new Block(1850, ORIGINAL_HEIGHT - 400, 50, 50));

			obstacles.add(new Block(2050, ORIGINAL_HEIGHT - 450, 50, 50));
			obstacles.add(new Block(2100, ORIGINAL_HEIGHT - 450, 50, 50));

			obstacles.add(new Block(2300, ORIGINAL_HEIGHT - 500, 50, 50));
			obstacles.add(new Block(2350, ORIGINAL_HEIGHT - 500, 50, 50));

			obstacles.add(new Block(350, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(500, ORIGINAL_HEIGHT - 300, 50, 50));

			obstacles.add(new Block(650, ORIGINAL_HEIGHT - 100, 50, 50));
			obstacles.add(new Block(650, ORIGINAL_HEIGHT - 150, 50, 50));
			obstacles.add(new Block(650, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(650, ORIGINAL_HEIGHT - 250, 50, 50));
			obstacles.add(new Block(650, ORIGINAL_HEIGHT - 300, 50, 50));
			obstacles.add(new Block(650, ORIGINAL_HEIGHT - 350, 50, 50));

			obstacles.add(new Block(1250, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(1300, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(1350, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(1400, ORIGINAL_HEIGHT - 200, 50, 50));

			obstacles.add(new Block(2250, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(2300, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(2350, ORIGINAL_HEIGHT - 200, 50, 50));
			obstacles.add(new Block(2400, ORIGINAL_HEIGHT - 200, 50, 50));

		}
		obstacles.add(new FinishHouse(2630, 450, ImageLoader.finish, 100, 100));
		this.setupBlocks();

	}

	public void draw() {
		scale(width / ORIGINAL_WIDTH, height / ORIGINAL_HEIGHT);
		background(Color.WHITE.getRGB());
		// System.out.println("FPS:" + frameRate);
		// System.out.println(this.distanceTranslated);
		// if (guy.hearts() <= 0) {
		// currentMenu = deathMenu;
		// }
		// if (currentMenu != null) {
		// currentMenu.draw(this);
		// } else if (currentMenu == null) {
		//
		// if (inGameMenu != null) {
		// if (!god.canPlace() && inGameMenu instanceof GodScreen) {
		// inGameMenu = null;
		// translate(-distanceTranslated);
		//
		// } else
		// inGameMenu.draw(this);
		// } else if (gameMode.equals(gameModes.singleplayer)) {
		// this.simpleAI();
		// inGameMenu = null;
		// }
		//
		// if (!(inGameMenu instanceof DifficultyMenu) || !(inGameMenu instanceof
		// LevelMenu)) {
		// for (Obstacle o : obstacles) {
		// o.draw(this);
		// }
		// }
		//
		// if (!(inGameMenu instanceof GodScreen)) {
		// hitDetection();
		// guy.update(keys, this);
		// guy.draw(this);
		// }
		// }

		if (currentMenu != null) {
			currentMenu.draw(this);
		} else {
			for (Obstacle o : obstacles) {
				// if(o.getX()>=-50 && o.getX()<=ORIGINAL_WIDTH)
				o.draw(this);
			}
			if (inGameMenu != null) {
				if (gameMode.equals(gameModes.singleplayer)) {
					this.simpleAI();
					inGameMenu = null;
				} else if (!god.canPlace()) {
					inGameMenu = null;
					guy = new Player(50, 450, 50, 50);
					guy.setup(this);
					translate(-distanceTranslated);
				} else {
					inGameMenu.draw(this);
					// guy.draw(this);
				}
			} else {
				// thread("update");
				update();
				guy.draw(this);
			}
		}
	}

	public void update() {
		hitDetection();
		guy.update(keys, this);
		if (guy.hearts() <= 0) {
			currentMenu = deathMenu;
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

		// System.out.println(keyCode == KeyEvent.VK_D);

		if ((keyCode == KeyEvent.VK_P || keyCode == KeyEvent.VK_SPACE) && currentMenu == null) {
			currentMenu = pauseMenu;
		} else if (keyCode == KeyEvent.VK_D && inGameMenu instanceof GodScreen) {
			translate(10);
		} else if (keyCode == KeyEvent.VK_A && inGameMenu instanceof GodScreen) {
			translate(-10);
		}
		keys.add(this.keyCode);
	}

	public void keyReleased() {
		keys.remove(this.keyCode);

	}

	public void simpleAI() {
		// setupBlocks();
		int hu, kadaba;
		while (god.canPlace()) {
			hu = (int) (Math.random() * obstacles.size());
			if (obstacles.get(hu).getX() > this.levelLength - 50 || obstacles.get(hu).getX() < 100) {
				continue;
			}
			if (obstacles.get(hu) instanceof Block) {
				Block vandevoorde = (Block) obstacles.get(hu);
				if (!vandevoorde.getStuffOnTop()) {
					kadaba = (int) (Math.random() * 4);
					if (kadaba == 0) {
						obstacles.add(new Spike((float) obstacles.get(hu).getX(), (float) obstacles.get(hu).getY() - 30,
								50, 30));
					} else if (kadaba == 1) {
						obstacles.add(new Glue((float) obstacles.get(hu).getX(), (float) obstacles.get(hu).getY() - 10,
								50, 20));
					} else if (kadaba == 2) {
						obstacles.add(new Turret((float) obstacles.get(hu).getX(),
								(float) obstacles.get(hu).getY() - 50, 50, 50, Math.PI));
					} else if (kadaba == 3) {
						obstacles.add(new LandMine((float) obstacles.get(hu).getX(),
								(float) obstacles.get(hu).getY() - 25, 25, 25));
					}
					vandevoorde.setStuffOnTop(true);
					god.place();
				}
			}
		}
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
		if (inGameMenu != null) {
			String buttonText = inGameMenu.checkIfButtonsPressed((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGINAL_HEIGHT)));

			if (buttonText == null) {
				return;
			}
			inGameMenu.doButtonAction(buttonText, this);

		}
	}

	public void mouseClicked() {
		mouseP.setLocation((int) (mouseX / (width / ORIGINAL_WIDTH)), (int) (mouseY / (height / ORIGINAL_HEIGHT)));
		// System.out.println( " " + mouseP.getX() + " " + mouseP.getY() +
		// godScreen.getDragging());
		if (inGameMenu instanceof GodScreen)
			addObstacle();
		// allows player to remove by clicking on
		// if(currentMenu == null && inGameMenu == null) {
		// for(int i = 0;i<obstacles.size();i++) {
		// if(obstacles.get(i).getBoundRect().contains(mouseX, mouseY)) {
		// obstacles.remove(i);
		// i--;
		// }
		// }
		// }
	}

	public void addObstacle() {
		if (keyPressed) {
			return;
		}
		if (godScreen.getDragging()) {
			Spike spike = null;
			Glue glue = null;
			Turret turret = null;
			LandMine mine = null;
			boolean canPlaceBlock = true;
			String x = godScreen.getObstacleType();
			for (Obstacle o : obstacles) {
				if (o.getBoundRect().contains(mouseP) && o instanceof Block) {
					Block bee = (Block) o;
					if (!bee.getStuffOnTop()) {
						godScreen.setDragging(false);
						if (x.equals("spike")) {
							spike = new Spike((float) o.getX(), (float) o.getY() - 30, 50, 30);
						} else if (x.equals("glue")) {
							glue = new Glue((float) o.getX(), (float) o.getY() - 10, 50, 10);
						} else if (x.equals("turret")) {
							turret = new Turret((float) o.getX(), (float) o.getY() - 50, 50, 50, Math.PI);
						} else if (x.equals("mine")) {
							mine = new LandMine((float) o.getX(), (float) o.getY() - 25, 25, 25);
						}

						bee.setStuffOnTop(true);
					} else {
						spike = null;
						glue = null;
						turret = null;
						mine = null;
					}
				}
				if (o.getBoundRect().contains(mouseP)) {
					canPlaceBlock = false;
					// System.out.println("AM I ACTUALLY BEING PLACED?");
				}
			}
			if (spike != null) {
				obstacles.add(spike);
				spike = null;
				god.place();
			} else if (glue != null) {
				obstacles.add(glue);
				glue = null;
				god.place();
			} else if (turret != null) {
				obstacles.add(turret);
				turret = null;
				god.place();
			} else if (mine != null) {
				obstacles.add(mine);
				mine = null;
				god.place();
			}
			if (canPlaceBlock && mouseY > 100 && x.equals("block")) {
				obstacles.add(new Block((float) (mouseX - mouseX % (50)), (float) mouseY - mouseY % 50, 50, 50));
				god.place();
			}
		}
	}

	public void mouseMoved() {
		if (currentMenu != null) {
			currentMenu.updateButtons((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGINAL_HEIGHT)));
		}

		if (inGameMenu != null) {
			inGameMenu.updateButtons((int) (mouseX / (width / ORIGINAL_WIDTH)),
					(int) (mouseY / (height / ORIGINAL_HEIGHT)));
		}
	}

	public void changeMenuMode(String menumode) {
		if (menumode.equals("singleplayer")) {
			gameMode = gameModes.singleplayer;
			reset(true);
		} else if (menumode.equals("localmultiplayer")) {
			gameMode = gameModes.localMultiplayer;
			reset(true);
		} else if (menumode.equals("onlinemultiplayer")) {
			gameMode = gameModes.onlineMultiplayer;
			currentMenu = null;
		} else if (menumode.equals("main")) {
			reset(true);
			currentMenu = startMenu;
		} else if (menumode.equals("options")) {

		} else if (menumode.equals("resume")) {
			currentMenu = null;
		} else if (menumode.equals("multiplayer")) {
			currentMenu = multiplayerMenu;
		} else if (menumode.startsWith("level")) {
			this.lvlNum = Integer.parseInt(menumode.charAt(menumode.length() - 1) + "");
			this.doLvl();
			currentMenu = difficultyMenu;
		} else if (menumode.equals("easy")) {
			currentMenu = null;
			god.setObstacleAmount(7);
			inGameMenu = godScreen;
		} else if (menumode.equals("medium")) {
			currentMenu = null;
			god.setObstacleAmount(12);
			inGameMenu = godScreen;
		} else if (menumode.equals("hard")) {
			currentMenu = null;
			god.setObstacleAmount(20);
			inGameMenu = godScreen;
		} else if (menumode.equals("backtolevelmenu")) {
			currentMenu = this.levelMenu;
		} else if (menumode.equals("restart")) {
			reset(false);
		} else if (menumode.equals("quit")) {
			exit();
		} else {
			currentMenu = null;
		}
		this.mouseMoved();
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

				else if (obstacles.get(i) instanceof Block) {
					guy.setSlow(false);

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
				} else if (obstacles.get(i) instanceof FinishHouse) {
					currentMenu = finishedLevelMenu;
				} else {

					guy.takeDamage(obstacles.get(i).getDamage());
					if (obstacles.get(i) instanceof LandMine) {
						LandMine tempMine = (LandMine) obstacles.get(i);
						tempMine.setOff();
					}
				}
			} else {
				obstacles.get(i).resetNumTimesHit();

			}
			if (obstacles.get(i) instanceof Turret) {
				Turret t = (Turret) (obstacles.get(i));
				for (int j = 0; j < t.bullets().size(); j++) {
					Bullet b = t.bullets().get(j);
					if (b.getBoundingRect().intersects(guy.getBoundingRect())) {
						guy.takeDamage(b.getDamage());
						t.bullets().remove(j);
						j--;
					} else {
						for (Obstacle o : obstacles) {
							if (o instanceof Block) {
								Block block = (Block) o;
								if (block.getBoundRect().intersects(b.getBoundingRect()) && t.bullets().size() != 0) {
									t.bullets().remove(j);
									j--;
								}
							}
						}
					}
				}
			}

		}

		if (gRect.intersectsLine(0, ORIGINAL_HEIGHT, ORIGINAL_WIDTH, ORIGINAL_HEIGHT)) {
			guy.setVY(0);
			guy.setY(ORIGINAL_HEIGHT - guy.getHeight());
			guy.cancelJump();
		}
		// System.out.println(guy.getX() + guy.getWidth());
	}

	public boolean translate(double x) {
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
}