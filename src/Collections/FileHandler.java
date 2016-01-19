/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author Jack
 */
public class FileHandler {
    
    public LinkedList nameList = new LinkedList();
    public LinkedList scoreList = new LinkedList();
    
    
    
      // saves data to a file
    public void save(String filename, LinkedList nameList, LinkedList scoreList){
        /**
         * Saves data to a file
         * 
         * Params:
         * 
         * filename - the path of the file to save to
         * nameList - the list of names you want to save
         * scoreList - the listof scores you want
         * to save
         * 
         * Return Type: Void
         */ 
        
        try{
            FileWriter writer = new FileWriter(filename);
            PrintWriter file = new PrintWriter(writer);
            for (int i = 0; i < nameList.size(); i++) {
               file.println(nameList.get(i));
               file.println(scoreList.get(i));
            }
            
            file.close();
            
            
        }
        catch(IOException error){
            System.out.println("File save error");
        }
    }
    // opens data from a file
    public void open(String filename){
        /**
         * Reads saved data from a file.
         * 
         * Params:
         * 
         * filename - path of the file to read from
         * 
         * Return Type: Void
         */ 
        try{
            
            FileReader reader = new FileReader(filename);
            BufferedReader file = new BufferedReader(reader);
            
            for (int i = 0; i < 10; i++) {
                    nameList.addLast(file.readLine());
                    scoreList.addLast(file.readLine());

            }
            file.close();
            
        }
        catch(IOException error){
            System.out.println("File open error " + error.getMessage());
        }
        
        
    }
}
