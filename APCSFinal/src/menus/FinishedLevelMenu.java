package menus;

import java.awt.Color;

import processing.core.PApplet;
import utilities.GameScreen;

public class FinishedLevelMenu extends Menu {

	public FinishedLevelMenu() {
		doButtons();
	}
	
	private void doButtons() {
		this.addButton(new Button(325, 200, 150, 50, "Restart", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 260, 150, 50, "Options", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 320, 150, 50, "Main Menu", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 380, 150, 50, "Quit", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Restart")) {
			gameScreen.changeMenuMode("restart");
		} else if(buttonText.equals("Options")) {
			gameScreen.changeMenuMode("options"); 
		}else if(buttonText.equals("Main Menu")) {
			gameScreen.changeMenuMode("main");
		} else if(buttonText.equals("Quit")) {
			System.exit(0);
		}
	}
	
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Georgia", 20));
		drawer.textSize(20);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("Congratulations! You finished the level!", 400, 100);
	}

}
