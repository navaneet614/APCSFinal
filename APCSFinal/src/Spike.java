
public class Spike extends Obstacle
{

	public Spike(float x, float y,double width, double height) {
		super(x, y, ImageLoader.spike ,width,height);
	}
	


	public int getDamage() 
	{
		return 1;
	}

}
