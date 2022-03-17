package example.app11chatting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import example.app11chatting.thread.ChatClientSocketThread;

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
                String fromHostMessage = (String) message.obj;
                textViewContent.append(fromHostMessage + "\n");
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

        this.textViewContent = (TextView) findViewById(R.id.textView_content);
        this.scrollView = (ScrollView) findViewById(R.id.scrollview);
        this.buttonSend = (Button) findViewById(R.id.button_send);
        this.editTextMessage = (EditText) findViewById(R.id.edittext_message);

        this.chatClientSocketThread = new ChatClientSocketThread(handler);
        chatClientSocketThread.start();

        buttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //chatClientSocketThread.sendMessageToServer(editTextMessage.getText() + "");
                new Thread(){
                    @Override
                    public void run() {
                        chatClientSocketThread.sendMessageToServer(editTextMessage.getText() + "");
                    }
                }.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(getClass().getSimpleName()+".onDestroy()");

        if(chatClientSocketThread != null){
            chatClientSocketThread.onDestroy();
        }
    }
}