/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package Interfaces;


import GameObjects.Coin;
import GameObjects.Heart;
import GameObjects.LandEnemy;
import GameObjects.Player;
import Levels.Level;
import Levels.LevelOne;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author j.gore
 */
    
    
public class GamePanel extends JPanel{
    
    private Player player;
    private LevelOne level;
    private Font font;
    
    private LinkedList<Coin> coinList;
    private LinkedList<LandEnemy> landEnemyList;
    private LinkedList<Heart> indicatorHeartList;
    private boolean areCoins;
    private boolean areLandEnemys;
    private boolean areIndicatorHearts;
    private int coinCount = 0;
    
    public GamePanel(){
        
    }
    private Image setCoinImage(){
        // Sets the coin image
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/Media/coin-1.png"));
        Image img = ii.getImage();
        return img;
    }
    public void addPlayer(Player player){
        // adds a player object to be used on the panel
        this.player = player;
    }
    public void addLevel(LevelOne levelOne){
        // adds a level object to be used on the panel
        this.level = levelOne;
    }
    public void addCoins(LinkedList<Coin> coinList){
        // adds a a linked list of coin objects to be used on the panel
        areCoins = true;
        this.coinList = coinList;
    }
    public void addIndicatorHearts(LinkedList<Heart> heartIndicatorList){
        // adds a a linked list of heart objects to be used on the panel
        areIndicatorHearts = true;
        this.indicatorHeartList = heartIndicatorList;
    }
    public void addLandEnemy(LinkedList<LandEnemy> landEnemyList){
        // adds a a linked list of land enemy objects to be used on the panel
        areLandEnemys = true;
        this.landEnemyList = landEnemyList;
    }
    public void setString(int count){
        // Sets the string to the current coin count; to be displayed
        this.coinCount = count;
    }
    public void setCoinCount(int number){
       // // Sets the coin count to the given coin count; to be displayed
        this.coinCount = number;
    }
    private String getCoinString(){
        // COnverts coin coint to type String
        String text = Integer.toString(coinCount);
        return text;
    }
    @Override
    public void paintComponent(Graphics g){
        // Paints all of the different objects onto the panel
        Graphics gCopy = g.create();
        
        
        gCopy.drawImage(level.getImage(), level.coordinates.getX(), level.coordinates.getY(), level.BACKGROUND_WIDTH, level.BACKGROUND_HEIGHT, null);
        gCopy.drawImage(this.setCoinImage(), 10, 10, 50, 50, null);
        gCopy.setColor(Color.red);
        gCopy.setFont(new Font("default", Font.BOLD, 50));
        gCopy.drawString(getCoinString(), 70, 52);
        
        if(player.isAlive()){
            gCopy.drawImage(player.getImage(), player.coordinates.getX(), player.coordinates.getY(), player.PLAYER_WIDTH, player.PLAYER_HEIGHT, null);
        }
        if(areLandEnemys){
            for (int i = 0; i < this.landEnemyList.size(); i++) {
                gCopy.drawImage(landEnemyList.get(i).getImage(), landEnemyList.get(i).coordinates.getX(), 
                        landEnemyList.get(i).coordinates.getY(), landEnemyList.get(i).WIDTH, landEnemyList.get(i).HEIGHT, null);
            }
        }
        if(areCoins){
            for (int i = 0; i < this.coinList.size(); i++) {
                gCopy.drawImage(coinList.get(i).getImage(), coinList.get(i).coordinates.getX(), 
                        coinList.get(i).coordinates.getY(), coinList.get(i).COIN_WIDTH, coinList.get(i).COIN_HEIGHT, null);
            }
        }
        if(areIndicatorHearts && indicatorHeartList.size() != 0){
            for (int i = 0; i < this.indicatorHeartList.size(); i++) {
                gCopy.drawImage(indicatorHeartList.get(i).getImage(), indicatorHeartList.get(i).coordinates.getX(), 
                        indicatorHeartList.get(i).coordinates.getY(), 
                        indicatorHeartList.get(i).coordinates.getWidth(), indicatorHeartList.get(i).coordinates.getHeight(), null);
            }
        }
        
        
    }
    

}
