package menus;
import java.awt.Color;
import java.util.ArrayList;

import obstacles.Glue;
import obstacles.Spike;
import obstacles.Turret;
import obstacles.LandMine;
import processing.core.PApplet;
import utilities.ClickableObstacle;
import utilities.GameScreen;
import utilities.God;
import utilities.ImageLoader;

public class GodScreen extends Menu {
	private float x, y, width, height;
	private ClickableObstacle spike, glue, turret, mine;
	private God god;
	private boolean drawSpike, drawGlue, drawTurret, dragging, drawMine;

	public GodScreen(float x, float y, float width, float height, God god) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		spike = new ClickableObstacle(x, y, width / 8, height, new Spike(x, y, 50, 50), "Spikes");
		glue = new ClickableObstacle(x + width / 8, y, width / 8, height, new Glue(x, y, 50, 50), "Glue");
		turret = new ClickableObstacle(x + width / 4, y, width / 8, height, new Turret(x, y, 50, 50, 180), "Turrets");
		mine = new ClickableObstacle(x + 3*width / 8, y, width / 8, height, new LandMine(x, y, 25, 25), "Mine");
		addButton(spike);
		addButton(glue);
		addButton(turret);
		addButton(mine);
		this.god = god;
		dragging = false;
	}

	public void draw(PApplet drawer) {
		drawer.fill(Color.LIGHT_GRAY.getRGB());
		drawer.rect(x, y, width, height);
		spike.draw(drawer);
		glue.draw(drawer);
		turret.draw(drawer);
		mine.draw(drawer);

		drawer.fill(0);
		drawer.textSize(15);
		drawer.line(700, 0, 700, 100);
		drawer.text("Block Limit", 750, 10);
		drawer.textSize(25);
		drawer.text(god.getAmountOfObstacles(), 750, 50);

		drawer.textSize(15);
		drawer.line(600, 0, 600, 100);
		drawer.text("Blocks Placed", 650, 10);
		drawer.textSize(25);
		drawer.text(god.getPlacedObstacles(), 650, 50);

		if (dragging) {
			if (drawSpike) {
				drawer.image(ImageLoader.spike, (drawer.mouseX / (drawer.width / 800f)), (drawer.mouseY / (drawer.height / 600f)), 50, 40);
			} else if (drawGlue) {
				drawer.image(ImageLoader.glue, (drawer.mouseX / (drawer.width / 800f)), (drawer.mouseY / (drawer.height / 600f)), 50, 10);
			} else if (drawTurret) {
				drawer.image(ImageLoader.turret, (drawer.mouseX / (drawer.width / 800f)), (drawer.mouseY / (drawer.height / 600f)), 50, 50);
			}else if(drawMine) {
				drawer.image(ImageLoader.mine, (drawer.mouseX / (drawer.width / 800f)), (drawer.mouseY / (drawer.height / 600f)), 25, 25);
			}
		}
		
		
	}
	

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if (buttonText.equals("Spikes")) {
			drawSpike = true;
			drawGlue = false;
			drawTurret = false;
			drawMine = false;
		} else if (buttonText.equals("Glue")) {
			drawGlue = true;
			drawSpike = false;
			drawTurret = false;
			drawMine = false;
		} else if (buttonText.equals("Turrets")) {
			drawTurret = true;
			drawSpike = false;
			drawGlue = false;
			drawMine = false;
		}else if(buttonText.equals("Mine")) {
			drawSpike = false;
			drawGlue = false;
			drawTurret = false;
			drawMine = true;
		}
		dragging = true;

	}

	public String getObstacleType() {
		if (drawSpike) {
			return "spike";
		} else if (drawGlue)
			return "glue";
		else if(drawMine) {
			return "mine";
		}
			return "turret";
	}

	public void setDragging(boolean x) {
		dragging = x;
	}

	public boolean getDragging() {
		return dragging;
	}

}
