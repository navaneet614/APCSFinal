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
		this.addButton(new Button(350, 200, 100, 50, "Start", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(350, 260, 100, 50, "Options", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(350, 320, 100, 50, "Quit", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Start")) {
			gameScreen.changeMenuShowing(null);
		} else if(buttonText.equals("Options")) {
			System.out.println("options");
		} else if(buttonText.equals("Quit")) {
			System.exit(0);
		}
	}
}
