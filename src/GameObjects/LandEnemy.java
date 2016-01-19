/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package GameObjects;

import Interfaces.GamePanel;

/**
 *
 * @author j.gore
 */
public class LandEnemy extends GameObject{
    
    private final String FILE_LAND_MONSTER1 = "/Media/landMonsterframe-1.png";
    private final String FILE_LAND_MONSTER2 = "/Media/landMonsterframe-2.png";
    private final String FILE_LAND_MONSTER3 = "/Media/landMonsterframe-3.png";
    private final String FILE_LAND_MONSTER4 = "/Media/landMonsterframe-4.png";
    private final String FILE_LAND_MONSTER5 = "/Media/landMonsterframe-5.png";
    private final String FILE_LAND_MONSTER6 = "/Media/landMonsterframe-6.png";
    private final String FILE_LAND_MONSTER7 = "/Media/landMonsterframe-7.png";
    private final String FILE_LAND_MONSTER8 = "/Media/landMonsterframe-8.png";
    private final String FILE_LAND_MONSTER9 = "/Media/landMonsterframe-9.png";
    private final String FILE_LAND_MONSTER10 = "/Media/landMonsterframe-10.png";
    
    private final String FILE_LAND_MONSTER_RIGHT1 = "/Media/enemy-1-right1.png";
    private final String FILE_LAND_MONSTER_RIGHT2 = "/Media/enemy-1-right (2).png";
    private final String FILE_LAND_MONSTER_RIGHT3 = "/Media/enemy-1-right (3).png";
    private final String FILE_LAND_MONSTER_RIGHT4= "/Media/enemy-1-right (4).png";
    private final String FILE_LAND_MONSTER_RIGHT5 = "/Media/enemy-1-right (5).png";
    private final String FILE_LAND_MONSTER_RIGHT6 = "/Media/enemy-1-right (6).png";
    private final String FILE_LAND_MONSTER_RIGHT7 = "/Media/enemy-1-right (7).png";
    private final String FILE_LAND_MONSTER_RIGHT8 = "/Media/enemy-1-right (8).png";
    private final String FILE_LAND_MONSTER_RIGHT9 = "/Media/enemy-1-right (9).png";
    private final String FILE_LAND_MONSTER_RIGHT10 = "/Media/enemy-1-right (10).png";
    
    
    private final String FILE_LM_STOMPED1   = "/Media/LM_STOMPED(2).png";
    private final String FILE_LM_STOMPED2   = "/Media/LM_STOMPED(3).png";
    private final String FILE_LM_STOMPED3   = "/Media/LM_STOMPED(4).png";
    private final String FILE_LM_STOMPED4   = "/Media/LM_STOMPED(5).png";
    private final String FILE_LM_STOMPED5   = "/Media/LM_STOMPED(6).png";
    
    public String[] monsterMovingImages;
    public String[] monsterMovingImagesRight;
    public String[] monsterStompedImages;
        
    private boolean isAlive = false;
    private boolean isStomped = false;
    private int stompedCount = 0;
    public boolean detects = false;
    
    public final int HEIGHT_UNSCALED = 240;
    public final int WIDTH_UNSCALED = 395;
    public final int HEIGHT;
    public final int WIDTH;
    private final double scale = 0.18;
    
    
    
    
    public LandEnemy(int x, int y){
        super(x, y);
        HEIGHT = (int)(HEIGHT_UNSCALED * scale);
        WIDTH = (int)(WIDTH_UNSCALED * scale);
        super.coordinates = new Coordinates(x, y, HEIGHT, WIDTH);
        setUpImages();
        spawn();
        setImage(FILE_LAND_MONSTER1);
    }
    
    @Override
    public void setPanel(GamePanel panel) {
        super.setPanel(panel);
    }
    
    private void spawn(){
        // Sets the LandEnemy to being alive
        isAlive = true;
    }
    public void stompedCountIncrease(){
        // Changes the image to be displayed during the stomped animation
        stompedCount++;
    }
    public int getStompedCount(){
        // returns which image is the current stomped count
        return this.getStompedCount();
    }
    public void stomp(){
        // Sets isStomped to be true
        this.isStomped = true;
    }
    public boolean isAlive(){
        // returns if the enemy is alive or not
        return this.isAlive;
    }
    private void setUpImages(){
        /**
         * Sets up the different images used by the land enemy
         */
        monsterMovingImages = new String[]{FILE_LAND_MONSTER1, FILE_LAND_MONSTER2,
            FILE_LAND_MONSTER3, FILE_LAND_MONSTER4, FILE_LAND_MONSTER5, 
            FILE_LAND_MONSTER6, FILE_LAND_MONSTER7, FILE_LAND_MONSTER8, FILE_LAND_MONSTER9,
            FILE_LAND_MONSTER10};
        monsterStompedImages = new String[]{FILE_LM_STOMPED1, FILE_LM_STOMPED2,
            FILE_LM_STOMPED3,FILE_LM_STOMPED4,FILE_LM_STOMPED5,};
        monsterMovingImagesRight = new String[]{FILE_LAND_MONSTER_RIGHT1, FILE_LAND_MONSTER_RIGHT2, 
            FILE_LAND_MONSTER_RIGHT3, FILE_LAND_MONSTER_RIGHT4, FILE_LAND_MONSTER_RIGHT5, 
            FILE_LAND_MONSTER_RIGHT6, FILE_LAND_MONSTER_RIGHT7, FILE_LAND_MONSTER_RIGHT8, 
            FILE_LAND_MONSTER_RIGHT9, FILE_LAND_MONSTER_RIGHT10};
        
    }
    public void kill(){
        // sets isAlive to be false
        this.isAlive = false;
    }
    public boolean isStomped(){
        // returns whether or not the enemy has been stomped
        return this.isStomped;
    }

    public void turnOnHitBox() {
        super.turnOnHitBox();
    }
            
}
