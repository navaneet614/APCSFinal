import java.awt.Color;

import processing.core.PApplet;

public class StartMenu extends Menu {

	public StartMenu() {
		super();
		doButtons();
	}
	
	public void draw(PApplet drawer) {
		super.draw(drawer);
	}
	
	private void doButtons() {
		this.addButton(new Button(100, 100, 100, 50, "Start", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
	}
	
	
}
