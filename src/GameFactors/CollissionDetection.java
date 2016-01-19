/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package GameFactors;


import GameObjects.Coordinates;
import GameObjects.GameObject;
import GameObjects.LandEnemy;
import GameObjects.Player;
import Levels.Level;

/**
 *
 * @author j.gore
 */
public class CollissionDetection {
    
    public CollissionDetection(){
        
    }   
    public void checkOffScreen(GameObject object, int targetX, int targetY){
        /**
         * Checks if the any object is off the JFrame, if it is it corrects it
         * by changing its x coordinate.
         * 
         * Params:
         * 
         * object is any object in the game (player,land enemy, coin)
         * targetX is the x coordinate of the screen
         * targetY is the y coordinate of the screen
         * 
         * Return Type:
         * 
         * Void
         */
        if(object.coordinates.getX() <= 0){
            object.coordinates.setX(1);
        }
        if(object.coordinates.getRight() >= targetY){
            object.coordinates.setX(targetY - (object.coordinates.getWidth()));
        }
    }
    public void checkOffBackGround(GameObject object, Level level){
        /**
         * Checks to see if the object is off of the background image. If it is 
         * it corrects it by changing its x coordinate.
         * 
         * Params:
         * 
         * object is any object in the game (player,land enemy, coin)
         * level is the level object
         * 
         * Return Type:
         * 
         * Void
         */
        
        if(object.coordinates.getX() <= level.coordinates.getX()){
            object.coordinates.setX(level.coordinates.getX() + 1);
        }
        if(object.coordinates.getRight() >= level.coordinates.getRight()){
            object.coordinates.setX(level.coordinates.getRight() - (object.coordinates.getWidth() + 1));
        }
    }  
    public boolean isCollidingHorizontally(Player player, GameObject target){
        /**
         * Checks horizontal collision between the player object and any other
         * object the 'Target'
         * 
         * Params:
         * 
         * player is the Player object.
         * target is any other object (coin, land enemy)
         * 
         * Returns True if it is colliding 
         * Returns False if it is not colliding.
         */
        if(player.isAlive()){
            if(player.coordinates.getLeft() >= target.coordinates.getLeft() && 
                    player.coordinates.getLeft() <= target.coordinates.getRight())
                return true;
            else if(player.coordinates.getRight() >= target.coordinates.getLeft() &&
                    player.coordinates.getRight() <= target.coordinates.getRight())
                return true;
            else if (target.coordinates.getLeft() >= player.coordinates.getLeft() &&
                    target.coordinates.getLeft() <= player.coordinates.getRight())
               return true;
            else if(target.coordinates.getRight() >= player.coordinates.getLeft() &&
                    target.coordinates.getRight() <= player.coordinates.getRight())
            return true;
        }
        return false;
    }
    public boolean isCollidingVertically(Player player, GameObject target){
        /**
         * Checks vertical collision between the player object and any other
         * object the 'Target'
         * 
         * Params:
         * 
         * player is the Player object.
         * target is any other object (coin, land enemy)
         * 
         * Returns True if it is colliding 
         * Returns False if it is not colliding.
         */
        if(player.isAlive()){
            if(player.coordinates.getTop() >= target.coordinates.getTop() && 
                    player.coordinates.getTop() <= target.coordinates.getBottom())
                return true;
            else if(player.coordinates.getBottom() >= target.coordinates.getTop() &&
                    player.coordinates.getBottom() <= target.coordinates.getBottom())
                return true;
            else if (target.coordinates.getTop() >= player.coordinates.getTop() &&
                    target.coordinates.getTop() <= player.coordinates.getBottom())
               return true;
            else if(target.coordinates.getBottom() >= player.coordinates.getTop() &&
                    target.coordinates.getBottom() <= player.coordinates.getBottom())
            return true;
        }
        return false;
    }       
    public boolean isCollidingHorizontallySmallHB(Player player, LandEnemy landEnemy, int amount){
        /**
         * Checks horizontal collision between the player object and any other
         * object the 'Target' with a smaller hit box.
         * 
         * Params:
         * 
         * player is the Player object.
         * landEnemy is any of the landEnemy's
         * amount is how much you want to shrink the hit box by.
         * 
         * Returns True if it is colliding 
         * Returns False if it is not colliding.
         */   
            if(player.isAlive()){
            if(player.coordinates.getIndentLeft(amount) >= landEnemy.coordinates.getIndentLeft(amount) && 
                    player.coordinates.getIndentLeft(amount) <= landEnemy.coordinates.getIndentRight(amount))
                return true;
            else if(player.coordinates.getIndentRight(amount) >= landEnemy.coordinates.getIndentLeft(amount) &&
                    player.coordinates.getIndentRight(amount) <= landEnemy.coordinates.getIndentRight(amount))
                return true;
            else if (landEnemy.coordinates.getIndentLeft(amount) >= player.coordinates.getIndentLeft(amount) &&
                    landEnemy.coordinates.getIndentLeft(amount) <= player.coordinates.getIndentRight(amount))
               return true;
            else if(landEnemy.coordinates.getIndentRight(amount) >= player.coordinates.getIndentLeft(amount) &&
                    landEnemy.coordinates.getIndentRight(amount) <= player.coordinates.getIndentRight(amount))
            return true;
        }
        return false;

    }
    public boolean isCollidingVerticallySmallHB(Player player, LandEnemy landEnemy, int amount){
        /**
         * Checks horizontal collision between the player object and any other
         * object the 'Target' with a smaller hit box
         * 
         * Params:
         * 
         * player is the Player object.
         * landEnemy is any of the landEnemy's
         * amount is how much you want to shrink the hit box by.
         * 
         * Returns True if it is colliding 
         * Returns False if it is not colliding.
         */       
            
            if(player.isAlive()){
            if(player.coordinates.getIndentTop(amount) >= landEnemy.coordinates.getIndentTop(amount) && 
                    player.coordinates.getIndentTop(amount) <= landEnemy.coordinates.getIndentBottom(amount))
                return true;
            else if(player.coordinates.getIndentBottom(amount) >= landEnemy.coordinates.getIndentTop(amount) &&
                    player.coordinates.getIndentBottom(amount) <= landEnemy.coordinates.getIndentBottom(amount))
                return true;
            else if (landEnemy.coordinates.getIndentTop(amount) >= player.coordinates.getIndentTop(amount) &&
                    landEnemy.coordinates.getIndentTop(amount) <= player.coordinates.getIndentBottom(amount))
               return true;
            else if(landEnemy.coordinates.getIndentBottom(amount) >= player.coordinates.getIndentTop(amount) &&
                    landEnemy.coordinates.getIndentBottom(amount) <= player.coordinates.getIndentBottom(amount))
            return true;
        }
        return false;
    }
    
    
    
}
