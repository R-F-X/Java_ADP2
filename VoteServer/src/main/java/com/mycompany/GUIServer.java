
package com.mycompany;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*; 
import javax.swing.border.EmptyBorder;


public class GUIServer extends JFrame{
    public static JTextArea logArea; 
    private JPanel mainPanel;
    private JLabel label1; 
    private JPanel panel1; 
    
    GUIServer(){
        super("Server");
        this.setFrame();
        this.initComponents();
        this.setVisible(true);
        
        Server server = new Server();
        server.process();
    }
    // ------------------------------
    
    private void setFrame(){
        this.setSize(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }
    
    private void initComponents(){
        mainPanel = new JPanel();
        
        // textarea
        logArea = new JTextArea("", 4, 6);
        
        // scrollpane
        JScrollPane scroll = new JScrollPane (
                logArea, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        // header
        label1 = new JLabel("Logs");
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        label1.setBorder(new EmptyBorder(15, 20, 15, 20));
        label1.setPreferredSize(new Dimension(100, 30));
        label1.setForeground(Color.BLUE);
        label1.setBackground(Color.ORANGE);
        label1.setOpaque(true);      
                
        // bottom panel
        panel1 = new JPanel();
        panel1.setBackground(Color.ORANGE);

        // adding to the frame
        this.add(mainPanel);
        
        // adding to the main panel
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.add(label1, BorderLayout.NORTH);
        this.mainPanel.add(scroll, BorderLayout.CENTER);
        this.mainPanel.add(panel1, BorderLayout.SOUTH);
    }
    
    // TESTING
    public static void main(String[] args){
        GUIServer server = new GUIServer();
    }
}
