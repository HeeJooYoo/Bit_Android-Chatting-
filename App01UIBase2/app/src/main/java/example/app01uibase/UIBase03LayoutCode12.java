package example.app01uibase;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UIBase03LayoutCode12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///////////////////////////////////////////////////////

        /*LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        textView.setText("환영합니다");
        //textView.setText(R.string.greeting);
        textView.setTextColor(Color.RED);
        textView.setBackgroundColor(Color.BLUE);
        textView.setTextSize(20);

        linearLayout.addView(textView);

        this.setContentView(linearLayout);*/

        ///////////////////////////////////////////////////////

        /*this.setContentView(R.layout.activity_uibaselayout12);*/

        ///////////////////////////////////////////////////////

        /*LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.activity_uibaselayout12, null);
        setContentView(linearLayout);*/

        ////////////////////////////////////////////////////////

        /*LayoutInflater layoutInflater = LayoutInflater.from(this);
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.activity_uibaselayout12, null);
        setContentView(linearLayout);*/

        ////////////////////////////////////////////////////////

        LinearLayout linearLayout = (LinearLayout) View.inflate(this, R.layout.activity_uibaselayout12, null);
        setContentView(linearLayout);
    }
}