/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */
package GameObjects;

import Interfaces.GamePanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Jack
 */
public class Player extends GameObject{
    
    private int health;
    
    private boolean isAlive; 
    public boolean isOnGround;
    
    public final int PLAYER_HEIGHT = 75;
    public final int PLAYER_WIDTH = 49;
    
    public boolean isMovingRight;
    public boolean isMovingLeft;
    
    public int JUMP_HEIGHT = 72;
    
    public String[] runningImages = new String[12];
    public String[] runningLeftImages = new String[12];
    
    
    // images
    public final String FILE_STARTING_IMAGE = "/Media/idleboy.png";
    public final String FILE_STARTING_IMAGE_LEFT = "/Media/idleboy-left.png";
    
    public final String FILE_JUMP_UP = "/Media/Jump-up.png";
    public final String FILE_JUMP_UP_LEFT = "/Media/Jump-up-left.png";
    public final String FILE_JUMP_FALL = "/Media/Jump-fall.png";
    
    private final String FILE_RUNNING_1 = "/Media/running-frame-1.png";
    private final String FILE_RUNNING_2 = "/Media/running-frame-2.png";
    private final String FILE_RUNNING_3 = "/Media/running-frame-3.png";
    private final String FILE_RUNNING_4 = "/Media/running-frame-4.png";
    private final String FILE_RUNNING_5 = "/Media/running-frame5.png";
    private final String FILE_RUNNING_6 = "/Media/running-frame-6.png";
    private final String FILE_RUNNINGLEFT_1 = "/Media/running-frame-1 - Copy.png";
    private final String FILE_RUNNINGLEFT_2 = "/Media/running-frame-2 - Copy.png";
    private final String FILE_RUNNINGLEFT_3 = "/Media/running-frame-3 - Copy.png";
    private final String FILE_RUNNINGLEFT_4 = "/Media/running-frame-4 - Copy.png";
    private final String FILE_RUNNINGLEFT_5 = "/Media/running-frame5 - Copy.png";
    private final String FILE_RUNNINGLEFT_6 = "/Media/running-frame-6 - Copy.png";
    
    public Player(int x, int y, String name, Dimension dimension){        
        super(x, y, name, dimension);
        super.coordinates = new Coordinates(x, y, PLAYER_HEIGHT, PLAYER_WIDTH);

        spawn();
        setUpRunning();
        // calls setImage method in super class
        setImage(FILE_STARTING_IMAGE);       
    }
    
    public void setPanel(GamePanel panel) {
        super.setPanel(panel);
    }
    
    public void destroy(){
        // Calls destroy method in GameObject
        // Sets is alive to false
        super.destroy();
        isAlive = false;
    }
    public void spawn(){
        // Sets initial variables of the player
        isMovingLeft = false;
        isMovingRight = false;
        isOnGround = true;
        health = 100;
        isAlive = true;
    }

    public boolean isAlive(){
    // returns if player is alive or not
        return isAlive;
    }
    public void update(){
        // updates the coordinates of the player
        setX(coordinates.getX());
        setY(coordinates.getY());
    }
    private void setUpRunning(){
        // sets up the images used when the player is running
        runningImages[0] = FILE_RUNNING_1;
        runningImages[1] = FILE_RUNNING_1;
        runningImages[2] = FILE_RUNNING_2;
        runningImages[3] = FILE_RUNNING_2;
        runningImages[4] = FILE_RUNNING_3;
        runningImages[5] = FILE_RUNNING_3;
        runningImages[6] = FILE_RUNNING_4;
        runningImages[7] = FILE_RUNNING_4;
        runningImages[8] = FILE_RUNNING_5;
        runningImages[9] = FILE_RUNNING_5;
        runningImages[10] = FILE_RUNNING_6;
        runningImages[11] = FILE_RUNNING_6;
        
        runningLeftImages[0] = FILE_RUNNINGLEFT_1;
        runningLeftImages[1] = FILE_RUNNINGLEFT_1;
        runningLeftImages[2] = FILE_RUNNINGLEFT_2;
        runningLeftImages[3] = FILE_RUNNINGLEFT_2;
        runningLeftImages[4] = FILE_RUNNINGLEFT_3;
        runningLeftImages[5] = FILE_RUNNINGLEFT_3;
        runningLeftImages[6] = FILE_RUNNINGLEFT_4;
        runningLeftImages[7] = FILE_RUNNINGLEFT_4;
        runningLeftImages[8] = FILE_RUNNINGLEFT_5;
        runningLeftImages[9] = FILE_RUNNINGLEFT_5;
        runningLeftImages[10] = FILE_RUNNINGLEFT_6;
        runningLeftImages[11] = FILE_RUNNINGLEFT_6;
    
    }
    public boolean onGround(){
        // returns whether or not the player is on the ground
        return isOnGround;
    }
    public void setUpisMoving(boolean movingLeft, boolean movingRight){
        // sets up the moving variables
        this.isMovingLeft = movingLeft;
        this.isMovingRight = movingRight;
                
    }

    public void turnOnHitBox() {
        super.turnOnHitBox();
    }

}
