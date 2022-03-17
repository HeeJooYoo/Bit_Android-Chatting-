package example.app04thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

public class Thread01UI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        /*<activity android:name=".Thread01UI"
                android:theme="@style/Theme.AppCompat.NoActionBar">*/

        setContentView(R.layout.thread01ui);
    }
}