/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package GameObjects;

import GameFactors.ScoreKeeper;

/**
 *
 * @author j.gore
 */
public class Coin extends GameObject {
    
    private boolean isCollected;
    private final String COIN_1 = "/Media/coin-1.png";
    private final String COIN_2 = "/Media/coin-2.png";
    private final String COIN_3 = "/Media/coin-3.png";
    private final String COIN_4 = "/Media/coin-4.png";
    private final String COIN_5 = "/Media/coin-5.png";
    private final String COIN_6 = "/Media/coin-6.png";
    private final String COIN_7 = "/Media/coin-7.png";
    private final String COIN_8 = "/Media/coin-8.png";
    private final String COIN_9 = "/Media/coin-9.png";
    private final String COIN_10 = "/Media/coin-10.png";
    public String[] coinImages;
    public final int COIN_HEIGHT = 30;
    public final int COIN_WIDTH = 30;
    public String name = "";
    
    
    public Coin(int x, int y){
        super(x, y);
        super.coordinates = new Coordinates(x, y, COIN_HEIGHT, COIN_WIDTH);
        spawn();
        setImage(COIN_1);
    }
    private void spawn(){
        /**
         * Method is called when a coin is created setting it to be un collected
         * and sets up its images.
         * 
         * Return Type: Void
         */  
        isCollected = false;
        coinImages = new String[]{COIN_1, COIN_2, COIN_3, COIN_4, COIN_5, 
            COIN_1, COIN_2, COIN_3, COIN_4, COIN_5};

    }
    public void setName(String text){
        // Sets the name of the coin to the text parameter
        name = text;
    }
    public void collected(ScoreKeeper scoreKeeper){
        // When a coin is collected it will call a method to increase the 
        // collected coin count.
        scoreKeeper.coinCollected();
    }
    
}
