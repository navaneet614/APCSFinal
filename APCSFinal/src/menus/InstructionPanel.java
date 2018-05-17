package menus;

import java.awt.Color;

import processing.core.PApplet;
import utilities.GameScreen;
import utilities.ImageLoader;

public class InstructionPanel extends Menu
{

	public InstructionPanel() 
	{
		super();
		doButtons();
	}
	
	public void doButtonAction(String buttonText, GameScreen gameScreen) 
	{
		if(buttonText.equals("Main Menu")) 
		{
			gameScreen.changeMenuMode("main");
		}
	}
	
	public void doButtons() 
	{
		this.addButton(new Button(325, 380, 150, 50, "Main Menu", Color.BLACK, Color.WHITE, Color.LIGHT_GRAY, Color.BLUE));
	}
	
	public void draw(PApplet drawer) 
	{
		super.draw(drawer);
		drawer.image( ImageLoader.character, 200, 400 );
		drawer.textFont(drawer.createFont("Georgia", 20));
		drawer.textSize(20);
		drawer.textAlign(PApplet.CENTER, PApplet.CENTER);
		drawer.text("In this action-packed adventure, your goal is\n to reach the end of each stage, avoiding God's wrath"
				+ "\n along the way. To move the (memester) character,\n you may use either the up, right, and left arrows,"
				+ "\n or the W, A, and D keys. In case you need to pause your\n marvelous journey, feel free to press the"
				+ "\n 'p' key. Now, if YOU are playing as the one and only\n God, experience the once-in-a-lifetime opportunity"
				+ "\n to wield SO MUCH power as you use the mouse (or touchpad)\n to drag and place obstacles in your very own"
				+ "\n stage!" , 400, 200);
	}

}
