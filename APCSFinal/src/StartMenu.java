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
		this.addButton(new Button(350, 200, 150, 50, "Singleplayer", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(350, 260, 150, 50, "Multiplayer", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(350, 320, 150, 50, "Options", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(350, 380, 150, 50, "Quit", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Singleplayer")) {
			gameScreen.changeMenuMode("singleplayer");
		} else if(buttonText.equals("Multiplayer")) {
			gameScreen.changeMenuMode("localmultiplayer"); 
		}else if(buttonText.equals("Options")) {
			gameScreen.changeMenuMode("options");
		} else if(buttonText.equals("Quit")) {
			System.exit(0);
		}
	}
}
