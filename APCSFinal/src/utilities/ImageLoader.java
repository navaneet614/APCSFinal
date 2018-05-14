package utilities;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Navaneet Kadaba
 * 
 * The ImageLoader class is a static class that
 * provides a convenient and centralized way to 
 * load image (png) files. This helps in executing
 * the program faster, as well as eliminating the
 * issue of lagging.
 *
 */
public class ImageLoader {
	
	public static PImage block, character, glue, health, lightningbolt, platform, player_walk, spike, turret;
	
	public static void loadAllImages(PApplet p, String locationOfResources) {
		block = p.loadImage(locationOfResources + "block.png");
		character = p.loadImage(locationOfResources + "character.png");
		glue = p.loadImage(locationOfResources + "glue.png");
		health = p.loadImage(locationOfResources + "health.png");
		lightningbolt = p.loadImage(locationOfResources + "lightningbolt.png");
		platform = p.loadImage(locationOfResources + "platform.png");
		player_walk = p.loadImage(locationOfResources + "player_walk.gif");
		spike = p.loadImage(locationOfResources + "spike.png");
		turret = p.loadImage(locationOfResources + "turret2.png");
	
		
	}
	

}
