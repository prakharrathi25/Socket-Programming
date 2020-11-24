/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside;

/* Import java Packages */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

/**
 * Name: Prakhar Rathi 
 * Roll Number: 1810110169
 */

public class Server extends JFrame{

    // Global adjmatrix
    int[][] globalAdjMatrix = new int[5][5];

    Point A = new Point(220, 80);
    Point B = new Point(100, 180);
    Point C = new Point(340, 180);
    Point D = new Point(140, 305);
    Point E = new Point(300, 305);

    int draw1[] = new int[25];
    int draw2[] = new int[25];

    Server(int[][] adj_mat) {
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                globalAdjMatrix[i][j] = adj_mat[i][j];

    }

    public void paint(Graphics g) {
        Image img = createImageWithText();
        g.drawImage(img, 20, 20, this);
    }

    private Image createImageWithText() {
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g.setColor(Color.green);
        g.fillOval(200, 50, 55, 55);
        g.fillOval(80, 150, 55, 55);
        g.fillOval(320, 150, 55, 55);
        g.fillOval(120, 275, 55, 55);
        g.fillOval(280, 275, 55, 55);
        g.setColor(Color.black);
        g.drawString("A", A.x, A.y);
        g.drawString("B", B.x, B.y);
        g.drawString("C", C.x, C.y);
        g.drawString("D", D.x, D.y);
        g.drawString("E", E.x, E.y);
        for (int i = 0; i < 25; i++) {
            draw1[i] = -1;
            draw2[i] = -1;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (globalAdjMatrix[i][j] > 0) {
                    int n = 0;
                    int m = 0;
                    if (draw1[0] == -1)
                        draw1[0] = i;
                    else {
                        while (draw1[n] != -1)
                            n++;
                        draw1[n] = i;
                    }
                    if (draw2[0] == -1)
                        draw2[0] = j;
                    else {
                        while (draw2[m] != -1)
                            m++;
                        draw2[m] = j;
                    }
                }
            }
        }

        for(int i=0; i<25;i++){
            if(draw1[i]>-1 && draw2[i]>-1){
                int a1=0,b1=0,a2=0,b2=0;
                switch(draw1[i]){
                    case 0:
                        a1 = A.x;
                        b1 = A.y;
                        break;
                    case 1:
                        a1 = B.x;
                        b1 = B.y;
                        break;
                    case 2:
                        a1 = C.x;
                        b1 = C.y;
                        break;
                    case 3:
                        a1 = D.x;
                        b1 = D.y;
                        break;
                    case 4:
                        a1 = E.x;
                        b1 = E.y;
                        break;
                }
                switch(draw2[i]){
                    case 0:
                        a2 = A.x;
                        b2 = A.y;
                        break;
                    case 1:
                        a2 = B.x;
                        b2 = B.y;
                        break;
                    case 2:
                        a2 = C.x;
                        b2 = C.y;
                        break;
                    case 3:
                        a2 = D.x;
                        b2 = D.y;
                        break;
                    case 4:
                        a2 = E.x;
                        b2 = E.y;
                        break;
                }
                drawArrow(g, a1, b1, a2, b2);
            }
        }
        return bufferedImage;
    }

    public void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        int midx = (x1 + x2)/2;
        int midy = (y1 + y2)/2;
        x1 = (midx + x1)/2;
        x2 = (midx + x2)/2;
        y1 = (midy + y1)/2;
        y2 = (midy + y2)/2;
        g.drawLine(x1, y1, x2, y2);
        drawTip(g, x1, y1,x2, y2);

    }

    private void drawTip(Graphics g, int x1, int y1, int x2, int y2) {
        int x,y;
        double rads = 0.523599;
        double hyp_multiplier = 10;
        int diff_y = y2 - y1;
        int diff_x = x2 - x1;
        double t = Math.atan2(diff_y, diff_x);
        double r = rads + t;
        for (int j = 0; j < 2; j++) {
            x = (int)(x2 - hyp_multiplier * Math.cos(r));
            y = (int)(y2 - hyp_multiplier * Math.sin(r));
            g.drawLine(x2, y2, x, y);
            r = t - rads;
        }
    }

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

    /* Function to check if the required path length in the given path lengths */
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

    /* Function to recursively check the path and then add to the list of path lengths */
    private static void pathLengthDFS(ArrayList<Integer>[] adjList, Integer s, Integer d, List<Integer> lengths, boolean[] hasVisited, List<Integer> funcPathList)
    {

        if (s.equals(d)) {

            // Add the path length to the path lengths list (subtract 1 to remove source node)
            lengths.add(funcPathList.size()-1);

            // If we have found a matching node then we can directly return after adding the length to tha path lenght
            return;
        }

        // Mark the current node as visited
        hasVisited[s] = true;

        // Recur to all the adjacent nodes for all the vertices
        for (Integer i : adjList[s]) {
            if (!hasVisited[i]) {
                // store current node in the path to begin traversal
                funcPathList.add(i);
                pathLengthDFS(adjList, i, d, lengths, hasVisited, funcPathList);

                // remove current node from the path
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

                // Get the client matrix
                Server s = new Server(adjMatrix);

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

                /* Graph Visualisation */
                JFrame frame = new Server(adjMatrix);
                frame.setSize(500, 500);
                frame.setVisible(true);
                frame.setTitle("Graph Image");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        } catch(IOException e){}
    }
}
