/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */
package Interfaces;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author j.gore
 */
public class GameOverScreen extends JFrame{
    
    private final int HEIGHT = 400;
    private final int WIDTH = 600;
    
    private GameOverPanel gameOverPanel;
    
    public GameOverScreen(){
        super("Commander");
        setUndecorated(true);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        
        setUpObjects();
        mouseListener();
    }

    private void setUpObjects() {
        /**
         * Instantiates GameOverPanel
         * adds the panel to the JFrame
         */
        gameOverPanel = new GameOverPanel();
        this.add(gameOverPanel);
        gameOverPanel.repaint();
    }

    private void mouseListener() {
        // Sets up the mouse listener
        MouseListener mouselistener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                click(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }            
        };
        this.addMouseListener(mouselistener);
    }
    
    private void click(MouseEvent e){
        // This method is the event that takes place when the user clicks on a button
        if((e.getX() > 35 && e.getX() < 285) && (e.getY() > 300 && e.getY() < 367)){
            this.dispose();
            GameScreen gameScreen = new GameScreen();
        } 
        else if ((e.getX() > 320 && e.getX() < 570) && (e.getY() > 300 && e.getY() < 367)){
            System.exit(0);
        } 
    }
}
