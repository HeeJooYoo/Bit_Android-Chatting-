package example.app04thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Thread06ETCPost extends AppCompatActivity {

    ///Field
    private TextView textViewMain;
    private TextView textViewThread;
    private int mainValue;
    private int threadValue;
    private DaemonThread06 daemonThread06;

    private Handler handler = new Handler();

    ///Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.thread01ui);

        this.textViewMain = (TextView)this.findViewById(R.id.textview_main);
        this.textViewThread = (TextView)this.findViewById(R.id.textview_thread);

        this.daemonThread06 = new DaemonThread06();
        daemonThread06.start();

        ((Button)this.findViewById(R.id.button)).setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainValue++;
                textViewMain.setText(mainValue+"");
                textViewThread.setText(threadValue+"");
            }
        });

        System.out.println("===>"+getClass().getSimpleName()+".onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.daemonThread06.setThreadStop(true);
        System.out.println("===>"+getClass().getSimpleName()+".onDestroy()");
    }

    public class DaemonThread06 extends Thread {
        ///Field
        private boolean threadStop = false;

        ///Constructor
        public DaemonThread06(){

        }

        ///Method
        public void run(){
            while (!threadStop){
                threadValue++;
                System.out.println(getClass().getSimpleName() + " .threadValue : " + threadValue);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewThread.setText(threadValue+"");
                    }
                });

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        public void setThreadStop(boolean threadStop){
            this.threadStop = threadStop;
        }
    }
}