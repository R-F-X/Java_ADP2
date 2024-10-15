package com.mycompany;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Used to send data to a server, and receive data from a server
 */
public class Client {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Client() {
        try {
            socket = new Socket("127.0.0.1", 6666);
        } catch (IOException e) {
            System.out.println("Error creating socket: " + e.getMessage());
        }
        createStreams();
    }

    private void createStreams() {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error creating connection: " + e.getMessage());
        }
    }

    public ArrayList<Object[]> readResults() {
        ArrayList<Object[]> results = new ArrayList<>();
        try {
            results = (ArrayList<Object[]>) input.readObject();
        } catch (IOException e) {
            System.out.println("Error getting results: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return results;
    }

    public void writeVehicle(String vehicle) {
        try {
            output.writeObject(vehicle);
            output.flush();
        } catch (IOException e) {
            System.out.println("Error writing object: " + e.getMessage());
        }
    }

    public void close() {
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Error closing soket: " + e.getMessage());
        }
    }

}
