import processing.core.PImage;

public class Player {
	private double xCoord;
	private double yCoord;
	private int health;
	private double width;
	private double height;
	private PImage character;
	
	public Player(double x, double y) {
		xCoord = x;
		yCoord = y;
		character = loadImage("character.png");
	}
	
}
