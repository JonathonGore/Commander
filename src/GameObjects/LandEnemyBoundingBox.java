/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */
package GameObjects;

/**
 *
 * @author j.gore
 */
public class LandEnemyBoundingBox extends GameObject{
    
    public LandEnemyBoundingBox(int x, int y, int width, int height){
        super(x, y);
        super.coordinates = new Coordinates(x, y, HEIGHT, WIDTH);
    }
}
