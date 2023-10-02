package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    BufferedReader inputClient;
    DataOutputStream output;

    String receivedString;
    String outputString;


    public Socket _wait() {
        try {
            int port = 6666;
            server = new ServerSocket(port);
            System.out.println("Server started on port: " + port);

            // synchronized operation
            client = server.accept();

            // once received the connection, turn off the access to the server
            server.close();

            // get the input stream access
            inputClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR AT START CONNECTION");
            System.exit(1);
        }
        return client;
    }

    public void send() {
        try {
            System.out.println("Waiting for input...");
            receivedString = inputClient.readLine();
            System.out.println("Received String: " + receivedString);

            outputString = receivedString.toUpperCase();

            output.writeBytes(outputString + "\n");
            System.out.println("Sending processed string: " + outputString);
            client.close();
        } catch (Exception e) {
            System.out.println("Failed to send message. Exiting...");
            System.exit(1);
        }
    }
}
