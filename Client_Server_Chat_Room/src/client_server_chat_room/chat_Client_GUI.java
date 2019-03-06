/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_server_chat_room;

import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 *
 * @author gogoh
 */
public class chat_Client_GUI {
    public static Chat_client chatclient;
    public static String username="Anonymous";
    
    public static JFrame Mainwindow=new JFrame();
    private static JButton B_about=new JButton();
    private static JButton B_connect=new JButton();
    private static JButton B_disconnection=new JButton();
    private static JButton B_Help=new JButton();
    private static JButton B_send=new JButton();
    private static JLabel l_Message=new JLabel("Messasge:.....");
    public static JTextField TF_message=new JTextField();
    private static JLabel L_conversation=new JLabel();
    public static JTextArea TA_connversation=new JTextArea();
    private static JScrollPane SP_converstion=new JScrollPane();
    private static JLabel L_online=new JLabel();
    public static JList jL_online=new JList();
    private static JLabel L_login=new JLabel();
    private static JLabel L_loginasbox=new JLabel();
    private static JScrollPane SP_online=new JScrollPane();
    
    public static JFrame loginwindow=new JFrame();
    public static JTextField usernamebox=new JTextField(20);
    private static JButton B_enter=new JButton("enter");
    private static JLabel L_enterusernmae=new JLabel("enter username: ");
    private static JPanel p_login=new JPanel();
    
    
    public static void main(String args[])
    {
        Build_main_window();
        initialize();
    }
    public static void connect()
    {
        try {
            final int port=666;
            final String host="Galactica";
            Socket Sock=new Socket(host, port);
            System.out.println("you connected to: " +host);
            chatclient =new Chat_client(Sock);
            
            PrintWriter out=new PrintWriter(Sock.getOutputStream());
            out.println(username);
            out.flush();
            
            Thread x=new Thread(chatclient);
            x.start();
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"server isnt responding..");
            System.exit(0);
        }
        
    }
    public static void initialize(){
        B_send.setEnabled(true);
        B_disconnection.setEnabled(false);
        B_connect.setEnabled(true);
    }
    private static void Build_main_window() {
        Mainwindow.setTitle(username+"s chat");
        Mainwindow.setSize(450,500);
        Mainwindow.setLocation(220,180);
        Mainwindow.setResizable(false);
        
        config_main_window();
        main_window_action();
        
        Mainwindow.setVisible(true);

    }
    public static void Build_login_window()
    {
        loginwindow.setTitle("what is your name");
        loginwindow.setSize(400,100);
        loginwindow.setLocation(250,200);
        loginwindow.setResizable(false);
        p_login=new JPanel();
        p_login.add(L_enterusernmae);
        p_login.add(usernamebox);
        p_login.add(B_enter);
        loginwindow.add(p_login);
        
        login_action();
        loginwindow.setVisible(true);
        
    }

    private static void login_action() {
        B_enter.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Action_B_enter();
            }

            private void Action_B_enter() {
                 if(!usernamebox.getText().equals(""))
                 {
                     username =usernamebox.getText().trim();
                     L_loginasbox.setText(username);
                     Chat_Server.Current_users.add(username);
                     Mainwindow.setTitle(username +"s chat box");
                     loginwindow.setVisible(false);
                     B_send.setEnabled(true);
                     B_disconnection.setEnabled(true);
                     B_connect.setEnabled(false);
                     connect();  
                 }
                 else
                 {
                     JOptionPane.showMessageDialog(null,"please enter your name: ");
                 }
            }
        });
    }
    

    private static void config_main_window() {
        Mainwindow.setBackground(new java.awt.Color(255,255,255));
        Mainwindow.setSize(500,320);
        Mainwindow.getContentPane().setLayout(null);
        
        B_send.setForeground(new java.awt.Color(255,255,255));
        B_send.setBackground(new java.awt.Color(0,0,255));
        B_send.setText("SEND");
        Mainwindow.getContentPane().add(B_send);
        B_send.setBounds(250,40,81,25);
        
        B_disconnection.setForeground(new java.awt.Color(255,255,255));
        B_disconnection.setBackground(new java.awt.Color(0,0,255));
        B_disconnection.setText("disconnect");
        Mainwindow.getContentPane().add(B_disconnection);
        B_disconnection.setBounds(10,40,110,45);
        
        B_connect.setForeground(new java.awt.Color(0,0,255));
        B_connect.setBackground(new java.awt.Color(255,255,255));
        B_connect.setText("connect");
        Mainwindow.getContentPane().add(B_connect);
        B_connect.setBounds(130,40,110,45);
        
        
        B_Help.setForeground(new java.awt.Color(0,0,255));
        B_Help.setBackground(new java.awt.Color(255,255,255));
        B_Help.setText("help");
        Mainwindow.getContentPane().add(B_Help);
        B_Help.setBounds(420,40,70,45);
        
        B_about.setForeground(new java.awt.Color(0,0,255));
        B_about.setBackground(new java.awt.Color(255,255,255));
        B_about.setText("about");
        Mainwindow.getContentPane().add(B_about);
        B_about.setBounds(420,40,70,45);
        
        l_Message.setText("Message:....");
        Mainwindow.getContentPane().add(l_Message);
        l_Message.setBounds(10,10,60,20);
        
        TF_message.setForeground(new java.awt.Color(0,0,255));
        TF_message.requestFocus();
        TF_message.setText("about");
        Mainwindow.getContentPane().add(TF_message);
        TF_message.setBounds(70,4,260,30);
        
        L_conversation.setHorizontalAlignment(SwingConstants.CENTER);
        L_conversation.setText("conversation");
        Mainwindow.getContentPane().add(L_conversation);
        L_conversation.setBounds(100, 70, 140, 16);
        
        TA_connversation.setColumns(20);
        TA_connversation.setFont(new java.awt.Font("Tahoma", 0, 12));
        TA_connversation.setForeground(new java.awt.Color(0,0,255));
        TA_connversation.setLineWrap(true);
        TA_connversation.setRows(5);
        TA_connversation.setEditable(false);
        
        //SP_converstion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //SP_converstion.setHorizontalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_converstion.setViewportView(TA_connversation);
        Mainwindow.getContentPane().add(SP_converstion);
        SP_converstion.setBounds(10, 90, 330, 180);
        
        L_online.setHorizontalAlignment(SwingConstants.CENTER);
        L_online.setText("curruntly online");
        L_online.setToolTipText("");
        Mainwindow.getContentPane().add(TF_message);
        L_online.setBounds(350,70,130,16);
        
        String [] textname={"george","andrew","bavly","maria"};
        jL_online.setForeground(new java.awt.Color(0,0,255));
        jL_online.setListData(textname);
        
        //SP_online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //SP_online.setHorizontalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_online.setViewportView(jL_online);
        Mainwindow.getContentPane().add(SP_online);
        SP_online.setBounds(350,90,130,180);
        
        L_login.setFont(new java.awt.Font("Tahoma", 0, 12));
        L_login.setText("currently logged in as: ");
        Mainwindow.getContentPane().add(L_login);
        L_online.setBounds(348,0,140,15);
        
        L_loginasbox.setHorizontalAlignment(SwingConstants.CENTER);
        L_loginasbox.setFont(new java.awt.Font("Tahoma", 0, 12));
        L_loginasbox.setForeground(new java.awt.Color(255,0,0));
        L_loginasbox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,255)));
        Mainwindow.getContentPane().add(L_loginasbox);
        L_loginasbox.setBounds(340,17,150,20);
        
    }

    private static void main_window_action() {
        B_send.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action_B_Send();
            }

            private void Action_B_Send() {
                if(!TF_message.getText().equals(""))
                {
                    chatclient.send(TF_message.getText());
                    TF_message.requestFocus();
                }
            }
        });
        B_disconnection.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Action_B_disconnect();
            }

            private void Action_B_disconnect() {
                try {
                    chatclient.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        B_connect.addActionListener(new java .awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Action_B_connect();
            }

            private void Action_B_connect() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                B_about.addActionListener(new java .awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Action_B_about();
            }

            private void Action_B_about() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
            B_Help.addActionListener(new java .awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Action_B_help();
            }

            private void Action_B_help() {
                JOptionPane.showMessageDialog(null,"multi-client chat george 2019 ");
            }
        });     
    }
}