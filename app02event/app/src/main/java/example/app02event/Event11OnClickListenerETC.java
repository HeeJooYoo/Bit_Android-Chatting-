package example.app02event;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import example.app02event.custom.widget.EventTextView;

public class Event11OnClickListenerETC extends AppCompatActivity {

    ///Field
    private Button button01;
    private Button button02;
    private Button button03;
    private EventTextView eventTextView;

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

        /*button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventTextView.setTextSize(20);
                eventTextView.setText(((Button)v).getText() + "가 OnClick 됨");
            }
        });// end of anonymous

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventTextView.setTextSize(40);
                eventTextView.setText(((Button)v).getText() + "가 OnClick 됨");
            }
        });// end of anonymous*/

        button03.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(v.getContext(), "01 : 화면이 눌렸습니다. / DOWN", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(Event11OnClickListenerETC.this, "03: 화면에서 뗏습니다. / UP", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });//end of anonymous
    }

    public void xmlOnClick(View v){
        //EventTextView eventTextView = (EventTextView) findViewById(R.id.eventTextView);
        eventTextView.setTextSize(60);
        eventTextView.setText(((Button)v).getText() + "가 OnClick 됨");
    }
}