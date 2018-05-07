import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public abstract class Menu {

	private ArrayList<Button> buttons;

	public Menu() {
		buttons = new ArrayList<Button>();
	}

	public void setup() {

	}

	public void draw(PApplet drawer) {
		drawer.background(Color.WHITE.getRGB());
		for (Button b : buttons) {
			b.draw(drawer);

		}
	}

	public void addButton(Button b) {
		buttons.add(b);
	}

	public String checkIfButtonsPressed(int mouseX, int mouseY) {
		for (Button b : buttons) {
			if (b.mouseOver(mouseX, mouseY)) {
				return b.getText();
			}
		}
		return null;
	}

	public void mouseMoved(int mouseX, int mouseY) {
		for (Button b : buttons) {
			b.update(mouseX, mouseY);
		}
	}

	public abstract void doButtonAction(String buttonText, GameScreen gameScreen);
}
