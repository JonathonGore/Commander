/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */
package GameFactors;

import GameObjects.GameObject;
import GameObjects.LandEnemy;
import GameObjects.Player;

/**
 *
 * @author j.gore
 */
public class Detection {
    
    private final int DETECTION_DISTANCE = 250;
    
    public Detection(){
        
    }   

    public boolean doesDetect(GameObject object, Player player){
        /**
         * Checks if the player is detected (in a certain range of any landEnemy).
         * 
         * Params:
         * 
         * player is the Player object.
         * object is any of the landEnemy's
         * 
         * Returns True if it is detected 
         * Returns False if it is not detected.
         */       
        
        if ((player.coordinates.getRight() >= object.coordinates.getLeftBuffer(DETECTION_DISTANCE) && 
             player.coordinates.getRight() <= object.coordinates.getRightBuffer(DETECTION_DISTANCE)) ||
             player.coordinates.getLeft()  >= object.coordinates.getLeftBuffer(DETECTION_DISTANCE) && 
             player.coordinates.getLeft()  <= object.coordinates.getRightBuffer(DETECTION_DISTANCE)) {
//            System.out.println("hitting");
            return true;
        }
        else if ((object.coordinates.getLeftBuffer(DETECTION_DISTANCE)  >= player.coordinates.getLeft() && 
                  object.coordinates.getLeftBuffer(DETECTION_DISTANCE)  <= player.coordinates.getRight())  ||
                  object.coordinates.getRightBuffer(DETECTION_DISTANCE) >= player.coordinates.getLeft()  && 
                  object.coordinates.getRightBuffer(DETECTION_DISTANCE) <= player.coordinates.getRight()) {
//            System.out.println("hitting");
            return true;
        }
        else {
//            System.out.println("miss");
            return false;
        }
            
        
        
//        if(player.coordinates.getRight() >= (object.coordinates.getLeftBuffer(DETECTION_DISTANCE))  && 
//                player.coordinates.getLeft() < object.coordinates.getLeft()){
//            System.out.println("true");
//            return true;
//            
//        }    
//        else if(player.coordinates.getLeft() <= object.coordinates.getRightBuffer(DETECTION_DISTANCE)&&
//                player.coordinates.getRight() > object.coordinates.getRight()){
//            System.out.println("true");
//            return true;
//        }
//            
//        
//        
//        System.out.println("false");
//        return false;
    }
}
