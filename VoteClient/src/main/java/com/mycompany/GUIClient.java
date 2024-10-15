
package com.mycompany;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 Comments
 - table should only have 2 columns
 - need to add buttons; exit, add car, vote, update table (or create an auto-refresh functionality)
 - remove images
 - 
**/

public class GUIClient extends JFrame implements MouseListener{
    private JPanel topPanel,
            panel4,
            centerPanel,
            leftPanel, 
            rightPanel, 
            rightBottomPanel;
    
    private JTable table;
    
    private JLabel label1, label2, label3, label4;
    
    private  Client client;
    private DefaultTableModel tableModel;
    private ArrayList<Object[]> items;
    
    public GUIClient() {
        super("Car-Voting App");
        client = new Client();
//        client.communicate();
        setGui();
        
        // client connection
        
//        client.readResults(); 
//        client.writeVehicle("vehicle text");
    }
    // --------------------------------------

    
    public void setGui(){
        // window 
        setSize(800, 800);   
        //center
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        setVisible(true);
        setResizable(false);
        // ----------------------------------

        
        items = client.readResults();
        System.out.println("Cars \t\t\t Price");
        
       
        
        // combo box
        Object[] options = {"Option 1", "Option 2", "Option 3"};
        
    
             
        JComboBox<Object> comboBox = new JComboBox<>();
        
             //loop
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i)[0] + "\t\t\t" + items.get(i)[1]);
            comboBox.addItem(items.get(i)[0]);
        }
        
        comboBox.setBorder(BorderFactory.createEmptyBorder(55, 55, 55, 55));      
        comboBox.setBackground(Color.black);
        comboBox.setForeground(Color.WHITE);
        // ----------------------------------


        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "CarName", "Votes"
                }
        );
        
           //loop
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i)[0] + "\t\t\t" + items.get(i)[1]);
            comboBox.addItem(items.get(i)[0]);
            tableModel.addRow(items.get(i));
        }
       

        table = new JTable(tableModel);
        
        table.setBackground(new Color(41, 41, 41)); // Background color
        table.setForeground(Color.WHITE); // Text color
        table.setGridColor(Color.WHITE); // Grid color
        table.setSelectionBackground(new Color(100, 100, 100)); // Selection background color
        table.setSelectionForeground(Color.WHITE); // Selection text color
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Font settings
           
        JScrollPane scrollPane = new JScrollPane(table);
        // ----------------------------------

        // panels
        topPanel = new JPanel();
        
//        topPanel.setBackground(new Color(41, 41, 41));
        topPanel.setBackground(Color.black);
        
        topPanel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Add padding
        JLabel headingLabel = new JLabel("ADP Voting System");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headingLabel.setForeground(Color.WHITE);
        topPanel.add(headingLabel);
        
        //center panel will store - leftpanel, rightpanel
        // leftpanel - 2 images
        // right panel- jtable (north), rightBottomPanel-(combo and panel4) (SOUTH)
        
        centerPanel = new JPanel(new GridLayout(1, 2)); // Split into two columns  
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        rightBottomPanel = new JPanel();
       
        
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BorderLayout());
        rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.Y_AXIS));
//        leftPanel.setBackground(new Color(41, 41, 41));
        leftPanel.setBackground(Color.white);
        
      
        panel4 = new JPanel();
