
package com.mycompany;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

// add loop somewhere
/**
* Used to client an instance of a Client object. 
* Used to send and retrieve messages from a server
**/
public class Client {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Client() {      
        try {
            socket = new Socket("localhost", 6666);
            System.out.println("connected to server");
        } catch (IOException e) {
            System.out.println("Error creating socket: " + e.getMessage());
        }
        createStreams();
    }
    
    private void createStreams(){
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            
        } catch (IOException e) {
            System.out.println("Error creating connection: " + e.getMessage());
        }
    }
    
    public ArrayList<Object> readResults(){
        ArrayList<Object> results = new ArrayList<>();
        try {
            results = (ArrayList<Object>) input.readObject();
        } catch (IOException e) {
            System.out.println("Error getting results: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return results;
    }
    
    private void writeVehicle(String vehicle){
        try {
            output.writeObject(vehicle);
            output.flush();
        } catch (IOException e) {
            System.out.println("Error writing object: " + e.getMessage());
        }
    }
    
    public void close(){
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Error closing soket: " + e.getMessage());
        }
    }
    
    // TESTING
    public static void main(String[] args){
        System.out.println("Client Start");

        Client client = new Client();
        VotingGui gui = new VotingGui();
        client.writeVehicle("vehicle text");
//        ArrayList<Object> r = client.readResults(); // ammars was : String r = client.readResults(); arraylist error cnnot be converted to string
//        System.out.println(r);
        
        client.close();
    }
}
