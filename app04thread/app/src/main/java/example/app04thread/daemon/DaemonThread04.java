package example.app04thread.daemon;

import android.os.Handler;
import android.os.Message;

public class DaemonThread04 extends Thread {

    ///Field
    private int threadValue;
    private Handler handler;
    private boolean threadStop = false;

    ///Constructor
    public DaemonThread04(){
        super();
    }
    public DaemonThread04(Handler handler){
        super();
        this.handler = handler;
    }

    ///Method
    public void run(){
        while (!threadStop){
            threadValue++;
            System.out.println(getClass().getSimpleName() + " .threadValue : " + threadValue);

            /*Message message = new Message();
            message.what = 100;
            message.arg1 = this.threadValue;
            message.obj = new Integer(this.threadValue);*/

            Message message = Message.obtain(handler, 100, threadValue, 0, new Integer(threadValue));

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

