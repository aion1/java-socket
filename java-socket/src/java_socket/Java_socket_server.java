
package java_socket;
import java.io.*;
import java.net.*;
/**
 *
 * @author gogoh
 */
public class Java_socket_server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
            Java_socket_server Server=new Java_socket_server();
            Server.run();
    }

    private void run() throws Exception {
        ServerSocket Servskt=new ServerSocket(666);
        Socket Sock=Servskt.accept();
        InputStreamReader IR=new InputStreamReader(Sock.getInputStream());   //listen from the clint then put it in a bufferreader 
        BufferedReader BR=new BufferedReader(IR);
        String Message=BR.readLine();
        System.out.println(Message);     //print the clint message
        
        
        if(Message !=null)
        {
            PrintStream PS= new PrintStream(Sock.getOutputStream());
            PS.println("Message recived successfully");
            
        }
    }
    
}
