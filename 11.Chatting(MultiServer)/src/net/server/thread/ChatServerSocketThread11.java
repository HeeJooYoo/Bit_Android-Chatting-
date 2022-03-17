package net.server.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.List;

public class ChatServerSocketThread11 extends Thread {

	///Field
	private BufferedReader fromClient;
	private PrintWriter toClient;
	private Socket socket;
	private List<ChatServerSocketThread11> list;
	private boolean loopFlag;
	private SocketAddress socketAddress;
		
	public ChatServerSocketThread11() {
	}

	public ChatServerSocketThread11(Socket socket, List<ChatServerSocketThread11> list) {
		this.socket = socket;
		this.list = list;
		
		try {
			fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			
			loopFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	///Method
	public void run() {
		
		System.out.println("[ServerSocketThread] (" + socketAddress + ") : ] : data�� ���� �۽� Loop Start");
		String fromClientData = null;
		
		while(loopFlag) {
			try {
				if((fromClientData = fromClient.readLine()) != null && !fromClientData.equals("Quit")) {
					System.out.println("\n[ChatServerSocketThread "+ socketAddress +")] : Client ���۹��� Data ==> " + fromClientData);
					
					//toClient.println(fromClientData);
					toAllClient(fromClientData);
					
				}else {
					System.out.println("\n[ChatServerSocketThred " + socketAddress + " ] : Client ���������  Thread ������");
					break;
				}
				
			} catch (SocketException se) {
				se.printStackTrace();
				loopFlag = false;
			} catch (Exception e) {
				e.printStackTrace();
				loopFlag = false;
			}
		}// end of while
		
		System.out.println("\n[ServerSocketThread (" + socketAddress + " ) : ] : data�� ����, �۽� Loop End");
		this.close();
	}// end of run()
	
	public synchronized void toAllClient(String message) {
		for(ChatServerSocketThread11 chatServerSocketThread : list) {
			chatServerSocketThread.getWriter().println(message);
		}
	}
	
	public PrintWriter getWriter() {
		return toClient;
	}
	
	public void close() {
		
		System.out.println(":: close() start");
		
		try {
			if(toClient != null) {
				toClient.close();
				toClient = null;
			}
			
			if(fromClient != null) {
				fromClient.close();
				fromClient = null;
			}
			if(socket != null) {
				socket.close();
				socket = null;
			}
			
			list.remove(this);
			
			System.out.println("�����ڼ� : " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(":: close() end...");
		
	}

	public void setLoopFlag(boolean loopFlag) {
		this.loopFlag = loopFlag;
	}
	
}// end of class