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
			System.out.println("[Server] : Client의 접속을 기다린다...");
			
			Socket socket = serverSocket.accept();
			System.out.println("[Server] : client 연결 완료...");
			
			new SocketThread(socket).start();
			
			System.out.println("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>> 여기는 main의 while문 끝 \n\n\n");
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
				System.out.println("[Server] : client로 부터 전송 받은 Data ==> " + clientData);
				toClient.println("Server 회신 [Data receiver OK]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}//end of SocketThread
