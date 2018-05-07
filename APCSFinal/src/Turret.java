import processing.core.*;

public class Turret extends Obstacle
{

	public Turret(float x, float y, String imPath) 
	{
		super(x, y, imPath);
	}
	
	public Turret() 
	{
		super("turret.png");
	}

	public int getDamage() 
	{
		return 2;
	}
	
	public void shoot() 
	{
		Blip b = new Blip(  );
	}
	
	public void draw( PApplet drawer ) 
	{
		super.draw(drawer);
		
	}

}
