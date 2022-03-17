package base.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientApp {

	///Field
	private Socket socket;
	private BufferedReader fromServer;
	private PrintWriter toServer;
	
	///Constructor
	public SocketClientApp() throws Exception{
		this.connect("127.0.0.1","7000");
		
		this.dataSendAndReceive();
	}
	
	///Method
	public void connect(String connectIp, String connectPort) {
		
		try {
			socket = new Socket(connectIp, Integer.parseInt(connectPort));
			
			socket.setSoTimeout(3000);
			
			toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dataSendAndReceive() {
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			while(true) {
				System.out.print("[Client] : Server로 전송할 Data : ");
				
				String content = keyboard.readLine();
				
				if(content.equals("quit")) {
					break;
				}
				
				toServer.println(content);
				
				String serverData = fromServer.readLine();
				
				System.out.println("[Client] : Server로 부터 받은 Data : " + serverData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	///main method
	public static void main(String[] args) throws Exception{
		new SocketClientApp();
	}//end of main

}//end of class
