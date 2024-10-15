
package com.mycompany;

import com.mycompany.databaseControl.ServerDAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    private ServerSocket serverSocket;
    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerDAO serverDAO;

    public Server() {
        serverDAO = new ServerDAO();
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Error creating server socket: " + e.getMessage());
        }
        listen();
        createStreams();
        writeResults();
    }

    private void listen(){
        try {
            System.out.println("\nListening for clients...");
            connection = serverSocket.accept();
            System.out.println("<Connection accepted>");
        } catch (IOException e) {
            System.out.println("Error creating connection: " + e.getMessage());
        }
    }
    
    private void createStreams(){
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            System.out.println("Error creating streams: " + e.getMessage());
        }
    }

    private void writeResults() { 
        ArrayList<Object[]> results = serverDAO.readRecords();
        try{
            output.writeObject(results);
            output.flush();
        }
        catch(IOException e){
            System.out.println("Error writing results: " + e.getMessage());
        }
    }
    
    private String readVehicle() {
        String vehicle = "";
        try {
            vehicle = (String) input.readObject();    
        } catch (IOException e) {
            System.out.println("Error reading vehicle: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return vehicle;
        
        
    }

    public void process(){       
        do {
            String vehicle = readVehicle();
            if (vehicle.equals("")) {
                this.close();
                break;
            }
            // updates server log
            GUIServer.logArea.append(serverDAO.process(vehicle) + "\n");
            writeResults();
        } while (true);
    }
    
    public void close(){
        try {
            serverSocket.close();
            connection.close();
            input.close();
            output.close();
            System.out.println("\n<server offline>");
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