//        panel4.setLayout(new GridLayout(1,2));
        panel4.setLayout(new GridLayout(1,3));
      
      
        // ----------------------------------

        // buttons
        // Button 1
        // Vote Button
        label1 = new JLabel("Vote");
        label1.setFont(new Font("Arial", Font.PLAIN, 24)); 
        label1.setHorizontalAlignment(JLabel.CENTER); // Center the text
        label1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        label1.setPreferredSize(new Dimension(30,30)); // Set preferred size
        label1.setForeground(Color.WHITE);
        label1.setBackground(Color.BLACK);
        label1.setOpaque(true); 
        
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Vote Added!");
                String selectedItem = (String) comboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null, "You selected: " + selectedItem);
                client.writeVehicle(selectedItem);
                
                for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                    tableModel.removeRow(i);
                    
                }
                items = client.readResults();
                for (int i = 0; i < items.size(); i++) {
                    tableModel.addRow(items.get(i));
                }
                
            }
        });

        //Button 2
        label2 = new JLabel("Exit");
        label2.setFont(new Font("Arial", Font.PLAIN, 24)); 
        label2.setHorizontalAlignment(JLabel.CENTER); // Center the text
        label2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        label2.setPreferredSize(new Dimension(30, 30)); 
        label2.setForeground(Color.WHITE);
        label2.setBackground(Color.BLACK);
        label2.setOpaque(true); 
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
                System.out.println("\n<program closed by user>");
            }
        });
        
        //Extra button
        label3 = new JLabel("Add car");
        label3.setFont(new Font("Arial", Font.PLAIN, 24));
        label3.setHorizontalAlignment(JLabel.CENTER); // Center the text
        label3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
        label3.setPreferredSize(new Dimension(30, 30)); 
        label3.setForeground(Color.WHITE);
        label3.setBackground(Color.BLACK);
        label3.setOpaque(true); 
        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showMessageDialog(null, "add car button clicked");
                JFrame newWindow = new JFrame("New Window");
                newWindow.setLayout(new BorderLayout());
                JPanel newWindowPanel = new JPanel();             
                newWindowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                
                //text box
                JTextField textField = new JTextField("Enter new car");
                textField.setPreferredSize(new Dimension(300, 40));
                //button
                label4 = new JLabel("Add Car");
                label4.setFont(new Font("Arial", Font.PLAIN, 24));
                label4.setHorizontalAlignment(JLabel.CENTER); // Center the text
                label4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Create a solid white border
                label4.setPreferredSize(new Dimension(100, 30));
                label4.setForeground(Color.WHITE);
                label4.setBackground(Color.BLACK);
                label4.setOpaque(true);
                label4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        String selectedItem = textField.getText();
                        JOptionPane.showMessageDialog(null, "You are adding " + selectedItem);
                        client.writeVehicle(selectedItem);

                        
                        boolean x = true;
                        
                        for (int i = 0; i < items.size(); i++) {
                            if(selectedItem.equals(items.get(i)[0])){
                                
                                x = false;
                            }
                            
                        }
                        
                        if(x){                            
                            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                                tableModel.removeRow(i);

                            }
                            items = client.readResults();
                            for (int i = 0; i < items.size(); i++) {
                                tableModel.addRow(items.get(i));
                            }

                            comboBox.addItem(selectedItem);
                        }
                                            
                        
                    }
                });
                
                label4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        label4.setBackground(new Color(160, 40, 60)); // Change color on hover
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        label4.setBackground(Color.BLACK); // Revert to original color
                    }
                });
                
                newWindow.setBackground(Color.black);
                newWindowPanel.setBackground(Color.black);
                
                newWindowPanel.add(textField);
                newWindowPanel.add(label4);
//                 
                
                newWindow.setSize(400, 300);
                newWindow.setLocationRelativeTo(GUIClient.this);
                
                newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close only the new window
                newWindow.setVisible(true);
                
                newWindow.add(newWindowPanel, BorderLayout.CENTER);
            }
        });
        
         label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label1.setBackground(new Color(160, 40, 60)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label1.setBackground(Color.BLACK); // Revert to original color
            }
        });
         
          label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label2.setBackground(new Color(160, 40, 60)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label2.setBackground(Color.BLACK); // Revert to original color
            }
        });
          
           label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label3.setBackground(new Color(160, 40, 60)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label3.setBackground(Color.BLACK); // Revert to original color
            }
        });
        
        
        // ----------------------------------

                    
       
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/carOne.png")); // must get from resource folder within project.
        ImageIcon imageIconTwo = new ImageIcon(getClass().getResource("/carTwo.png")); // must get from resource folder within project.

          
        ImageIcon resizedImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(500, 320, java.awt.Image.SCALE_SMOOTH));
        ImageIcon resizedImageIconTwo = new ImageIcon(imageIconTwo.getImage().getScaledInstance(500, 320, java.awt.Image.SCALE_SMOOTH));
   
        JLabel imageLabel = new JLabel(resizedImageIcon);
        JLabel imageLabelTwo = new JLabel(resizedImageIconTwo);
        
        imageLabel.setBorder(new EmptyBorder(0, 0, 10, 0)); // SET SPACE BETWEEN IMAGES Y AXIS perfecto
        
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(imageLabel);
        leftPanel.add(imageLabelTwo);
       
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
           
        
        centerPanel.add(leftPanel); // add left panel that contains 2 images
      
        
//      rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(scrollPane, BorderLayout.NORTH);
        
        rightBottomPanel.add(comboBox);//add buttons and combobox to right bottom panel
        
        panel4.add(label1);     
        panel4.add(label3);
        panel4.add(label2);
        rightBottomPanel.add(panel4);
        
        rightPanel.setBackground(Color.black);
        
       
        
   
        rightPanel.add(rightBottomPanel, BorderLayout.SOUTH);
        
        
        centerPanel.add(rightPanel);
    
        
        
        

        setLayout(new BorderLayout()); // set entire frame to borderlayout
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
       
        // repacks everything to fit, fixed non display until maximise
        pack();
    }
    // --------------------------------------

    // EVENTS
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // --------------------------------------
    
    // TESTING
    public static void main(String[] args){
       GUIClient clientGUI = new GUIClient();
       
       
////        ArrayList<Object> r = client.readResults(); // ammars was : String r = client.readResults(); arraylist error cnnot be converted to string
////        System.out.println(r);
//        
//        client.close();
    }
}
