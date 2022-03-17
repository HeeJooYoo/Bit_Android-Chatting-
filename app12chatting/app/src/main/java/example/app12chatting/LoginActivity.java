package example.app12chatting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    ///Field
    private Button buttonEnter;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        this.buttonEnter = (Button) findViewById(R.id.button_enter);
        this.editTextName = (EditText) findViewById(R.id.editText_name);

        buttonEnter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ChatActivity.class);

                intent.putExtra("clientName", editTextName.getText().toString());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(getClass().getSimpleName()+"LogonAction.onPause()");
        this.finish();
    }


    //[경우1] onBackPress() + Toast
    /*private long re_PressLimitTime = 2000;
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
    }*/

    //[경우2] onBackPressed() + AlertDialog 사용
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);

        alertDialog.setTitle("종료 확인");
        alertDialog.setMessage("종료하시겠습니까?");

        alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alertDialog.show();
    }

    //[경우3] onKeyDown() + Toast
    /*private long re_PressLimitTime = 2000;
    private long firstPressedTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            long tempTime = System.currentTimeMillis();
            long re_PressIntervalTime = tempTime - firstPressedTime;

            if(0 <= re_PressIntervalTime && re_PressLimitTime >= re_PressIntervalTime){
                super.onBackPressed();
            }else{
                firstPressedTime = tempTime;

                Toast.makeText(this, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }*/

    //[경우3] onKeyDown() + AlertDialog
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("종료확인")
                    .setMessage("종료하시겠습니까?")
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("아니오", null).show();
        }
        return true;
    }*/
}// end of Activity



















