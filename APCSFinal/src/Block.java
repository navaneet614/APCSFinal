
public class Block extends Obstacle 
{

	public Block(float x, float y, double width, double height) 
	{
		super(x, y, ImageLoader.block ,width,height);
	}
	
	public int getDamage() 
	{
		return 0;
	}
	
}
