/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside;
// Import java Packages 
import java.io.*; 
import java.net.*;
import java.util.*;

/**
 * Name: Prakhar Rathi 
 * Roll Number: 1810110169
 */
public class Server {

    // function to convert adjacency matrix to adjacency list
    public static ArrayList<ArrayList<Integer>> mat_to_list(int[][] m){

        // Collect number of vertices
        int vertices = m[0].length;

        // Declare an Array List
        ArrayList<ArrayList<Integer>> adjListArray = new ArrayList<ArrayList<Integer>>(vertices);

        // Create a new list for each vertex to store the vertices
        for (int i = 0; i < vertices; i++) {
            adjListArray.add(new ArrayList<Integer>());
        }

        // Store the vertices in the adjacency list
        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] >= 1) {
                    adjListArray.get(i).add(j);
                }
            }
        }

        return adjListArray;

    }

    // Function to count all path lengths and compare with the given path length


    public static void main(String args[])throws Exception{
        try{

            // create a server socket and bind it to the port number 
            ServerSocket serverSocket = new ServerSocket(9000); 
            System.out.println("Server has been started");
            
            while(true){
                
                // Create a new socket to establish a virtual pipe 
                // with the client side (LISTEN)
                Socket socket = serverSocket.accept(); 
                
                // Create a datainput stream object to communicate with the client (Connect)
                DataInputStream input = new DataInputStream(socket.getInputStream()); 

                // Create a dataoutput stream object to communicate with the client (Connect)
                DataOutputStream output = new DataOutputStream(socket.getOutputStream()); 
                
                // Read the data from the client (Receieve)
                int pathLength = input.readInt();
                int start = input.readInt();
                int end = input.readInt();

                // Collect the nodes and the matrix through the data
                int nodes = input.readInt();
                int adjMatrix[][] = new int[nodes][nodes]; // Create the matrix
                for (int i = 0; i < nodes; i++)
                    for (int j = 0; j < nodes; j++)
                        adjMatrix[i][j] = input.readInt();

                // Print test outputs
//                System.out.printf("Path Length = %d\n", pathLength);

                // send data to the client
                // output.writeDouble(area);
            }
        } catch(IOException e){}
    }
}
