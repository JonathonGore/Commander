/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */
package Interfaces;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author j.gore
 */
public class GameOverPanel extends JPanel{
    
    public GameOverPanel(){
        
    }
    private Image setUpBackgroundIcon(){
        // Sets up the background image
        ImageIcon backgroundIcon = new ImageIcon(this.getClass().getResource("/Media/Game_Over.png"));
        return backgroundIcon.getImage();
    }
    private Image getPlayAgainButton(){
        // returns the image for the play again button
        ImageIcon backgroundIcon = new ImageIcon(this.getClass().getResource("/Media/PlayAgainButton.png"));
        return backgroundIcon.getImage();
    }
    private Image getQuitButton(){
        // returns image for the quit button
        ImageIcon backgroundIcon = new ImageIcon(this.getClass().getResource("/Media/QuitButton.png"));
        return backgroundIcon.getImage();
    }
    @Override
    public void paintComponent(Graphics g){
        // Paingts the different images onto the panel
        Graphics gCopy = g.create();
        gCopy.drawImage(setUpBackgroundIcon(), -60, -50, 700, 450, null);
        gCopy.drawImage(getPlayAgainButton(), 35, 300, 250, 67, null);
        gCopy.drawImage(getQuitButton(), 320, 300, 250, 67, null);
    }
        
  
}
