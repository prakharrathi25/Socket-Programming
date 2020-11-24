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
    public static ArrayList<Integer>[] mat_to_list(int[][] m){

        // Collect number of vertices
        int vertices = m[0].length;

        // Declare an Array
        ArrayList<Integer>[] adjList = new ArrayList[vertices];
//        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(vertices);

        // Create a new list for each vertex to store the vertices
        for (int i = 0; i < vertices; i++) {
            adjList[i] = (new ArrayList<Integer>());
        }

        // Store the vertices in the adjacency list
        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] >= 1) {
                    adjList[i].add(j);
                }
            }
        }

        return adjList;

    }

    // Function to check if the required path length in the given path lengths
    public static boolean checkPathLength(ArrayList<Integer>[] list, int source, int dest, int vertices, int reqLength)
    {
        // Create an array of visited nodes
        boolean[] hasVisited = new boolean[vertices];
        ArrayList<Integer> pathList = new ArrayList<>();

        // add the source node to the path (subtract 1 from path length)
        pathList.add(source);
        ArrayList<Integer> pathLength = new ArrayList<>();

        // Call recursive DFS function
        pathLengthDFS(list, source, dest, pathLength, hasVisited, pathList);

        // Return whether the path length exists or not
        return pathLength.contains(reqLength);
    }

    // Function to recursively check the path and then add to the list of path lengths
    private static void pathLengthDFS(ArrayList<Integer>[] adjList, Integer s, Integer d, List<Integer> lengths, boolean[] hasVisited, List<Integer> funcPathList)
    {

        if (s.equals(d)) {

            // Add the path length to the path lengths list (subtract 1 to remove source node)
            lengths.add(funcPathList.size()-1);

            // Print the path (OPTIONAL)
            //System.out.println(funcPathList);

            // If we have found a matching node then we can directly return after adding the length to tha path lenght
            return;
        }

        // Mark the current node as visited
        hasVisited[s] = true;

        // Recur to all the adjacent nodes for all the vertices
        for (Integer i : adjList[s]) {
            if (!hasVisited[i]) {
                // store current node
                // in path[]
                funcPathList.add(i);
                pathLengthDFS(adjList, i, d, lengths, hasVisited, funcPathList);

                // remove current node
                // in path[]
                funcPathList.remove(i);
            }
        }

        // Mark the current node
        hasVisited[d] = false;
    }


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

                // Convert adjacency matrix to adjacency list
                ArrayList<Integer>[] adjList = new ArrayList[nodes];
                adjList = mat_to_list(adjMatrix);

                // Check whether path length is present in the array
                boolean pathExists = checkPathLength(adjList, start, end, nodes, pathLength);

                // Send the Y or N to the client
                char response;
                if(pathExists)
                    response = 'Y';
                else
                    response = 'N';

                output.writeChar(response);
            }
        } catch(IOException e){}
    }
}
