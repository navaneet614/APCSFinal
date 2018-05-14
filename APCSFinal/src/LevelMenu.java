import java.awt.Color;

import processing.core.PApplet;

public class LevelMenu extends Menu {
	
	public LevelMenu() {
		doButtons();
	}
	
	private void doButtons() {
		this.addButton(new Button(325, 200, 150, 50, "Level 1", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 260, 150, 50, "Level 2", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 320, 150, 50, "Level 3", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 380, 150, 50, "Back", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
	}
	
	
	
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Level 1")) {
			gameScreen.changeMenuMode("level1");
		} else if(buttonText.equals("Level 2")) {
			gameScreen.changeMenuMode("level2"); 
		}else if(buttonText.equals("Level 3")) {
			gameScreen.changeMenuMode("level3");
		} else if(buttonText.equals("Back")) {
			gameScreen.changeMenuMode("main");
		}
	}
	
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Georgia", 20));
		drawer.textSize(20);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("Level Select", 400, 100);
	}

}
