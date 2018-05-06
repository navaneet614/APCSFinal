import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public abstract class Menu{
	
	private ArrayList<Button> buttons;
	
	public Menu() {
		 buttons = new ArrayList<Button>();
	}


	public void setup() {
		
	}
	
	public void draw(PApplet drawer) {
		drawer.background(Color.WHITE.getRGB());
		for(Button b:buttons) {
			b.draw(drawer, drawer.mouseX, drawer.mouseY);
		}
	}
	
	public void addButton(Button b) {
		buttons.add(b);
	}
}
