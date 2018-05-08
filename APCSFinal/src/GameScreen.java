
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScreen extends PApplet {
	public final float ORIGINAL_WIDTH = 800, ORIGINAL_HEIGHT = 600;
	private StartMenu startMenu;
	private Menu currentMenu;
	private Player guy;
	private God god;
	private HashSet<Integer> keys;
	private ArrayList<Obstacle> obstacles;
	private Turret t;

	public GameScreen() {
		startMenu = new StartMenu();
		currentMenu = startMenu;
		guy = new Player(50, 50, 50, 50);
		god = new God(450, 100, 120, 140);
		keys = new HashSet<Integer>();
		obstacles = new ArrayList<Obstacle>();
		t = new Turret(400, 150, "turret.png", 50, 50, 5 * Math.PI / 4);
	}

	public void setup() {
		guy.setup(this);
		god.setup(this);
		t.setup(this);
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
			god.draw(this);
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

		for (Bullet b : t.bullets()) {
			if (gRect.intersects(b.getBoundingRect())) {
				// do something
			}
		}

		for (int i = 0; i < obstacles.size(); i++) {
			oRect = obstacles.get(i).getBoundRect();
			if (gRect.intersects(oRect)) {
				guy.takeDamage(obstacles.get(i).getDamage());

				if (obstacles.get(i) instanceof Glue) {
					guy.setSlow(true);
				}

				if (obstacles.get(i) instanceof Block) {
					int offset = 3;
					if (gRect.intersectsLine(oRect.getX() + offset, oRect.getY(), oRect.getMaxX() - offset,
							oRect.getY())) {
						guy.setVY(0);
						guy.setY(oRect.getY() - guy.getHeight());
						guy.cancelJump();
					}
					if (gRect.intersectsLine(oRect.getX(), oRect.getY() + offset, oRect.getX(),
							oRect.getMaxY() - offset)) {
						guy.cancelJump();
						guy.setX(oRect.getX() - guy.getHeight());
					}
					if (gRect.intersectsLine(oRect.getMaxX(), oRect.getY() + offset, oRect.getMaxX(),
							oRect.getMaxY() - offset)) {
						guy.cancelJump();
						guy.setX(oRect.getMaxX());
					}
					if (gRect.intersectsLine(oRect.getX(), oRect.getMaxY(), oRect.getMaxX(), oRect.getMaxY())) {
						guy.cancelJump();
						guy.setY(oRect.getMaxY());
					}
				}
			}
		}
		guy.setSlow(false);
		if (gRect.intersectsLine(0, ORIGINAL_HEIGHT, ORIGINAL_WIDTH,ORIGINAL_HEIGHT)) {
			guy.setVY(0);
			guy.setY(ORIGINAL_HEIGHT - guy.getHeight());
			guy.cancelJump();
		}
	}

	public void translate(int x) {
		for (Obstacle o : obstacles) {
			o.setX((float) (o.getX() + x));
		}
	}
}
