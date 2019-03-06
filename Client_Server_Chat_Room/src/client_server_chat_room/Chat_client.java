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
import javax.swing.JOptionPane;
/**
 *
 * @author gogoh
 */
public class Chat_client implements Runnable{
        Socket Sock;
        Scanner in;
        Scanner Send=new Scanner(System.in);
        PrintWriter out;
        
        
    public Chat_client(Socket Sock) {
        this.Sock=Sock;
    }


        
        
        public void run(){
            
            try {
                try {
                    in=new Scanner(Sock.getInputStream());
                    out=new PrintWriter(Sock.getOutputStream());
                    out.flush();
                    checkstream();
                    
                }
                
                finally {
                    Sock.close();
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    private void checkstream() {
        while (true) {            
            Receive();
        }
    }

    private void Receive() {
        if(in.hasNext())
        {
            String message=in.nextLine();
            if(message.contains("#?!"))
            {
                String temp1=message.substring(3);
                       temp1=temp1.replace("[","");
                       temp1=temp1.replace("]","");
                       
                    String [] currentuser;
                currentuser = temp1.split(", ");
                chat_Client_GUI.jL_online.setListData(currentuser);
}
            
else{
         chat_Client_GUI.TA_connversation.append(message+"\n");
    }

    }
    }
    public  void send(String x)
    {
        out.println(chat_Client_GUI.username+": "+x);
        out.flush();
        chat_Client_GUI.TF_message.setText("");
    }
    public void disconnect() throws IOException
    {
        out.println(chat_Client_GUI.username+" has disconnected");
        out.flush();
        Sock.close();
        JOptionPane.showMessageDialog(null,"you disconnected");
        System.exit(0);
    }
}
