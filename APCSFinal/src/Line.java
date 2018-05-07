import processing.core.PApplet;


/*
 * Great use of multiple methods that are called in order to get the intersection point
 * Variable names are very direct and straight to the point
 * 
 * You do not need a bunch of methods in order to get the points from the the seconds line because you are still in this line class
 * ie just use other.x1 instead of creating another whole method for it
 * 
 * When the lines are at 90 degrees to each other but do not touch, the tester returns true even though they never exactly intersect
 *no need to use doubles for the points, can just use ints(there is no half a pixel) and saves the hassle of casting 
 * 
 * 
 */

public class Line 
{
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	public Line( double x1, double y1, double x2, double y2 ) 
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void setPoint1( double x1, double y1 ) 
	{
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public void setPoint2( double x2, double y2 ) 
	{
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public double max( double a, double b ) 
	{
		if (a > b) 
		{
			return a;
		}
		else if (a < b) 
		{
			return b;
		}
		else 
		{
			return a;		// x1 == x2 in this case
		}
	}
	
	public double min( double a, double b ) 
	{
		if (a > b) 
		{
			return b;
		}
		else if (a < b) 
		{
			return a;
		}
		else 
		{
			return a;		// case where x1 == x2
		}
	}
	
	
	public double getX1() 
	{
		return x1;
	}
	
	public double getY1() 
	{
		return y1;
	}
	
	public double getX2() 
	{
		return x2;
	}
	
	public double getY2() 
	{
		return y2;
	}
	
	public double getIntersectionX( Line other ) 
	{
		double x3 = other.getX1();
		double y3 = other.getY1();
		double x4 = other.getX2();
		double y4 = other.getY2();
		
		double intersectionX = ( (x1*y2-x2*y1)*(x3-x4) - (x1-x2)*(x3*y4-x4*y3) ) / ( (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4) );
		return intersectionX;
	}
	
	public double getIntersectionY( Line other ) 
	{
		double x3 = other.getX1();
		double y3 = other.getY1();
		double x4 = other.getX2();
		double y4 = other.getY2();
		
		double intersectionY = ( (x1*y2-x2*y1)*(y3-y4) - (y1-y2)*(x3*y4-x4*y3) ) / ( (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4) );
		return intersectionY;
	}
	
	public void draw( PApplet drawer ) 
	{
		drawer.line( (float)x1, (float)y1, (float)x2, (float)y2 );
	}
	
	public boolean intersects( Line other ) 
	{
		if ( getIntersectionX(other) <= max(x1, x2) && getIntersectionX(other) >= min(x1, x2) && other.getIntersectionX(this) <= max(x1, x2) && other.getIntersectionX(this) >= min(x1, x2) ) 
		{
			if ( getIntersectionX(other) <= max(other.getX1(), other.getX2()) && getIntersectionX(other) >= min(other.getX1(), other.getX2()) && other.getIntersectionX(this) <= max(other.getX1(), other.getX2()) && other.getIntersectionX(this) >= min(other.getX1(), other.getX2()) ) 
			{		
				if ( getIntersectionY(other) <= max(other.getY1(), other.getY2()) && getIntersectionY(other) >= min(other.getY1(), other.getY2()) && other.getIntersectionY(this) <= max(other.getY1(), other.getY2()) && other.getIntersectionY(this) >= min(other.getY1(), other.getY2()) ) 
				{
					if ( getIntersectionY(other) <= max(y1, y2) && getIntersectionY(other) >= min(y1, y2) && other.getIntersectionY(this) <= max(y1, y2) && other.getIntersectionY(this) >= min(y1, y2) ) 
					{
						return true;
					}
					else 
					{
						return false;
					}
				}
				else 
				{
					return false;
				}
			}
			else 
			{
				return false;
			}
		}
		else 
		{
			return false;
		}
	}
	
}
