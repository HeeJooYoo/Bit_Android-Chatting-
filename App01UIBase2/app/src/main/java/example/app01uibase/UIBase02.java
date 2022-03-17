package example.app01uibase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UIBase02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uibase02);

        EditText editText = (EditText) findViewById(R.id.edit_text);
        System.out.println("입력된 내용 : " + editText.getText());

        Button buttonInputOk = (Button) this.findViewById(R.id.button_input_ok);
        System.out.println("버튼이름 : " + buttonInputOk.getText());
    }
}