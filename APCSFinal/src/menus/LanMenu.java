package menus;

import java.awt.Color;

import frontend.NetworkManagementPanel;
import utilities.GameScreen;

public class LanMenu extends Menu {
	
	public LanMenu() {
		this.addButton(new Button(325, 200, 150, 50, "Open Networking Panel", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
	}

	@Override
	public void doButtonAction(String buttonText, GameScreen gameScreen) {
		if(buttonText.equals("Open Networking Panel")) {
			NetworkManagementPanel nmp = new NetworkManagementPanel("IDK", 5, gameScreen);
		}
	}

}
