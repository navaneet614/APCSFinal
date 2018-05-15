package obstacles;

import processing.core.PApplet;
import processing.core.PShape;
import utilities.GameScreen;

public class Office 
{
	private PShape wall;  // The PShape object
	private PShape door;
	private PShape doorHandle;
	private PShape window1;
	private PShape window2;
	private PShape roof;
	private PShape myOffice;
	private float officeX, officeY;
	private float scale;
	
	
	public Office(float x, float y) {
		officeX = x;
		officeY = y;
	}

	public void setup(PApplet drawer) {
	  
	  scale = .5f;

//	  officeX = 900;	// initial values so that office is off the screen
//	  officeY = 100;
	  // Creating the PShape as a square. The
	  // numeric arguments are similar to rect().
	  myOffice = drawer.createShape(drawer.GROUP);		// myHouse PShape consists of all these other PShapes
	  
	  wall = drawer.createShape(drawer.RECT, 75, 100, 300, 200);
	  door = drawer.createShape(drawer.RECT, 200, 200, 50, 100);
	  doorHandle = drawer.createShape(drawer.ELLIPSE, 210, 250, 10, 10);
	  window1 = drawer.createShape(drawer.ELLIPSE, 140, 150, 60, 60);
	  window2 = drawer.createShape(drawer.ELLIPSE, 310, 150, 60, 60);
	  door.setFill(drawer.color(200,69,19));
	  doorHandle.setFill(drawer.color(0, 0, 255));
	  wall.setFill(drawer.color(245, 245, 230));
	  window1.setFill(drawer.color(100,255,100));
	  window2.setFill(drawer.color(100,255,100));
	  wall.setStroke(false);
	  door.setStroke(true);
	  doorHandle.setStroke(true);
	  window1.setStroke(false);
	  window2.setStroke(false);
	  
	  roof = drawer.createShape();
	  roof.beginShape();
	  roof.fill(139, 69, 19);
	  roof.noStroke();
	  roof.vertex(75, 100);
	  roof.vertex(225, 10);
	  roof.vertex(375, 100);
	  roof.endShape(drawer.CLOSE);
	  
	  myOffice.addChild(wall);
	  myOffice.addChild(door);
	  myOffice.addChild(doorHandle);
	  myOffice.addChild(window1);
	  myOffice.addChild(window2);
	  myOffice.addChild(roof);
} 
	
	public void setX( float x ) 
	{
		officeX = x;
	}
	
	public void setY( float y ) 
	{
		officeY = y;
	}
	
	public float getX() 
	{
		return officeX;
	}
	
	public float getY() 
	{
		return officeY;
	}
	
	public void draw(PApplet drawer) 
	{
		drawer.pushMatrix();
		
		drawer.translate(GameScreen.ORIGINAL_WIDTH / 2, GameScreen.ORIGINAL_HEIGHT / 2);
		drawer.scale(scale);
		drawer.translate(-GameScreen.ORIGINAL_WIDTH / 2, -GameScreen.ORIGINAL_HEIGHT / 2);

		drawer.shape(myOffice, officeX, officeY);
		
		drawer.popMatrix();
	}
}
