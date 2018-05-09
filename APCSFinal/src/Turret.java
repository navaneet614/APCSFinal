import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import processing.core.*;
import javax.swing.Timer;

public class Turret extends Obstacle implements ActionListener
{
	private ArrayList<Bullet> bullets;
	private Timer t;
	double angle;
	int count;
	public Turret(float x, float y, double width, double height, double a) 
	{
		super(x, y, ImageLoader.turret, width, height);
		bullets = new ArrayList<Bullet>();
		t = new Timer( 10, this );
		t.start();
		angle = a;
		count = 0;
	}

	public int getDamage() 
	{
		return 1;
	}
	
	
	public void shoot() 
	{
		bullets.add(new Bullet(getX()+getWidth()/2, getY()+getHeight()/2, angle));
		//angle+=0.7;
	}
	
	public void draw( PApplet drawer ) 
	{
		super.draw(drawer);
		for( Bullet b : bullets ) {
			b.draw(drawer);
		}
		
	}
	
	public ArrayList<Bullet> bullets() 
	{
		return bullets;
	}

	public void actionPerformed(ActionEvent e) 
	{
		count++;
		if ( count % 100 == 0) 
		{
			shoot();
		}
		for ( Bullet b : bullets ) 
		{
			b.shoot();
		}
		
	}

}
