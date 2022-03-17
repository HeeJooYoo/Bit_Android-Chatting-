package example.app03activityintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Intent01AllUI extends AppCompatActivity {

    ///Field
    private ViewGroup page1;
    private ViewGroup page2;

    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button_page1:
                    page2.setVisibility(View.INVISIBLE);
                    page1.setVisibility(View.VISIBLE);
                    break;
                case R.id.button_page2:
                    page1.setVisibility(View.INVISIBLE);
                    page2.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent01_all_ui);

        page1 = (ViewGroup) this.findViewById(R.id.page1);
        page2 = (ViewGroup) this.findViewById(R.id.page2);

        page1.setVisibility(View.INVISIBLE);

        this.findViewById(R.id.button_page1).setOnClickListener(onClickListener);
        this.findViewById(R.id.button_page2).setOnClickListener(onClickListener);
    }
}