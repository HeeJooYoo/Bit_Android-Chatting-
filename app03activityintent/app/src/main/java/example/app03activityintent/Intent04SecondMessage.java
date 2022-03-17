package example.app03activityintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Intent04SecondMessage extends AppCompatActivity {

    ///Field
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_second);

        this.editText = (EditText) findViewById(R.id.edittext);

        Intent intent = this.getIntent();

        String message = intent.getStringExtra("message01");
        if(message != null){
            editText.setText(message);
        }

        System.out.println(this.getClass().getSimpleName()+".onCreate()");
    }

    public void xmlOnClick(View v){

        if(((Button)v).getText().equals("Close")){
            this.finish();
            return;
        }

        Intent intent = new Intent(this, Intent04ThirdMessage.class);

        intent.putExtra("message02", editText.getText().toString());

        //startActivity(intent);
        // 3번 부르고 바로 종료 주석풀면 3번에서 Close하면 1번으로 감
        //finish();

        startActivityForResult(intent, 1004);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("==> requestCode " + requestCode);
        System.out.println("==> resultCode " + resultCode);
        System.out.println("==> data " + data);

        switch(requestCode){
            case 1004:
                String message = "리턴정보가 없습니다.";
                if(data != null){
                    message = data.getStringExtra("returnMessage");
                }

                if(resultCode == Activity.RESULT_OK){
                    editText.setText("Activity.RESULT_OK = " + resultCode + " : " + message);
                }else if(resultCode == Activity.RESULT_CANCELED){
                    editText.setText("Activity.RESULT_CANCELED = " + resultCode + " : " + message);
                }
                break;
            case 1005:
                break;

            default:
        }
    }
}