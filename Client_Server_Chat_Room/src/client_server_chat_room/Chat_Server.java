/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_server_chat_room;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author gogoh
 */
public class Chat_Server {
    public static ArrayList<Socket>Connections=new ArrayList<Socket>();
    public static ArrayList<String> Current_users=new ArrayList<String>();
    
    
    
        public static void main(String[] args) throws Exception
        {
            try {
                final int port=666;
                ServerSocket Server=new ServerSocket(port);
                System.out.println("waiting for clints.....");
                while(true){
                    Socket Sock= Server.accept();
                    Connections.add(Sock);
                    System.out.println("client connected from: "+Sock.getLocalAddress().getHostName());
                    Add_User_Name(Sock);
                    chat_server_return Chat= new chat_server_return(Sock);
                    Thread x=new Thread(Chat);
                    x.start();
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }

    private static void Add_User_Name(Socket Sock) throws Exception {
        Scanner Input=new Scanner(Sock.getInputStream());
        String Username=Input.nextLine();
        Current_users.add(Username);
            
            for(int i=1;i<=Chat_Server.Connections.size();i++)
            {
               Socket temp_sock=(Socket)Chat_Server.Connections.get(i-1);
               PrintWriter out =new PrintWriter(temp_sock.getOutputStream());
               out.println("#?!"+Current_users);
               out.flush();
            }
    }
}
