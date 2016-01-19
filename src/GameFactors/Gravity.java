/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package GameFactors;

import GameObjects.Player;
import Interfaces.GameScreen;

/**
 *
 * @author j.gore
 */
public class Gravity {
    
    private int groundHeight;
    private boolean isOnPlatform = false;
    private int fallDirection = 1;
    
    public Gravity(int groundHeight){     
        this.groundHeight = groundHeight;
    }
    public boolean playerFall(Player player, int objectHeight){
        /**
         * Controls the falling of the player object. Determines which way the 
         * player will fall based on which way he jumped.
         * 
         * If player is no longer falling it will change the image of the player
         * back to the default or 'idle'image.
         * 
         * Params:
         * 
         * player is the Player object.
         * objectHeight is the height of the ground
         * 
         * Return Type: Boolean
         * 
         * Returns True if player is still falling
         * Returns False if player is not still falling
         */       
        if(!isOnPlatform){
            if(objectHeight < groundHeight){
                if(player.isMovingLeft && !player.isMovingRight) {
                    player.coordinates.fallLeft();
                    fallDirection = 0;
                }
                else if((!player.isMovingLeft && !player.isMovingRight) || (player.isMovingLeft && player.isMovingRight)) {
                    player.coordinates.moveDown();
                    fallDirection = 1;
                }              
                else if (!player.isMovingLeft && player.isMovingRight){
                    player.coordinates.fallRight();
                    fallDirection = 2;
                }
                return true;
            }
            if(player.coordinates.getBottom() >= groundHeight){
                player.setImage(player.FILE_STARTING_IMAGE);
            }
            return false;
        }
        return false;
    }
    public int getFallDirection(){
        /**
         * Returns fall direction of the player
         * 
         * Return Type: int
         * 
         * Returns the fall direction of player (0, 1 or 2)
         */  
        return fallDirection;
    }
    
}
