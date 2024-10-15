
package com.mycompany;

import javax.swing.JOptionPane;

public class Tools {
    Tools(){}
    // ===========================================

    public static void closeApp(){
        int option = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to close the app?",
                "Close app", 
                JOptionPane.YES_NO_OPTION
        );
        
        if (option == 0){
            System.exit(0);
            System.out.println("\n<Program terminated by user>");
        }
    }
    
    public static String capitalize(String word){
        char firstChar = Character.toUpperCase(word.charAt(0));
        String cap = "" + firstChar;
        for (int a=1; a < word.length(); a++){
            cap += word.charAt(a);
        }
        
        return cap; 
    }
}
