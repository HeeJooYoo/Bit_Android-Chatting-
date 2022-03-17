package net.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import net.server.thread.ChatServerSocketThread12;

public class ChatServerApp12 {

	//Main method
	public static void main(String[] args) {
		System.out.println("==============================");
		System.out.println("[ChatServerApp Main Thread] : StartUp...\n");
		
		List<ChatServerSocketThread12> list = new Vector<ChatServerSocketThread12>(10,10);
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		ChatServerSocketThread12 chatServerSocketThread = null;
		
		boolean loopFlag = false;
		
		try {
			serverSocket = new ServerSocket(7000);
			loopFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(loopFlag) {
			try {
				System.out.println("\n\t\t\t////////////////////////////////////");
				System.out.println("\t\t\t[ChatServerApp Main Thread] : Client Connection Wait");
				socket = serverSocket.accept();
				
				System.out.println("\n\t\t\t client: " + socket.getRemoteSocketAddress() + " 연결");
				
				chatServerSocketThread = new ChatServerSocketThread12(socket, list);
				
				list.add(chatServerSocketThread);
				
				System.out.println("\n\t\t\t 총 접속자수: " + list.size()+"\n");
				chatServerSocketThread.start();
				
			} catch (Exception e) {
				e.printStackTrace();
				
				loopFlag = false;
			}
		}//end of while
		
		System.out.println("\t\t\t\t\t[ChatServerApp Main Thread] : Client Connection Wait END");
		System.out.println("\n\t\t\t\t\t\t////////////////////////////////////");
		
		synchronized (list) {
			for(ChatServerSocketThread12 thread : list) {
				thread.setLoopFlag(false);
			}
		}
		
		while (true){
			if(list.size() != 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}else {
				break;
			}
		}
		
		try {
			if(serverSocket != null) {
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\n[ChatServerApp Main Thread] : ShutDown");
		System.out.println("==============================");
	}

}
