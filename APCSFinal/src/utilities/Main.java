package utilities;
import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * 
 * @author William Hu
 * 
 * The Main class is from where the program actually executes.
 * It has a GameScreen that controls the rest of the game actions
 * and conditions.
 *
 */
public class Main {

	public static void main(String[] args) {
//		GameScreen drawing = new GameScreen();
//		PApplet.runSketch(new String[] { "" }, drawing);
//		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
//		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
//		JFrame window = (JFrame) canvas.getFrame();
//
//		window.setName("God's Plan");
//		window.setLocation(100, 100);
//		window.setSize(800, 600);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setResizable(false);
//		// Container c = window.getContentPane();
//		// c.setBackground( Color.BLUE );
//
//		window.setVisible(true);
		
		PApplet.main("utilities.GameScreen");

		
	}
}