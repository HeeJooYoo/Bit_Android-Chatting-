package net.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import net.server.thread.ServerSocketThread;

public class EchoMultiServerApp {

	//Main method
	public static void main(String[] args) {
		System.out.println("==============================");
		System.out.println("[EchoMultiServerApp Main Thread] : StartUp...\n");
		
		List<ServerSocketThread> list = new Vector<ServerSocketThread>(10,10);
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		ServerSocketThread serverSocketThread = null;
		
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
				System.out.println("\t\t\t\t[EchoMultiServerApp Main Thread] : Client Connection Wait");
				socket = serverSocket.accept();
				
				System.out.println("\n\t\t\t\t\t [Host Main Thread] : client" + socket.getRemoteSocketAddress() + " 연결");
				
				serverSocketThread = new ServerSocketThread(socket, list);
				
				list.add(serverSocketThread);
				
				System.out.println("\t\t\t\t[EchoMultiServerApp Main Thread] : 현재 접속자수" + list.size()+"\n");
				serverSocketThread.start();
				
			} catch (Exception e) {
				e.printStackTrace();
				
				loopFlag = false;
			}
		}//end of while
		
		System.out.println("\t\t\t\t\t[EchoMultiServerApp Main Thread] : Client Connection Wait END");
		System.out.println("\n\t\t\t\t\t\t////////////////////////////////////");
		
		synchronized (list) {
			for(ServerSocketThread thread : list) {
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
		
		System.out.println("\n[EchoMultiServerApp Main Thread] : ShutDown");
		System.out.println("==============================");
	}

}
