package example.app14chatting.thread;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class ChatClientSocketThread extends Thread {

    ///Field
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private Socket socket;
    private int timeOut = 3000;
    private boolean loopFlag = false;

    private Handler handler;
    private String connectIp = "192.168.0.52";
    private int connectPort = 7000;
    private String clientName;

    public ChatClientSocketThread() {
    }

    public ChatClientSocketThread(Handler handler, String clientName) {

        this.handler = handler;
        this.clientName = clientName;
    }

    ///Method
    public void run() {

        System.out.println("[Client Thread] : " + getClass().getSimpleName() + ".run() START....");

        try {
            this.socket = new Socket();

            socket.setSoTimeout(timeOut);

            socket.setSoLinger(true, timeOut);

            SocketAddress socketAddress = new InetSocketAddress(connectIp, connectPort);
            socket.connect(socketAddress, timeOut*3);

            toServer = new PrintWriter( new OutputStreamWriter(socket.getOutputStream(),"UTF-8"), true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

            toServer.println("100: " + clientName);

            loopFlag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        while(loopFlag) {
            try {
                System.out.println("\n[Client Thread] : Server 와 data 수신 무한 Loop Start");

                String fromHostData = fromServer.readLine();

                if(fromHostData == null) {
                    break;
                }

                System.out.println(":: Server에서 수신 Data : " + fromHostData);

                Message message = new Message();
                message.what = 100;
                message.obj = fromHostData;
                this.handler.sendMessage(message);

                if(fromHostData.indexOf("대화명 중복") != -1){
                    break;
                }

            } catch (SocketTimeoutException stoe) {
                System.out.println(stoe.toString());
                //stoe.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

                Message message = new Message();
                message.what = 500;
                message.obj = "서버접속이 강제종료됨";
                this.handler.sendMessage(message);

                loopFlag = false;
            }
        }// end of while

        this.close();

        System.out.println("[Client Thread] : " + getClass().getSimpleName() + ".run() END...");
    }// end of run()

    public void close() {

        System.out.println(":: close() start");

        try {
            if(toServer != null) {
                toServer.close();
                toServer = null;
            }

            if(fromServer != null) {
                fromServer.close();
                fromServer = null;
            }
            if(socket != null) {
                socket.close();
                socket = null;
            }

            Thread.sleep(timeOut);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(":: close() end...");

    }

    public void onDestroy(){
        System.out.println("[Client Thread] : " + getClass().getSimpleName() + ".onDestroy()");
        loopFlag = false;
    }

    public void sendMessageToServer(String message) {

        if(toServer != null){
            toServer.println(message);
        }
    }
}// end of class