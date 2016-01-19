/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */
package GameObjects;

import Interfaces.GamePanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Jack
 */
public class Coordinates {
    
    // keeps track of position
    private int x;
    private int y;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int width;
    private int height;
    private int centre;
    private int moveAmount = 0;
    private int jumpAmount = 0;
    private JLabel hitBox;
    protected GamePanel panel;
    private boolean borderOn;
    
    // constructor methods
    public Coordinates(){
        x = 0;
        y = 0;
    }
    public Coordinates(int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        recalculate();
        this.hitBox = new JLabel();
        borderOn = false;
    }
    
    public int getX(){
        // Returns x coordinate
        return this.x;
    }
    public int getY(){
        // Returns Y coordinate
        return this.y;
    }
    public void setX(int x){
        /**
         * Sets the x coordinate to the given x coordinate in the param
         * Then recalculates the coordinates.
         */
        this.x = x;
        recalculate();
    }
    public int getRight(){
        // Returns the right coordinate 
        return this.right;
    }
    public int getLeft(){
        // Returns the left coordinate
        return this.left;
    }
    public int getWidth(){
        // returns the width
        return this.width;
    }
    public int getHeight(){
        // returns the height
        return this.height;
    }
    public int getBottom(){
        // returns the bottom
        return this.bottom;
    }
    public int getTop(){
        // returns the top
        return this.top;
    }
    public int getIndentRight(int amount){
        // returns the right minus the amount in the param
        return this.top - amount;
    }
    public int getIndentLeft(int amount){
        // returns the left plus the amount in the param
        return this.left + amount;
    }
    public int getIndentBottom(int amount){
        // returns the bottom minus the amount in the param
        return this.bottom - amount;
    }
    public int getIndentTop(int amount){
        // returns the top plus the amount in the param
        return this.top + amount;
    }
    public int getCentre(){
        // returns the centre of the object
        return this.centre;
    }
    public int getRightBuffer(int amount){
        //returns the right plus amount in the param
        return this.right + amount;
    }
    public int getLeftBuffer(int amount){
        // returns the left minus the amount in the param
        return this.left - amount;
    }
    public void setMoveAmount(int amount){
        // Sets the amount the object will move to the amount in the param
        moveAmount = amount;
    }
    public void setJumpAmount(int amount){
        // Sets the amount the object will jump to the amount in the param
        jumpAmount = amount;
        redrawHitBox();
    }
    public void moveDown(){
        // Moves the object down the set moveAmount
        y = y + moveAmount;
        redrawHitBox();
    }
    public void moveDown(int amount){
        // Moves the object down the given amount
        y = y + amount;
        redrawHitBox();
    }    
    public void fallRight(){
        // Moves the object down and to the right the set moveAmount/2
        moveDown(moveAmount / 2);
        moveRight(moveAmount / 2);
        redrawHitBox();
    }
    public void fallLeft(){
        // Moves the object down and to the left the set moveAmount/2
        moveDown(moveAmount / 2);
        moveLeft(moveAmount / 2);
        redrawHitBox();
    }    
    public void moveRight(){
        // Moves the object right the set moveAmount
        x += moveAmount;
        redrawHitBox();
    }
    public void moveRight(int amount){
        // Moves the object down the given amount
        x += amount;
        redrawHitBox();
    }
    public void moveLeft(){
        // Moves the object left the set moveAmount
        x = x - moveAmount;
        redrawHitBox();
    }
    public void moveLeft(int amount){
        // Moves the object down the given amount
        x = x - amount;
        redrawHitBox();
    }
    public void jump(){
        // Moves the object up the set moveAmount
        y = y - jumpAmount;
        redrawHitBox();
    }
    public void jump(int amount){
        // Moves the object up the given amount
        y = y - amount;
        redrawHitBox();
    }
    public void jumpLeft(){
        // Moves the object up and to the left the set moveAmount/2
        jump(moveAmount / 2);
        moveLeft(moveAmount / 2);
        redrawHitBox();
    }
    public void jumpRight(){
        // moves the object up and to the right the set moveAmount/2
        jump(moveAmount / 2);
        moveRight(moveAmount / 2);
        redrawHitBox();
    }
    public void recalculate(){
        // Recalculates all of the coordinates of the object
        this.left = x;
        this.right = x + width;
        this.top = y;
        this.bottom = y + height;
        this.centre = left + (width / 2);
    }
    public boolean inRange(int low, int high, int number){
        /**
         * Checks to see if a number is in a given range.
         * 
         * Params:
         * 
         * low is the minimum of the range.
         * high is the maximum of the range.
         * number is the given number to check.
         * 
         * Return Type: Boolean
         * 
         * Returns True if the number is in the range.
         * Returns False if the number is not in the range.
         */  
        if(number >= low){
            if (number <= high) return true;
        }
        return false;
    }

    public void turnOnHitBox() {
        if (borderOn) {
            hitBox.setBorder(null);
            borderOn = false;
        }
        else {
            borderOn = true;
            panel.add(hitBox);
            hitBox.setBorder(BorderFactory.createLineBorder(Color.black,1));
            redrawHitBox();
            
        }
    }

    private void redrawHitBox() {
        if (panel != null) {
            hitBox.setBounds(x,y,width,height);
        }
    }

}
