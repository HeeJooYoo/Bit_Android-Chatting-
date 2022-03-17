package base.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp03 {
		
	//main method
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(7000);
		
		while(true) {
			System.out.println("[Server] : Client�� ������ ��ٸ���...");
			
			Socket socket = serverSocket.accept();
			System.out.println("[Server] : client ���� �Ϸ�...");
			
			new SocketThread(socket).start();
			
			System.out.println("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>> ����� main�� while�� �� \n\n\n");
		}
	}// end of main
}//end of class

class SocketThread extends Thread{
	///Field
	private BufferedReader fromClient;
	private PrintWriter toClient;
	
	///Constructor
	public SocketThread() {
		super();
	}
	
	public SocketThread(Socket socket) {
		try {
			
			fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	///Method
	public void run() {
		
		try {
			while(true) {
				String clientData = fromClient.readLine();
				System.out.println("[Server] : client�� ���� ���� ���� Data ==> " + clientData);
				toClient.println("Server ȸ�� [Data receiver OK]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}//end of SocketThread
