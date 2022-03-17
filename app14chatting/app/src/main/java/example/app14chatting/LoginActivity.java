package example.app14chatting;

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

import example.app14chatting.util.EndToast;

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

    EndToast endToast = new EndToast(this);

    @Override
    public void onBackPressed() {
        endToast.showEndToast("'취소' 버튼 한번 더 누르시면 종료합니다.");
    }
}// end of Activity