package example.app03activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Intent02SecondActivityLifeCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_second);

        System.out.println("===>"+getClass().getSimpleName()+".onCreate()");
    }

    public void xmlOnClick(View v){

        if(((Button)v).getText().equals("Close")){
            this.finish();
            return;
        }

        Intent intent = new Intent(this, Intent02ThirdActivityLifeCycle.class);

        startActivity(intent);
        // 3번 부르고 바로 종료 주석풀면 3번에서 Close하면 1번으로 감
        //finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("===>"+getClass().getSimpleName()+".onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("===>"+getClass().getSimpleName()+".onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("===>"+getClass().getSimpleName()+".onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("===>"+getClass().getSimpleName()+".onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("===>"+getClass().getSimpleName()+".onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("===>"+getClass().getSimpleName()+".onDestroy()");
    }
}