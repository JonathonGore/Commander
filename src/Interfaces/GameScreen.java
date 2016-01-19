/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package Interfaces;

//import Collections.LinkedList;
import Collections.SaveFiles;
import GameFactors.CollissionDetection;
import GameFactors.Detection;
import GameFactors.Gravity;
import GameFactors.ScoreKeeper;
import GameObjects.Coin;
import GameObjects.Heart;
import GameObjects.LandEnemy;
import GameObjects.Player;
import Levels.LevelOne;
import Utility.RandomTools;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 *
 * @author j.gore
 */
public class GameScreen extends JFrame {
    
    private final int FRAME_HEIGHT = 600;
    private final int FRAME_WIDTH = 800;
    private final int LANDENDEMY_CHASE_SPEED = 4;
    private final int LANDENDEMY_WALK_SPEED = 2;
    private final int MOVE_AMOUNT = 6;
//    private final int JUMP_AMOUNT = 4;
//    private final int JUMP_HEIGHT = 72; 
    private final int JUMP_AMOUNT = 5;
    private final int JUMP_HEIGHT = 90;
    private final int FALL_LEFT = 0;
    private final int FALL_RIGHT = 2;
    
    // height at which the player object will be at ground level
    private final int PLAYER_START_HEIGHT = 445;
    private int GROUND_HEIGHT = 0;
    private int coinImageCount = 0;
    private int landEnemyCount = 0;
    private int deadEnemyCount = 0;
    private int landEnemyMoveCount = 0;
    private int imageChange = 0;
    private int fallDirection;
    private int lives = 3;
    
    public  boolean isJumping = false;
    public  boolean isFalling = false;   
    public  boolean isMovingRight = false;
    public  boolean isMovingLeft = false;
//    private boolean enemyCollisionHor = false;
//    private boolean enemyCollisionVer = false;
    private boolean leMoveRight = true;
    
    private Timer timer;
    
    private JLabel coinText;
    
    private LinkedList<Coin> coinList;
    private LinkedList<LandEnemy> landEnemyList;
    private LinkedList<Heart> heartIndicatorList;
    private LinkedList nameList;
    private LinkedList scoreList;
    
    private String[] runningImages;
    private String[] runningLeftImages;
    
    private RandomTools randomTools;
    private CollissionDetection collissionDetection;
    private ScoreKeeper scoreKeeper;
    private Detection detection;
    
    private int right_count;
    private int left_count;
    
    private int defaultXLeft;
    private int defaultXRight;
      
    private Gravity gravity;
    private Player player;
    private LevelOne levelOne;
    private GamePanel gamePanel;
    
