package com.mycompany.clientside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    // Main Function
    public static void main(String[] args) {

        // Collect inputs from the user
        Scanner input = new Scanner(System.in);

        // Read the matrix
        int nodes;
        System.out.println("Enter the number of nodes in the graph: ");
        nodes = input.nextInt();

        // Check if it's greater than 5
        if(nodes > 5){
            System.out.println("\nThe maximum allowed nodes are 5 so the value is set to 5.");
            nodes = 5;
        }

        // Declare the matrix
        int adjMatrix[][] = new int[nodes][nodes];

        // Read the matrix values
        System.out.println("Enter the elements of the matrix");
        for (int i = 0; i < nodes; i++)
            for (int j = 0; j < nodes; j++)
                adjMatrix[i][j] = input.nextInt();

        // Display the entered matrix
        System.out.println("This is the matrix that was entered\n");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < nodes; i++) {
            s.append((char)(i+ (int)'A') + ": ");
            for (int j : adjMatrix[i]) {
                s.append((j) + " ");
            }
            s.append("\n");
        }
        System.out.print(s.toString());

        // Input path length
        System.out.println("Enter the path length");
        int pathLength = input.nextInt();

        // Input starting and ending nodes (convert alphabets to index values)
        System.out.println("Enter the start node");
        int start = (int)Character.toUpperCase(input.next().charAt(0)) - (int)'A';

        System.out.println("Enter the end node");
        int end = (int)Character.toUpperCase(input.next().charAt(0)) - (int)'A';

        // TCP Connection and communication with the server
        try {
            // Make a new client side connection
            Socket clientSocket = new Socket("localhost", 9000);

            // Make a new inputstream object
            DataInputStream dataInput = new DataInputStream(clientSocket.getInputStream());

            // Create an output stream
            DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());

            // Send data to the server

            // Send Path length
            dataOutput.writeInt(pathLength);
            dataOutput.flush();

            // Send start and end
            dataOutput.writeInt(start);
            dataOutput.flush();
            dataOutput.writeInt(end);
            dataOutput.flush();

            // Send the number of nodes and the matrix
            dataOutput.writeInt(nodes);
            dataOutput.flush();
            for (int i = 0; i < nodes; i++)
                for (int j = 0; j < nodes; j++)
                    dataOutput.writeInt(adjMatrix[i][j]);
                    dataOutput.flush();

            // Read the input from the server
            double area = dataInput.readDouble();
            System.out.println("Area is: " + area);
            clientSocket.close(); // close the connection

        } catch (IOException ex){}

    }
}
