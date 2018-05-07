
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

	@Override
	public int getDamage() 
	{
		return 2;
	}

}
