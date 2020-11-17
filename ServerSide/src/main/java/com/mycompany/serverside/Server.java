/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverside;
// Import java Packages 
import java.io.*; 
import java.net.*; 

/**
 * Name: Prakhar Rathi 
 * Roll Number: 1810110169
 */
public class Server {
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
                double radius = input.readDouble();
                double area = radius * radius * Math.PI; 

                // send data to the client
                output.writeDouble(area);              
            }
        } catch(IOException e){}
    }
}
