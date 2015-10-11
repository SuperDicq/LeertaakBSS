import java.io.*;
import java.net.*;

public class Worker implements Runnable{
	
	Socket socket;

	Worker(Socket socket){
		
		this.socket = socket;
		
	}
	
	public void run(){
		
        try{
        	
        	BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String url = reader.readLine();
	        
	        System.out.println("Received: "+url);
	        
	        PrintStream printstream = new PrintStream(socket.getOutputStream());  
		        
			try{
				
		        InetAddress hostAddress = InetAddress.getByName(url);
		        String IPaddress = hostAddress.getHostAddress();
		        
		        printstream.println(IPaddress);
		        
			}
			catch(UnknownHostException ex) {
				
				printstream.println("Unable to resolve host "+url+".");
				
			}
			
			socket.close();
			
        }
        catch(IOException ex){
        	
        	System.out.println(ex);
        	
        }
		
	}
	
}
