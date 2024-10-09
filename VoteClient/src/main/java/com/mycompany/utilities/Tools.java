
package com.beans.wellnesswave.utilities;

import java.util.Random;
import javax.swing.JOptionPane;

public class Tools {

    public static String createUserID(String name, String email){
        String userID = reverseStr(name) + email;
        return userID;
    }
    public static String createUserID1(String name, String email){
        String userID = reverseStr(name) + email.charAt(0) + email.charAt(1) + email.charAt(2);
        return userID;
    }
    
    public static String reverseStr(String word){
        String reversed = "";
        
        for (int a=word.length()-1; a >= 0; a--){
            reversed += word.charAt(a);
        }
        return reversed; 
    }
    
    public static String generateRandomNumStr(){
        String rNum = "";
        
        Random random = new Random();
        int num1 = random.nextInt(100);
        int num2 = random.nextInt(100);
        int num3 = random.nextInt(100);

        rNum = String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3);
        return rNum;
    }
    
    public static void closeApp(){
        int option = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to close the app?",
                "Close app", 
                JOptionPane.YES_NO_OPTION
        );
        
        System.out.println(option);
        
        if (option == 0){
            System.exit(0);
        }
    }
    
    // from 
    // https://www.studytonight.com/java-examples/check-if-input-is-integer-in-java#:~:text=hasNextInt()%20method%20checks%20whether,otherwise%20it%20will%20return%20false.
    
    public boolean checkIfInt(String input) {
//		String input = "1234";           
        Boolean flag = true;
        for (int a = 0; a < input.length(); a++) {
            if (a == 0 && input.charAt(a) == '-') {
                continue;
            }
            if (!Character.isDigit(input.charAt(a))) {
                flag = false;
            }
        }
//        if (flag) {
//            System.out.println(input + " is valid Integer");
//        }
        return flag;

    }
    
    // ===========================================
    // TEST
//    public static void main(String[] args){
////        Tools tools = new Tools();
//
//        String a = Tools.reverseStr("this");
//        String b = Tools.createUserID("James", "james@gmail.com");
//        
//        System.out.println(a);
//        System.out.println(b);
//
//    }

    // ===========================================

}
