package example.app02event.custom.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;


public class EventTextView extends TextView {
    ///Field
    ///Constructor
    public EventTextView(Context context) {
        super(context);
    }

    public EventTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ///Method
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if (this.getText().length() != 0) {
                setText("");
                setBackgroundColor(Color.YELLOW);
            } else {
                setBackgroundColor(Color.GREEN);
                setText("다시 Touch 해보세요.");
                setGravity(Gravity.CENTER);
            }
            return true;
        }
        return false;
    }
}
