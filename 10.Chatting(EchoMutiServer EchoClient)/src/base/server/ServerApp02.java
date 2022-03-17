package base.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp02 {
	///Field
	
	///Constructor
	public ServerApp02(Socket socket) {
		try {
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			
			System.out.println("[Server] : client ���� �� data�� �ޱ����� ���� loop start");
			
			while(true) {
				String clientData = fromClient.readLine();
				System.out.println("[Server] : client�� ���� ���� ���� Data ==> " + clientData);
				toClient.println("Server ȸ�� [Data receiver OK]");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//main method
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(7000);
		
		while(true) {
			System.out.println("[Server] : Client�� ������ ��ٸ���...");
			
			Socket socket = serverSocket.accept();
			System.out.println("[Server] : client ���� �Ϸ�...");
			
			new ServerApp02(socket);
			
			System.out.println("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>> ����� main�� while�� �� \n\n\n");
		}
	}// end of main

}//end of class
