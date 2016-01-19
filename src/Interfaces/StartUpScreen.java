/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */
package Interfaces;

import GameObjects.LandEnemy;
import GameObjects.Player;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Jack
 */
public class StartUpScreen extends JFrame {
    
    private final int HEIGHT = 400;
    private final int WIDTH = 600;
    
    private int imageChange = 0;
    private int playerImageCount = 0;
    private int landEnemyImageCount = 0;
    private Timer timer;
    
    private boolean isPlayerChasing = true;
    
    private Player player;
    private LandEnemy landEnemy;
    private StartUpPanel startUpPanel;

    
    public StartUpScreen(){
        super("Commander");
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setUpObjects();

    }
    private void setUpObjects(){
        /**
         * sets up the JFrame
         * 
         * Params: None
         * 
         * 
         * Return Type: Void
         * 
         */  
        

        startUpPanel = new StartUpPanel();              
        super.setLayout(new BorderLayout());

        this.add(startUpPanel);
        player = new Player(-10, 130, "Dud", null); 
        startUpPanel.addPlayer(player);
        
        landEnemy = new LandEnemy(-200, 165);
        landEnemy.setImage(landEnemy.monsterMovingImagesRight[0]);
        startUpPanel.addEnemy(landEnemy);
        
        mouseListener();

        timer();
        
    }
    private void mouseListener(){
        /**
         * Adds mouse listeners to JFrame
         * 
         * Params: None
         * 
         * 
         * Return Type: Void
         * 
         */  
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
    private void click(MouseEvent e) {
        /**
         * Event that happens after the user clicks
         * 
         * Params: 
         * 
         * e is the mouse event
         * 
         * Return Type: Void
         * 
         */  
        if((e.getX() > 140 && e.getX() < 440)&& (e.getY() > 300 && e.getY() < 380)){
            GameScreen gameScreen = new GameScreen();
            this.dispose();
        } 
    }
    private void changeImages(){
        /**
         * Changes the images of the objects for animation
         * 
         * Params: None
         * 
         * 
         * Return Type: Void
         * 
         */  
        player.setImage(player.runningImages[playerImageCount]);
        landEnemy.setImage(landEnemy.monsterMovingImagesRight[landEnemyImageCount]);
        landEnemyImageCount++;
        playerImageCount++;
        if(playerImageCount == 11) playerImageCount = 0;
        if(landEnemyImageCount == 10) landEnemyImageCount = 0;
    }
    private void movePlayer(){
        // Moves player right by amount of 5
        player.coordinates.moveRight(5);   
    }
    private void moveEnemy(){
        // Moves enemy right by amount of 5
        landEnemy.coordinates.moveRight(5);
        
    }
    private void checkOffScreen(){
        /**
         * Checks to see if the enemy or player is off the screen and if they are
         * resets their position.
         * 
         * Params: None
         * 
         * 
         * Return Type: Void
         * 
         */  
        if(landEnemy.coordinates.getX() > 610 && player.coordinates.getX() > 610){
            if(isPlayerChasing){
                player.coordinates.setX(-200);
                landEnemy.coordinates.setX(-10);
                isPlayerChasing = false;
            }
            else if(!isPlayerChasing){
                player.coordinates.setX(-10);
                landEnemy.coordinates.setX(-200);
                isPlayerChasing = true;
            }
        }
    }
    private void timer(){
        /**
         * Starts the timer which changes the images of th player and enemy objects
         * 
         * Params: None
         * 
         * 
         * Return Type: Void
         * 
         */  
        timer = new Timer(30, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startUpPanel.repaint();
                if(imageChange == 2){
                    changeImages();
                    imageChange = 0;
                }
                movePlayer();
                moveEnemy();
                checkOffScreen();
                imageChange++;
            }
        });
        timer.start();
        
    }
}
