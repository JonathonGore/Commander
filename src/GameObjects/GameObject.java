package GameObjects;


import Interfaces.GamePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

public class GameObject extends JPanel{
    
    // global variables
    private int x;
    private int y;
    private String name;
    private Dimension dimension;
    public Coordinates coordinates;
    private Image img;
    
    // constructor methods
    public GameObject(int x, int y, String name, Dimension dimension){
        destroy();
        this.x = x;
        this.y = y;
        this.name = name;
        this.dimension = dimension;                
    }
    public GameObject(int x, int y, String name){
        this(x, y, name, null);
    }
    public GameObject(int x, int y, Dimension dimension){
        this(x, y, "", dimension);
    }    
    public GameObject(int x, int y){
        this(x, y, "", null);
    }
        
    public void setPanel(GamePanel panel) {
        coordinates.panel = panel;
    }
    public int getX(){
         // returns x coordinate
        return x;
    } 
    
    public int getY(){
        // returns y coordinate
        return y;
    }
    public void setX(int x){
        // Sets the x coordinate to the x given in the param
        this.x = x;
    }
    public void setY(int y){
        // Sets the y coordinate to the y given in the param
        this.y = y;
    }
   
    public void setImage(String filename){
         // sets image from a filename
        ImageIcon ii = new ImageIcon(this.getClass().getResource(filename));
        this.img = ii.getImage();
    }
    
    public Image getImage(){
        // returns the set image
        return this.img;
    }
    
    public void destroy(){
        // resets variables
        x = 0;
        y = 0;
        name = "";
        dimension = null;
        img = null;
    }

    protected void turnOnHitBox() {
        coordinates.turnOnHitBox();
    }
    
}
