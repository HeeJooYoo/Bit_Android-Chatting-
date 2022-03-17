package example.app02event;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import example.app02event.custom.widget.EventTextView;

public class Event12OnLongClickListenerETC extends AppCompatActivity {

    ///Field
    private Button button01;
    private Button button02;
    private Button button03;
    private EventTextView eventTextView;
    private android.os.Vibrator vibrator;

    private Button.OnClickListener onClickListener =  new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(v == button01){
                eventTextView.setTextSize(20);
            }else if( v == button02 ){
                eventTextView.setTextSize(40);
            }
            eventTextView.setText(((Button)v).getText() + " 가 OnClick 됨");

        }
    };

    ///Constructor

    ///Method
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_ui_etc);

        this.button01 = (Button) findViewById(R.id.button01);
        this.button02 = (Button) this.findViewById(R.id.button02);
        this.button03 = (Button) findViewById(R.id.button03);
        this.eventTextView = (EventTextView) findViewById(R.id.eventTextView);

        button01.setOnClickListener(onClickListener);
        button02.setOnClickListener(onClickListener);

        button03.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(v.getContext(), "01 : 화면이 눌렸습니다. / DOWN", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(Event12OnLongClickListenerETC.this, "03: 화면에서 뗏습니다. / UP", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });//end of anonymous

        eventTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Event12OnLongClickListenerETC.this.vibrator = (Vibrator) Event12OnLongClickListenerETC.this.getSystemService(Context.VIBRATOR_SERVICE);

                vibrator.vibrate(1000);

                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vibrator.cancel();
    }

    public void xmlOnClick(View v){
        //EventTextView eventTextView = (EventTextView) findViewById(R.id.eventTextView);
        eventTextView.setTextSize(60);
        eventTextView.setText(((Button)v).getText() + "가 OnClick 됨");
    }
}