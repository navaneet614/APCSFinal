package menus;
import java.awt.Color;

import processing.core.PApplet;
import utilities.GameScreen;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * This Menu is displayed before the user plays a level for him
 * or her to select the difficulty of the given level.
 *
 */
public class DifficultyMenu extends Menu {
	public DifficultyMenu() {
		doButtons();
	}
	
	private void doButtons() {
		this.addButton(new Button(325, 200, 150, 50, "Easy", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 260, 150, 50, "Not Easy", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 320, 150, 50, "Really Not Easy", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 380, 150, 50, "Back", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
	}
	
	
	
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Easy")) {
			gameScreen.changeMenuMode("easy");
		} else if(buttonText.equals("Not Easy")) {
			gameScreen.changeMenuMode("medium"); 
		}else if(buttonText.equals("Really Not Easy")) {
			gameScreen.changeMenuMode("hard");
		} else if(buttonText.equals("Back")) {
			gameScreen.changeMenuMode("backtolevelmenu");
		}
	}
	
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.textFont(drawer.createFont("Georgia", 20));
		drawer.textSize(20);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("Difficulty Select", 400, 100);
	}
}
