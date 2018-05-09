
public class Glue extends Obstacle
{

	public Glue(float x, float y, double width, double height ) 
	{
		super(x, y, ImageLoader.glue, width, height);
	}
	

	public int getDamage() 
	{
		return 0;
	}
	
}
