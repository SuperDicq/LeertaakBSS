import java.net.*;
import java.io.*;

public class Server{
	
	private static int port = 7789;
	
	public static void main(String[] args) throws Exception{
			
        ServerSocket server = new ServerSocket(port);
        System.out.println("Port " + port + " opened");
        
        while(true) {
        	
	        Socket socket = server.accept();
	        System.out.println("Connection established");
	 
	        Thread thread = new Thread(new Worker(socket));  
	        thread.start(); 
	        
        }
    }
	
}
