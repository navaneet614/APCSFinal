/**
 * 
 * @author Shaunak Bhandarkar
 *
 * The Block class is a very specific type of Obstacle. However, unlike 
 * other obstacles, it cannot affect the user's health; their purpose is
 * either to block the user from going somewhere, or to be stepped on by
 * the user.
 *
 */
public class Block extends Obstacle 
{
	private boolean stuffOnTop;
	public Block(float x, float y, double width, double height) 
	{
		super(x, y, ImageLoader.block ,width,height);
		stuffOnTop = false;
	}
	
	public void setStuffOnTop(boolean x) {
		stuffOnTop = x;
	}
	
	public boolean getStuffOnTop() {
		return stuffOnTop;
	}
	
	public int getDamage() 
	{
		return 0;
	}
	
}
