/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import Utility.RandomTools;
import java.util.LinkedList;

/**
 *
 * @author Jack
 */
public class SaveFiles {
    
    private String name;
    private int score;
    private final String filename = "D:\\commanderScores.txt";
    private LinkedList<String> nameList;
    private LinkedList<String> scoreList;
    private String[] scoreArray;
    private String[] nameArray;
    private FileHandler fileHandler;
    
    public SaveFiles(String name, int score){
        RandomTools randomTools = new RandomTools();
        scoreList = new LinkedList<String>();
        nameList = new LinkedList<String>();
        fileHandler = new FileHandler();
        this.name = name;
        this.score = score;
        
        getScores();
        deleteNulls(this.nameList);
        deleteNulls(this.scoreList);   
        
        nameList.add(this.name);
        scoreList.add(Integer.toString(this.score));     
        
        nameArray = nameList.toArray(new String[nameList.size()]);
        scoreArray = scoreList.toArray(new String[scoreList.size()]);
              
        randomTools.bubbleSort(scoreArray, nameArray);
        scoreArray = randomTools.scoreArrayGood;
        nameArray = randomTools.stringArrayGood;
        
        nameList.clear();
        scoreList.clear();
        
        for (int i = 0; i < scoreArray.length; i++) {
            
            nameList.addLast(nameArray[i]);
            scoreList.addLast(scoreArray[i]);
            
        }
     
        /**
         * This is working 
         */

        save(this.nameList, this.scoreList);
        
    }
    private void save(LinkedList list1, LinkedList list2){
        /**
         * Saves file by calling method in fileHandler class
         * 
         * Params:
         * 
         * The two LinkedLists you wish to save
         * 
         * Return Type: Void
         */ 
       fileHandler.save(filename, list1, list2);
       
    }
    public LinkedList getScoreList(){
        // Returns the list of scores
        return this.scoreList;
    }
    public LinkedList getNameList(){
        // Returns list of names
        return this.nameList;
    }
    private void getScores(){
        /**
         * Calls method in fileHandler to read data in a saved file
         * 
         * Params:
         * 
         * None
         * 
         * Return Type: Void
         */ 
        fileHandler.open(filename);
        this.nameList = fileHandler.nameList;
        this.scoreList = fileHandler.scoreList;

    }
    private void deleteNulls(LinkedList list) {
        /**
         * Deletes all the nulls in LinkedList so they are not displayed
         * 
         * Params:
         * 
         * The list you want nulls deleted from
         * 
         * Return Type: Void
         */ 
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                list.remove(i);
                if(i != 0){
                    i = i -1;
                    
                }
                
                
            }
        }
    }
}
