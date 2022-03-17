package example.app04thread.daemon;

import android.widget.TextView;

public class DaemonThread02 extends Thread {

    ///Field
    private TextView textViewThread;
    private int threadValue;
    private boolean threadStop = false;

    ///Constructor
    public DaemonThread02(){
        super();
    }
    public DaemonThread02(TextView textViewThread, int threadValue){
        super();
        this.textViewThread = textViewThread;
        this.threadValue = threadValue;
    }

    ///Method
    public void run(){
        while (!threadStop){
            threadValue++;
            System.out.println(getClass().getSimpleName() + " .threadValue : " + threadValue);

            textViewThread.setText(threadValue+"");

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

