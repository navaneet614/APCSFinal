package menus;

import java.awt.Color;

import frontend.NetworkManagementPanel;
import processing.core.PApplet;
import utilities.GameScreen;

public class LanMenu extends Menu {
	
	private NetworkManagementPanel nmp;
	
	private boolean nmpOpened;
	
	public LanMenu() {
		nmpOpened = false;
		this.addButton(new Button(275, 200, 250, 50, "Open Networking Panel", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
		this.addButton(new Button(325, 260, 150, 50, "Main Menu", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));

	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Open Networking Panel")) {
			if(nmp == null) {
				nmp = new NetworkManagementPanel("Gods Plan", 5, gameScreen);
				this.removeButton(0);
				nmpOpened = true;
			}
		} else if(buttonText.equals("Main Menu")) {
			gameScreen.changeMenuMode("main");

		}
	}
	
	public void draw(PApplet drawer) {
		super.draw(drawer);
		if(this.nmpOpened) {
			drawer.fill(Color.BLACK.getRGB());
			drawer.text("Look at the other window that got opened.", 350, 50);
		}
	}

}
