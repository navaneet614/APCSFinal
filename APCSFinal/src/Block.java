
public class Block extends Obstacle 
{

	public Block(float x, float y, double width, double height) 
	{
		super(x, y, "block.png",width,height);
	}
	
	public int getDamage() 
	{
		return 0;
	}
	
}
