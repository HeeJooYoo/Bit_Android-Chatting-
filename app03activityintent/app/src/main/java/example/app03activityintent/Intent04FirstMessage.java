package example.app03activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Intent04FirstMessage extends AppCompatActivity {

    ///Field
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_first);

        this.editText = (EditText) findViewById(R.id.edittext);

        System.out.println(this.getClass().getSimpleName()+".onCreate");
    }

    public void xmlOnClick(View v){
        Intent intent = new Intent(this, Intent04SecondMessage.class);

        intent.putExtra("message01", editText.getText().toString());

        startActivity(intent);
    }
}