package example.app03activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Intent02Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_second);
    }

    public void xmlOnClick(View v){

        if(((Button)v).getText().equals("Close")){
            this.finish();
            return;
        }

        Intent intent = new Intent(this, Intent02Third.class);

        startActivity(intent);
        // 3번 부르고 바로 종료 주석풀면 3번에서 Close하면 1번으로 감
        //finish();
    }
}