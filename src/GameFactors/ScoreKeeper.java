/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package GameFactors;

/**
 *
 * @author j.gore
 */
public class ScoreKeeper {
    
    private int coinCount = 0;
    private int healthCount = 3;
    private final int RECOVERY_TIME = 70;
    private int recoveryPeriod = 0;
    private int level = 1;
    
    public ScoreKeeper(){
        
    }
    public void coinCollected(){
        // Increases coin count by one
        coinCount++;

    }
    public void setRecovery(){
        // Sets the recovery period (time player is immune from being attacked)
        recoveryPeriod = RECOVERY_TIME;
    }
    public int getRecovery(){
        // gets how much time is left in the current recovery period
        // returns the time 
        return this.recoveryPeriod;
    }
    public void healthGained(){
        // increase player health by 1
        healthCount++;
    }
    public void healthLost(){
        // decreases player health by 1
        healthCount--;
    }
    public int getCoinCount(){
        // Returns the coin count (# of coins collected)
        return coinCount;
    }
    public int getHealthCount(){
        // returns the amount of health the player has 
        return healthCount;
    }
    public void countDownRecovery(){
        // decrease the amount of recovery left by 1
        recoveryPeriod--;
    }
    public int getLevel(){
        // returns the level the player is on
        return this.level;
    }
    public void levelUp(){
        // in creases the level the player is on
        this.level++;
    }
    public int getLandMonsterCount(){
        // returns how many landmonsters are on the current level.
        return 3 * this.level - this.level;
    }
}
