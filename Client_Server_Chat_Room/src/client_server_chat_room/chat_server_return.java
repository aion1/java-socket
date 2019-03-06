/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_server_chat_room;
import com.sun.security.ntlm.Client;
import java.io.*;
import java.net.*;

import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author gogoh
 */
class chat_server_return implements Runnable{
        Socket Sock;
        private Scanner in;
        private PrintWriter out;
        String message="";
        
    chat_server_return(Socket x) {
        this.Sock=x;
    }
    
    public void CheckConn()throws IOException
    {
        if(!Sock.isConnected()){
            for(int i=0;i<=Chat_Server.Connections.size();i++)
            {
                if(Chat_Server.Connections.get(i)==Sock)
                {
                    Chat_Server.Connections.remove(i);
                }
            }
            for(int i=1;i<=Chat_Server.Connections.size();i++)
            {
                Socket temp_sock=(Socket)Chat_Server.Connections.get(i-1);
                PrintWriter temp_out=new PrintWriter(Sock.getOutputStream());
                temp_out.println(temp_sock.getLocalAddress().getHostName()+"disconnected");
                temp_out.flush();
                System.out.println(temp_sock.getLocalAddress().getHostName()+"disconnected");
            }
        }
    }
    
    
    @Override
    public void run() {
        try {
            try {
                in=new Scanner(Sock.getInputStream());
                out=new PrintWriter(Sock.getOutputStream());
                
                while(true)
                {
                    CheckConn();
                    if(!in.hasNext())
                    {
                        return;
                    }
                    message=in.nextLine();
                    System.out.println("client said:" +message);
                    for(int i=1;i<=Chat_Server.Connections.size();i++)
                    {
                        Socket temp_sock=(Socket) Chat_Server.Connections.get(i-1);
                        PrintWriter temp_out=new PrintWriter(temp_sock.getOutputStream());
                        temp_out.println(message);
                        temp_out.flush();
                        System.out.println("sent to: "+temp_sock.getLocalAddress().getHostName());
                    }
                }
            } 
                finally{
                Sock.close();
            }
        } catch (Exception e) {
            
            System.out.println(e);
        }
    }

}
