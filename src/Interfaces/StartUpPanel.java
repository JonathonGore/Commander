/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */
package Interfaces;

import GameObjects.LandEnemy;
import GameObjects.Player;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author j.gore
 */
public class StartUpPanel extends GamePanel{
    
    private ImageIcon buttonIcon; 
    private ImageIcon backgroundIcon;
    private ImageIcon playerIcon;
    
    private Player player;
    private LandEnemy landEnemy;
    
    private boolean isPlayer = false;
    private boolean isEnemy = false;
    
    
    public StartUpPanel(){
        
    }
    public Image setUpBackgroundIcon(){
        /**
         * Sets up the background image of the start screen
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Image
         * 
         * Returns the image on the background
         */  
        backgroundIcon = new ImageIcon(this.getClass().getResource("/Media/background_3.jpg"));
        return backgroundIcon.getImage();
    }
    public Image SetUpImageButton(){
        /**
         * Sets up the image of the start button
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Image
         * 
         * Returns the image of the button
         */  
        buttonIcon = new ImageIcon(this.getClass().getResource("/Media/StartButton.png"));
        return buttonIcon.getImage();
    }
    public void addPlayer(Player player){
        /**
         * Adds player to the panel class
         * 
         * Params:
         * 
         * player is a player object
         * 
         * Return Type: Void
         * 
         */  
        this.player = player;
        isPlayer = true;
    }
    public void addEnemy(LandEnemy landEnemy){
        /**
         * Adds enemy to the panel class
         * 
         * Params:
         * 
         * landEnemy is a landEnemy object
         * 
         * Return Type: Void
         * 
         */          
        this.landEnemy = landEnemy;
        isEnemy = true;
    }
    @Override
    public void paintComponent(Graphics g){
        /**
         * Paints objects onto the panel
         * 
         * Params: None
         * 
         * Return Type: Void
         * 
         */  
        Graphics gCopy = g.create();
        
        
        
        gCopy.drawImage(setUpBackgroundIcon(), -500, 0, 1600, 900, null);
        gCopy.drawImage(SetUpImageButton(), 140, 250, 300, 80, null);
        if(isPlayer){
            gCopy.drawImage(player.getImage(), player.coordinates.getX(), player.coordinates.getY(), 49, 75, null);
        }
        if(isEnemy){
            gCopy.drawImage(landEnemy.getImage(), landEnemy.coordinates.getX(), landEnemy.coordinates.getY(), (int)(395 * 0.18), (int)(240 * 0.18), null);
        }

        

    }
}
