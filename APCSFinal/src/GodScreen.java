import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class GodScreen extends Menu {
	private float x, y, width, height;
	private ClickableObject spike, glue, turret;
	private God god;
	private boolean drawSpike, drawGlue, drawTurret, dragging;

	public GodScreen(float x, float y, float width, float height, God god) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		spike = new ClickableObject(x, y, width / 4, height, new Spike(x, y, 50, 50), "Spikes");
		glue = new ClickableObject(x + width / 4, y, width / 4, height, new Glue(x, y, 50, 50), "Glue");
		turret = new ClickableObject(x + width / 2, y, width / 4, height, new Turret(x, y, 50, 50, 180), "Turrets");
		addButton(spike);
		addButton(glue);
		addButton(turret);
		this.god = god;
		dragging = false;
	}

	public void draw(PApplet drawer) {
		drawer.fill(Color.LIGHT_GRAY.getRGB());
		drawer.rect(x, y, width, height);
		spike.draw(drawer);
		glue.draw(drawer);
		turret.draw(drawer);

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
				drawer.image(ImageLoader.spike, drawer.mouseX - 10, drawer.mouseY, 50, 50);
			} else if (drawGlue) {
				drawer.image(ImageLoader.glue, drawer.mouseX - 10, drawer.mouseY, 50, 10);
			} else if (drawTurret) {
				drawer.image(ImageLoader.turret, drawer.mouseX - 10, drawer.mouseY, 50, 50);
			}
		}
	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if (buttonText.equals("Spikes")) {
			drawSpike = true;
			drawGlue = false;
			drawTurret = false;
		} else if (buttonText.equals("Glue")) {
			drawGlue = true;
			drawSpike = false;
			drawTurret = false;
		} else if (buttonText.equals("Turrets")) {
			drawTurret = true;
			drawSpike = false;
			drawGlue = false;
		}
		dragging = true;

	}

	public String getObstacleType() {
		if (drawSpike) {
			return "spike";
		} else if (drawGlue)
			return "glue";
		else
			return "turret";
	}

	public void setDragging(boolean x) {
		dragging = x;
	}

	public boolean getDragging() {
		return dragging;
	}

}
