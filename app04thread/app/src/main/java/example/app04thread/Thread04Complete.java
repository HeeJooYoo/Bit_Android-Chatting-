package example.app04thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import example.app04thread.daemon.DaemonThread03;
import example.app04thread.daemon.DaemonThread04;

public class Thread04Complete extends AppCompatActivity {

    ///Field
    private TextView textViewMain;
    private TextView textViewThread;
    private int mainValue;
    private int threadValue;
    private DaemonThread04 daemonThread04;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            if(message.what == 100){
                //threadValue = message.arg1;
                threadValue = ((Integer)message.obj).intValue();
                textViewThread.setText(threadValue+"");
            }
        }
    };

    ///Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.thread01ui);

        this.textViewMain = (TextView)this.findViewById(R.id.textview_main);
        this.textViewThread = (TextView)this.findViewById(R.id.textview_thread);

        this.daemonThread04 = new DaemonThread04(handler);
        daemonThread04.start();

        ((Button)this.findViewById(R.id.button)).setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainValue++;
                textViewMain.setText(mainValue+"");
                threadValue = daemonThread04.getThreadValue();
                textViewThread.setText(threadValue+"");
            }
        });

        System.out.println("===>"+getClass().getSimpleName()+".onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.daemonThread04.setThreadStop(true);
        System.out.println("===>"+getClass().getSimpleName()+".onDestroy()");
    }
}