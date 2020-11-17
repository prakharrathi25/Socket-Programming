# Socket-Programming

Socket programming is a way of connecting two nodes on a network to communicate with each other. One socket(node) listens on a particular port at an IP, while other socket reaches out to the other to form a connection. Server forms the listener socket while client reaches out to the server. 

## Socket Programming in Java

Java Socket programming is used for communication between the applications running on different JRE. Java Socket programming can be connection-oriented or connection-less. Socket and ServerSocket classes are used for connection-oriented socket programming and DatagramSocket and DatagramPacket classes are used for connection-less socket programming. The client in socket programming must know two information:

1. IP Address of Server, and
2. Port number.

I have made a one-way client and server communication. In this application, client sends a message to the server, server reads the message and prints it. Here, two classes are being used: Socket and ServerSocket. The Socket class is used to communicate client and server. Through this class, we can read and write message. The ServerSocket class is used at server-side. The accept() method of ServerSocket class blocks the console until the client is connected. After the successful connection of client, it returns the instance of Socket at server-side.

<p align="center">
  <img src="Images/socket-programming-java.png" width="350" title="Socket Programming Flow in Java">
</p>

## Directory Structure 

1. **Server-Side**: To create the server application, I have created an instance of ServerSocket class. Here, we are using 6666 port number for the communication between the client and server. You may also choose any other port number. The accept() method waits for the client. If clients connects with the given port number, it returns an instance of Socket. The Server-Side folder contains server.java file which has the code for the server side connection. 
2. **Client-Side**: To create the client application, I have created an instance of Socket class. Here, we need to pass the IP address or hostname of the Server and a port number. Here, we are using "localhost" because our server is running on same system. The Client-Side folder contains client.java file which has the code for the client side connection. 


## Problem being Solved

