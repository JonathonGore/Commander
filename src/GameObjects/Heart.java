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
public class Heart extends GameObject{
    
    private final String HEART_1 = "/Media/heart.png";
    
    
    public Heart(int x, int y, int height, int width){
        super(x, y);
        super.coordinates = new Coordinates(x, y, height, width);
        setImage(HEART_1);
        
    }
}
