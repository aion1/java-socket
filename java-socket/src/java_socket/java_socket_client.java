package java_socket;

import java.io.*;
import java.net.*;

public class java_socket_client {

    public static void main(String[] args) throws Exception {
        java_socket_client Client = new java_socket_client();
        Client.run();
    }

    private void run() throws Exception {

        Socket Sock = new Socket("localhost", 666);  //the client should be having the port and the host address
        //the socket is acting as the real socket or a connector each client is having his own socket 
        PrintStream PS = new PrintStream(Sock.getOutputStream()); //it is now sending to the server
        PS.println("Hello to server from clint");

        InputStreamReader IR = new InputStreamReader(Sock.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String Message = BR.readLine();
        System.out.println(Message);

    }
}
