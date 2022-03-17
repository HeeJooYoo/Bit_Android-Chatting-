package example.app04thread;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import example.app04thread.daemon.DaemonThread02;

public class Thread02Problem extends AppCompatActivity {

    ///Field
    private TextView textViewMain;
    private TextView textViewThread;
    private int mainValue;
    private int threadValue;
    private DaemonThread02 daemonThread02;

    ///Constructor

    ///Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.thread01ui);

        this.textViewMain = (TextView)this.findViewById(R.id.textview_main);
        this.textViewThread = (TextView)this.findViewById(R.id.textview_thread);

        this.daemonThread02 = new DaemonThread02(textViewThread, threadValue);
        daemonThread02.start();

        ((Button)this.findViewById(R.id.button)).setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainValue++;
                textViewMain.setText(mainValue+"");
                threadValue = daemonThread02.getThreadValue();
                textViewThread.setText(threadValue+"");
            }
        });

        System.out.println("===>"+getClass().getSimpleName()+".onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.daemonThread02.setThreadStop(true);
        System.out.println("===>"+getClass().getSimpleName()+".onDestroy()");
    }
}