
public class LightningBolt extends Obstacle
{

	public LightningBolt(float x, float y ) 
	{
		super(x, y, "lightning.png");
	}

	public int getDamage() 
	{
		return 3;
	}
	
}
