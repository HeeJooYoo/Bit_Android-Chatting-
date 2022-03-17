package net.client;

import net.client.thread.ClientSocketThread;

public class EchoClientApp {

	public static void main(String[] args) {
		System.out.println("=============================");
		System.out.println("[EchoClientApp Main Thread] : Start Up...\n");
		
		String connectIp = "127.0.0.1";
		int connectPort = 7000;
		
		ClientSocketThread clientSocketThread = new ClientSocketThread(connectIp, connectPort);
		clientSocketThread.start();
		
		try {
			clientSocketThread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n [EchoClientApp] Main Thread] : ShutDown");
		System.out.println("=============================");
	}
}
