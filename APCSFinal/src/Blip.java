import processing.core.*;

public class Blip 
{
	private PShape s;
	
	public Blip( PShape shape ) 
	{
		s = shape;
	}
	
	public void setBlip( PShape shape ) 
	{
		s = shape;
	}
	
	public PShape getBlip() 
	{
		return s;
	}
}
