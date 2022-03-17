package example.app03activityintent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Intent03LifeCycleBundle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_second);

        /*if(savedInstanceState != null){
            EditText editText = (EditText) this.findViewById(R.id.edittext);
            editText.setBackgroundColor(Color.YELLOW);
            String value = savedInstanceState.getString("endTime");
            editText.setText(value);
        }*/

        System.out.println("===>"+getClass().getSimpleName()+".onCreate()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Calendar calendar = Calendar.getInstance();
        String value = calendar.get(Calendar.HOUR_OF_DAY) + " 시" + calendar.get(Calendar.MINUTE) + " 분" + calendar.get(Calendar.SECOND) + " 초에 종료되었음";

        System.out.println(":: onSaveInstanceState : " + value);

        outState.putString("endTime", value);

        System.out.println("===>"+getClass().getSimpleName()+".onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        EditText editText = (EditText) this.findViewById(R.id.edittext);
        editText.setBackgroundColor(Color.YELLOW);

        String value = savedInstanceState.getString("endTime");
        System.out.println(":: onRestoreInstanceState : " + value);

        editText.setText(value);
        System.out.println("==>"+getClass().getSimpleName()+".onRestoreInstanceState()");
    }

    public void xmlOnClick(View v){
        Intent intent = new Intent(this, Intent02SecondActivityLifeCycle.class);

        startActivity(intent);
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