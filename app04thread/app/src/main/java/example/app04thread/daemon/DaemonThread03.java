package example.app04thread.daemon;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class DaemonThread03 extends Thread {

    ///Field
    private int threadValue;
    private Handler handler;
    private boolean threadStop = false;

    ///Constructor
    public DaemonThread03(){
        super();
    }
    public DaemonThread03(Handler handler){
        super();
        this.handler = handler;
    }

    ///Method
    public void run(){
        while (!threadStop){
            threadValue++;
            System.out.println(getClass().getSimpleName() + " .threadValue : " + threadValue);

            Message message = new Message();
            message.what = 100;
            message.arg1 = this.threadValue;
            message.obj = new Integer(this.threadValue);

            this.handler.sendMessage(message);

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public int getThreadValue(){
        return this.threadValue;
    }

    public void setThreadStop(boolean threadStop){
        this.threadStop = threadStop;
    }
}

