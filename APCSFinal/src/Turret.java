
public class Turret extends Obstacle
{

	public Turret(float x, float y, String imPath) {
		super(x, y, imPath);
		// TODO Auto-generated constructor stub
	}
	
	public Turret() 
	{
		super("turret.png");
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
