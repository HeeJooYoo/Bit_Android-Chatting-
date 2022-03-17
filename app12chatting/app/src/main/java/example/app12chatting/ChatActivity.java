package example.app12chatting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import example.app12chatting.thread.ChatClientSocketThread;

public class ChatActivity extends AppCompatActivity {

    ///Field
    private TextView textViewContent;
    private Button buttonSend;
    private EditText editTextMessage;
    private ScrollView scrollView;
    private ChatClientSocketThread chatClientSocketThread;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            if(message.what == 100){
                String fromHostData = (String) message.obj;
                textViewContent.append(fromHostData + "\n");
                //scrollView.scrollTo(0, scrollView.getHeight());
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                editTextMessage.setText("");
            }

            if(message.what == 500){
                String endMessage = (String) message.obj;
                textViewContent.append(endMessage+"\n");
                scrollView.scrollTo(0, scrollView.getHeight());
                //scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                buttonSend.setEnabled(false);
                editTextMessage.setEnabled(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        Intent intent  = this.getIntent();

        String clientName = intent.getStringExtra("clientName");
        System.out.println(getClass().getSimpleName()+"::대화명:: " + clientName);

        this.textViewContent = (TextView) findViewById(R.id.textView_content);
        this.scrollView = (ScrollView) findViewById(R.id.scrollview);
        this.buttonSend = (Button) findViewById(R.id.button_send);
        this.editTextMessage = (EditText) findViewById(R.id.edittext_message);

        this.chatClientSocketThread = new ChatClientSocketThread(handler, clientName);
        chatClientSocketThread.start();

        buttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //chatClientSocketThread.sendMessageToServer(editTextMessage.getText() + "");
                new Thread(){
                    @Override
                    public void run() {
                        chatClientSocketThread.sendMessageToServer("200: " + editTextMessage.getText());
                    }
                }.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(getClass().getSimpleName()+".onDestroy()");

        new Thread(){
            @Override
            public void run() {
                chatClientSocketThread.sendMessageToServer("400: " + editTextMessage.getText());
            }
        }.start();

        if(chatClientSocketThread != null){
            chatClientSocketThread.onDestroy();
        }
    }

    private long re_PressLimitTime = 2000;
    private long firstPressedTime = 0;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long re_PressIntervalTime = tempTime - firstPressedTime;

        if(0 <= re_PressIntervalTime && re_PressLimitTime >= re_PressIntervalTime){
            super.onBackPressed();
        }else{
            firstPressedTime = tempTime;

            Toast.makeText(this, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}