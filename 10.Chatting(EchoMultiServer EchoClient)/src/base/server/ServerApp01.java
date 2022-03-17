package base.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp01 {
	
	//main method
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(7000);
		
		System.out.println("[Server] : Client의 접속을 기다린다...");
		
		Socket socket = serverSocket.accept();
		System.out.println("[Server] : client 연결 완료...");
		
		BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		PrintWriter toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
		
		System.out.println("[Server] : client 접속 후 data를 받기위한 무한 loop start \n");
		
		while(true) {
			String clientData = fromClient.readLine();
			
			System.out.println("[Server] : client로 부터 전송 받은 Data ==> " + clientData);
			toClient.println("server 회신[Data receiver OK]");
		}
	}// end of main

}//end of class
