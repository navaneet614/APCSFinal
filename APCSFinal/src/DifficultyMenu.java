import java.awt.Color;

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
			gameScreen.changeMenuMode("level1");
		} else if(buttonText.equals("Not Easy")) {
			gameScreen.changeMenuMode("level2"); 
		}else if(buttonText.equals("Really Not Easy")) {
			gameScreen.changeMenuMode("level3");
		} else if(buttonText.equals("Back")) {
			gameScreen.changeMenuMode("main");
		}
	}

}
