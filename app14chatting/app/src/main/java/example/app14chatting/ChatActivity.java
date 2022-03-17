package example.app14chatting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import example.app14chatting.thread.ChatClientSocketThread;
import example.app14chatting.util.EndAlertDialog;

public class ChatActivity extends AppCompatActivity {

    ///Field
    //private TextView textViewContent;
    private LinearLayout messageInLayout;
    private String clientName;
    private Button buttonSend;
    private EditText editTextMessage;
    private ScrollView scrollView;
    private ChatClientSocketThread chatClientSocketThread;

    private Handler handler = new Handler(){

        public void handleMessage(Message message) {
            if (message.what == 100) {
                String fromHostData = (String) message.obj;
                //textViewContent.append(fromHostData + "\n");
                append(fromHostData);

                if (fromHostData.indexOf("대화명 중복") != -1) {
                    buttonSend.setEnabled(false);
                    editTextMessage.setClickable(false);
                    editTextMessage.setEnabled(false);
                    editTextMessage.setFocusable(false);
                    editTextMessage.setFocusableInTouchMode(false);

                    new EndAlertDialog(ChatActivity.this)
                            .showEndDialogToActivity("[ 대화명중복 ]", "대화명 재입력하세요", LoginActivity.class);
                }

                //scrollView.scrollTo(0, scrollView.getHeight());
                //scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
                editTextMessage.setText("");
            }

            if (message.what == 500) {
                String endMessage = (String) message.obj;
                //textViewContent.append(endMessage+"\n");
                append(endMessage);
                //scrollView.scrollTo(0, scrollView.getHeight());
                //scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
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

        this.clientName = intent.getStringExtra("clientName");
        System.out.println(getClass().getSimpleName()+"::대화명:: " + clientName);

        //this.textViewContent = (TextView) findViewById(R.id.textView_content);
        this.scrollView = (ScrollView) findViewById(R.id.scrollview);
        this.messageInLayout = (LinearLayout) findViewById(R.id.message_in_layout);
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
                        chatClientSocketThread.sendMessageToServer("200:" + editTextMessage.getText());
                    }
                }.start();
            }
        });
    }

    public void append(String message){
        LinearLayout messageLayout = (LinearLayout) View.inflate(this, R.layout.message, null);

        messageInLayout.addView(messageLayout);

        if(message.indexOf(clientName) == -1){
            ((TextView) (messageLayout.findViewById(R.id.left_message))).setText(message);
        }else{
            ((TextView) (messageLayout.findViewById(R.id.right_message))).setText(message);
        }
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

    @Override
    public void onBackPressed() {
        new EndAlertDialog(this).showEndDialog();
    }
}