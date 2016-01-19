/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package Utility;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author j.gore
 */
public class RandomTools {
    
    public String[] scoreArrayGood;
    public String[] stringArrayGood;
    
    public RandomTools(){
        
    }
    public int randomInteger(int low, int high) {
        // returns a random integer within a given range
        double seed = Math.random();
//        double seed = System.currentTimeMillis();
        double L = (double)low;
        double H = (double)high;
        double random = (H - L + 1.0) * seed + L;
        return (int)random;
    }
    public boolean inRange(int point, int range, int checkPoint){
        // Checks if a value is within a certain range
        int left = point - range;
        int right = point + range;
        if(checkPoint >= left && checkPoint <= right){
            return true;
        }
        return false;
    }
    public String buildScores(LinkedList scoreList, LinkedList nameList){
        String text = "High Scores:\n\n";
        if (scoreList.size() >= 10){
            for (int i = 0; i < 10; i++) {
            text = text + nameList.get(i) + "\t\t" + scoreList.get(i) + "\n";
            }
        }
        else if (scoreList.size() < 10){
            for (int i = 0; i < scoreList.size(); i++) {
            text = text + nameList.get(i) + "\t\t" + scoreList.get(i) + "\n";
            }            
        }
        return text;
    }
    
    public void bubbleSort(String[] array, String[] array2){

        boolean sorted = true; 
        int temp = 0; 
        String tempWord = "";

        for (int i = array.length-1; i >= 0 ; i--) { 
            sorted = true; 

            for (int j = 0; j < i; j++) { 

                if (Integer.parseInt(array[j]) < Integer.parseInt(array[j+1])) { 
                    sorted = false; 
                    temp = Integer.parseInt(array[j]);
                    tempWord = array2[j];
                    array[j] = array[j+1];
                    array2[j] = array2[j+1];
                    array[j+1] = Integer.toString(temp);
                    array2[j+1] = tempWord;
                }
            }
            if (sorted) {
                scoreArrayGood = array;
                stringArrayGood = array2;
                return;
            } 
        }
    }

    public void displayScores(String text) {
        JTextArea textArea = new JTextArea(text);
        JOptionPane.showMessageDialog(null, textArea);
    }

}