    public GameScreen(){       
        super("Commander");
        // checks what level you are on
        levelOne = new LevelOne();
        // sets up player object, jumpAmount, moveAmount etc
        setUpObjects();
        // sets up details of the frame
        setUpScreen();
        connectPanel();
        setUpDefaults();
        // starts the timer
        timer();             
        startActionListeners();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setUpDefaults(){
        /**
         * Sets up all the default variables of the solution
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
         int defaultX = (FRAME_WIDTH / 2) - (player.PLAYER_WIDTH / 2);
         defaultXLeft = defaultX - 3;
         defaultXRight = defaultX + 3;
    }  
    private void setUpScreen() {
        /**
         * Sets up all the components of the JFrame
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        
        
        setVisible(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        
        gamePanel = new GamePanel();
        
        
        gamePanel.addLevel(levelOne);
        gamePanel.addPlayer(player);
        gamePanel.addCoins(coinList);
        gamePanel.addLandEnemy(landEnemyList);
        gamePanel.addIndicatorHearts(heartIndicatorList);

        this.add(gamePanel);
        setUpRunningImages();

    } 
    private void timer(){   
        /**
         * Sets up all the timer object
         * Timer controls and regulates all of the major aspects of the game
         * including collision detection, and updating the coordinates.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                checkIfAlive();
                checkLevelUp();
                jumpOrFall();                           
                checkCollisions();
                changeImages();
                checkCoins();
                checkEnemyDetection();
                walkLandEnemys();
                recalculate();
                gamePanel.repaint();
                imageChange++;
                
            }
        });
        timer.start();
    }
    private void checkCoins(){
        /**
         * Checks to see if the coin list is == 0
         * If it is it adds more coins to the game.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        if(coinList.size() == 0){
            setUpCoins();
            gamePanel.addCoins(coinList);
        }
    }
    private void checkLevelUp(){
        /**
         * Checks to see if you have deleted all monsters.
         * If you have it levels you up and adds more enemys to the game.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        if(landEnemyList.size() == 0){
            scoreKeeper.levelUp();
            scoreKeeper.healthGained();
            setUpHearts();
            gamePanel.addIndicatorHearts(heartIndicatorList);
            setUpLandMonster(scoreKeeper.getLandMonsterCount());
        }
    }   
    private void checkEnemyDetection(){
        /**
         * Loops through all land enemys and checks which ones detect the player
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        for (int i = 0; i < landEnemyList.size(); i++) {
            LandEnemy landEnemy = landEnemyList.get(i);
            landEnemy.detects = detection.doesDetect(landEnemy, player);
        }
    }
    private void walkLandEnemys(){
        /**
         * Walks the land enemy's back and forth if they do not detect.
         * If the landEnemys detect they will chase the player
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        for (int i = 0; i < landEnemyList.size(); i++) {
            LandEnemy landEnemy = landEnemyList.get(i);
            if(!landEnemy.detects){                        
                if(leMoveRight) landEnemy.coordinates.moveRight(LANDENDEMY_WALK_SPEED);
                else landEnemy.coordinates.moveLeft(LANDENDEMY_WALK_SPEED);
            }
            else if(landEnemy.detects){
                if(landEnemy.coordinates.getLeft() >= player.coordinates.getRight()){
                    landEnemy.coordinates.moveLeft(LANDENDEMY_CHASE_SPEED);
                }
                else if (landEnemy.coordinates.getRight() <= player.coordinates.getLeft()){
                    landEnemy.coordinates.moveRight(LANDENDEMY_CHASE_SPEED);
                }
            }
        }
        landEnemyMoveCount++;
        if(landEnemyMoveCount == 60){
            landEnemyMoveCount = 0;
            if(leMoveRight) leMoveRight = false;
            else if(!leMoveRight) leMoveRight = true;
        }
    }
    private void changeImages(){
        /**
         * Changes the image of the coins and landEnemy's for animation.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        if(imageChange == 5){
            coinImageChange();
            landEnemyChange();
            imageChange = 0;
        }
    }
    private void landEnemyChange(){
        /**
         * Changes the landEnemy images for animation
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        LandEnemy landEnemy;
        for (int i = 0; i < landEnemyList.size(); i++) {
            landEnemy = landEnemyList.get(i);
            
            if(landEnemy.isAlive()){
                landEnemy.setImage(landEnemy.monsterMovingImages[landEnemyCount]);
            }
            else if(!landEnemy.isAlive()){
                landEnemy.setImage(landEnemy.monsterStompedImages[deadEnemyCount]);
            }
        }
        landEnemyCount++;
        deadEnemyCount++;
        if(landEnemyCount == 9){
            landEnemyCount = 0;
        }
        if (deadEnemyCount == 5){                
            deadEnemyCount = 0;
            for (int i = 0; i < landEnemyList.size(); i++) {
                landEnemy = landEnemyList.get(i);
                if (!landEnemy.isAlive()){
                    landEnemyList.remove(i);
                }
            }                       
        }
    }
    private void jumpOrFall(){
        /**
         * CHecks to see if the player is jumping or falling and then takes
         * the appropriate action.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        if(isJumping) {
            jumpUp();
         }
        else if(isFalling){
            isFalling = gravity.playerFall(player, player.coordinates.getBottom());
            fallDirection = gravity.getFallDirection();
            if (fallDirection == FALL_LEFT)backGroundMoveRight();
            else if (fallDirection == FALL_RIGHT)backGroundMoveLeft();
        }
    }
    private void recalculate(){
        /**
         * Recalculates all the coordinates and necessary variables
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        
        gamePanel.setCoinCount(scoreKeeper.getCoinCount());
        player.coordinates.recalculate();
        levelOne.coordinates.recalculate(); 
        
        for (int i = 0; i < landEnemyList.size(); i++) {
            landEnemyList.get(i).coordinates.recalculate();
        }
        player.setUpisMoving(isMovingLeft, isMovingRight);
        for (int i = 0; i < coinList.size(); i++) {
            Coin coin = (Coin)coinList.get(i);
            coin.coordinates.recalculate();
        }      
    }
    private void checkIfAlive(){
        /**
         * Checks to see if the player is still alive
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        if (scoreKeeper.getHealthCount() <= 0){
            timer.stop();
            gameOver();
            
        }
    }
    private void gameOver(){
        /**
         * When the player dies it will ask for your name display highscores and
         * then show you the GameOver screen
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        nameList = new LinkedList();
        scoreList = new LinkedList();
        
        String name = JOptionPane.showInputDialog("Please enter your name!");
        int score = scoreKeeper.getCoinCount();
        SaveFiles saveFiles = new SaveFiles(name, score);
        
        nameList = saveFiles.getNameList();
        scoreList = saveFiles.getScoreList();
        
        String text = randomTools.buildScores(scoreList, nameList);
        
        randomTools.displayScores(text);
//        JOptionPane.showMessageDialog(this, text);
        
        GameOverScreen gameOverScreen = new GameOverScreen();
        this.dispose();
    }
    private void coinImageChange(){
        /**
         * Changes coin images for animation
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        for (int i = 0; i < coinList.size(); i++) {
            Coin coin = coinList.get(i);
            coin.setImage(coin.coinImages[coinImageCount]);           
        }
        coinImageCount++;
        if(coinImageCount == 9) {
            coinImageCount = 0;
        }
    }
    private void checkCollisions(){  
        /**
         * Checks collisions for both coins and landEnemys by calling methods
         * in the Collision detection class.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        
        boolean enemyCollisionHor;
        boolean enemyCollisionVer;
        
        collissionDetection.checkOffScreen(player, 0, FRAME_WIDTH);
        LandEnemy landEnemy;      
        for (int i = 0; i < landEnemyList.size(); i++) {
            landEnemy = landEnemyList.get(i);
            collissionDetection.checkOffBackGround(landEnemy, levelOne);
            
            enemyCollisionHor = collissionDetection.isCollidingHorizontally(player, landEnemy);
            enemyCollisionVer = collissionDetection.isCollidingVertically(player, landEnemy); 
            
            if(scoreKeeper.getRecovery() <= 0){    
                if(enemyCollisionHor && enemyCollisionVer && !isFalling && landEnemy.isAlive()){
                    System.out.println("hit: " + i);
                    hitByLandEnemy();
                }
            }
            if(enemyCollisionHor && enemyCollisionVer && isFalling){
                killLandEnemy(i);
                break;
            }
        }
        
        if(scoreKeeper.getRecovery() > 0){
            scoreKeeper.countDownRecovery();
        }
        
        if (coinList.size()> 0){           
            for (int i = 0; i < coinList.size(); i++) {
                Coin coin = coinList.get(i);
                if(collissionDetection.isCollidingHorizontally(player, coin) && collissionDetection.isCollidingVertically(player, coin)){
                    coin.collected(scoreKeeper);
                    coinList.remove(i);
                    break;
                }
            }
        }
    }
    private void killLandEnemy(int i){
        /**
         * Kills the land Enemy in the LinkedList at spot i
         * 
         * Params:
         * 
         * i is the spot in the LinkList of the enemy you killed
         * 
         * Return Type: Void
         */  
        LandEnemy landEnemy = landEnemyList.get(i);
        landEnemy.kill();
    }
    private void hitByLandEnemy(){
        scoreKeeper.healthLost();
        if(heartIndicatorList.size() != 0) heartIndicatorList.removeLast();        
        gamePanel.addIndicatorHearts(heartIndicatorList);
        scoreKeeper.setRecovery();
    }   
    private void startActionListeners(){
        /**
         * Sets up all of the key listeners for the solution
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
//                int key = e.getKeyChar();
//                keyTypedAction(key);    
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyChar();
                keyTypedAction(key);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyChar();
                keyReleasedAction(key);
            }
        });
        
    }
    private void keyReleasedAction(int key){
        /**
         * The action that takes place when a key is released
         * 
         * Params:
         * 
         * key is the key that was released
         * 
         * Return Type: Void
         */  
        if (key == 112) {
            turnOnHitBoxes();
        }
        if (key == 100) {
            goIdle(player.FILE_STARTING_IMAGE);
            isMovingRight = false;
        }
        if (key == 97) {
            goIdle(player.FILE_STARTING_IMAGE_LEFT);
            isMovingLeft = false;
        }
        if (key == 119){
            jump();
        }
        if (key == 113) {
            System.out.println("");
            System.out.println("Land Enemy Count:   " + landEnemyList.size());
            System.out.println("Health Count:   " + scoreKeeper.getHealthCount());
            System.out.println("Coins Collect:  " + scoreKeeper.getCoinCount());
            System.out.println("Level:  " + scoreKeeper.getLevel());
            System.out.println("Coin Count: " + coinList.size());
        }
    }   
    private void keyTypedAction(int key){
        /**
         * Performs appropriate action when a key is typed
         * 
         * Params:
         * 
         * key is the key that was typed
         * 
         * Return Type: Void
         */  
        if(key == 100){
            isMovingRight = true;
            moveRight();
        }
        if (key == 97){
            isMovingLeft = true;
            moveLeft();           
        }

    }
    private void goIdle(String file){
        /**
         * Resets player image to starting or 'idle' image.
         * 
         * Params:
         * 
         * the file name of the image
         * 
         * Return Type: Void
         */  
        left_count = 0;
        right_count = 0;
        player.setImage(file);
    }
    private void setUpRunningImages() {
        /**
         * Sets up the images used when player is running
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        this.runningImages = player.runningImages;
        this.runningLeftImages = player.runningLeftImages;
    }
    private void jump(){
        /**
         * Sets variable to whether or not player is in fact jumping
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        if(!isJumping && !isFalling){
            isJumping = true;
        }      
    }
    private void jumpUp(){
        /**
         * Determines and calls the correct jump method in the coordinates class
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        
        if ((!isMovingLeft && !isMovingRight) || (isMovingLeft && isMovingRight)){
            player.setImage(player.FILE_JUMP_UP);
            player.coordinates.jump();
            player.JUMP_HEIGHT = player.JUMP_HEIGHT - JUMP_AMOUNT;         
        }
        else if(isMovingLeft && !isMovingRight){
            player.setImage(player.FILE_JUMP_UP_LEFT);
            player.coordinates.jumpLeft();
            backGroundMoveRight();
            player.JUMP_HEIGHT = player.JUMP_HEIGHT - JUMP_AMOUNT;
        }
        else if(isMovingRight && !isMovingLeft){
            player.setImage(player.FILE_JUMP_UP);
            player.coordinates.jumpRight();
            backGroundMoveLeft();
            player.JUMP_HEIGHT = player.JUMP_HEIGHT - JUMP_AMOUNT;
        }
        if(player.JUMP_HEIGHT <= 0){
                isJumping = false;
                isFalling = true;
                player.JUMP_HEIGHT = JUMP_HEIGHT;
                
        }  
    }
    private void moveRight(){
        /**
         * Determines whether or not to move player or screen and then performs
         * appropriate action for moving right.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        if(right_count == 11) right_count = 0;
        player.setImage(runningImages[right_count]);
        
        if(playerInRange() && !levelInRangeRight()){
            backGroundMoveLeft();
        }
        else if(player.coordinates.getX() < defaultXLeft && !levelInRangeRight()){
            player.coordinates.moveRight();
        }
        else if(player.coordinates.getX() > defaultXRight && !levelInRangeRight()){
            backGroundMoveLeft();
        }
        else if(levelInRangeRight()){
            player.coordinates.moveRight();
        }
        right_count++;
    }
    private void moveLeft(){
        /**
         * Determines whether or not to move player or screen and then performs
         * appropriate action for moving right.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */  
        if(left_count == 11)  left_count = 0;
        player.setImage(runningLeftImages[left_count]);
        
        if(playerInRange() && !levelInRangeLeft()){
            backGroundMoveRight();
        }
        else if(player.coordinates.getX() > defaultXRight && !levelInRangeLeft()){
            player.coordinates.moveLeft();
        }
        else if(player.coordinates.getX() < defaultXLeft && !levelInRangeLeft()){
            backGroundMoveRight();
        }
        else if(levelInRangeLeft()){
            player.coordinates.moveLeft();
        }

        left_count++;

    }
    private boolean levelInRangeLeft(){
        /**
         * checks to see if in the range of the left side of the background
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Boolean
         * 
         * returns if the level is in range of the edge of screen or not
         */  
        if(levelOne.coordinates.getX() <= 0) 
            if (levelOne.coordinates.getX() >= (0 - 5)){
                return true;
        }
        return false;
    } 
    private boolean levelInRangeRight(){
        /**
         * checks to see if in the range of the right side of the background
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Boolean
         * 
         * returns if the level is in range of the edge of screen or not
         */ 
        if(levelOne.coordinates.getRight() >= FRAME_WIDTH) 
            if (levelOne.coordinates.getRight() <= (FRAME_WIDTH + 5)){
                return true;
        }
        return false;
    }
    private void backGroundMoveLeft(){
        /**
         * moves background and all walls etc left
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */ 
        if(levelOne.coordinates.getRight() > (FRAME_WIDTH + 5)){      
            levelOne.coordinates.moveLeft();
            for (int i = 0; i < coinList.size(); i++) {
                Coin coin = (Coin)coinList.get(i);
                coin.coordinates.moveLeft();           
            }
            for (int i = 0; i < landEnemyList.size(); i++) {
                LandEnemy landEnemy = (LandEnemy)landEnemyList.get(i);
                landEnemy.coordinates.moveLeft();
            }
        }
    }
    private void backGroundMoveRight(){
        /**
         * moves background and all wals etc right
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */ 
        if(levelOne.coordinates.getX() < (0 - 5)){
            levelOne.coordinates.moveRight();
            for (int i = 0; i < coinList.size(); i++) {
                Coin coin = (Coin)coinList.get(i);
                coin.coordinates.moveRight();
            }
            for (int i = 0; i < landEnemyList.size(); i++) {
                LandEnemy landEnemy = (LandEnemy)landEnemyList.get(i);
                landEnemy.coordinates.moveRight();
            }
        }
        
    }
    private void setUpObjects() {
        /**
         * instaniates objects and sets their variables
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */ 
        final int STARTING_LAND_ENEMYS = 3;
        randomTools = new RandomTools();
        scoreKeeper = new ScoreKeeper();
        landEnemyList = new LinkedList<LandEnemy>();
        setUpPlayer();
        setUpLevel();
        GROUND_HEIGHT = PLAYER_START_HEIGHT + player.PLAYER_HEIGHT;
        setUpCoins();
        setUpHearts();
        setUpLandMonster(STARTING_LAND_ENEMYS);
        setUpGravity();
        detection = new Detection();
        collissionDetection = new CollissionDetection();
    }
    private void setUpHearts(){
        /**
         * Sets up the linked list of hearts
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */ 
       
        heartIndicatorList = new LinkedList<Heart>();
        Heart heart;
        for (int i = 0; i < scoreKeeper.getHealthCount(); i++) {
            heart = new Heart(730 - (55 * i), 10, 50, 55);
            heartIndicatorList.add(heart);
        }
    }
    private void setUpLandMonster(int landEnemyAmount){   
        /**
         * sets up linked list of land Enemy and their starting locations
         * 
         * Params:
         * 
         * landEnemyAmount is the amount of landEnemy's to make.
         * 
         * Return Type: Void
         */ 
        LandEnemy landEnemy;     
        // this loop is so monster do not spawn on top of player.
        for (int i = 0; i < landEnemyAmount; i++) {
            do{
            landEnemy = new LandEnemy(randomTools.randomInteger(100, 4700), GROUND_HEIGHT - 40);
            }while(randomTools.inRange(player.coordinates.getX(), 50, landEnemy.coordinates.getX()));
            
            landEnemy.coordinates.setMoveAmount(MOVE_AMOUNT);
            landEnemyList.add(landEnemy);
        }
    }
    private void setUpPlayer(){
        /**
         * Instantiates player object and sets up its variables.
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */ 
        player = new Player(100, PLAYER_START_HEIGHT, "Commander", null);        
        player.coordinates.setMoveAmount(MOVE_AMOUNT);
        player.coordinates.setJumpAmount(JUMP_AMOUNT);
    }
    private void setUpLevel(){
        // Setsup the level move amount
        levelOne.coordinates.setMoveAmount(MOVE_AMOUNT);
    }
    private void setUpGravity(){
        // Passes the ground height to the gravity object
        gravity = new Gravity(GROUND_HEIGHT);        
    }
    private void setUpCoins(){
        /**
         * sets up coins by instaniating and inserting them into a linked list
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */ 
        coinList = new LinkedList<Coin>();       
        Coin coin;
        for (int i = 0; i < (levelOne.BACKGROUND_WIDTH / 100); i++) {
            coin = new Coin(randomTools.randomInteger(80, levelOne.BACKGROUND_WIDTH - 30), GROUND_HEIGHT - 30);
            coin.coordinates.setMoveAmount(MOVE_AMOUNT);
            coin.setName("Coin: " + i);
            coinList.add(coin);
        }
    }
    private boolean playerInRange(){
       // checks to see if the player is in the centre of the screen 
        return player.coordinates.inRange(defaultXLeft, defaultXRight, player.coordinates.getX());
    }
    private void turnOnHitBoxes() {
        player.turnOnHitBox();
        for (int i = 0; i < landEnemyList.size(); i++) {
            landEnemyList.get(i).turnOnHitBox();
        }
    }
    private void connectPanel() {
        player.setPanel(gamePanel);
        for (int i = 0; i < landEnemyList.size(); i++) {
            landEnemyList.get(i).setPanel(gamePanel);
        }
    }
    
}
