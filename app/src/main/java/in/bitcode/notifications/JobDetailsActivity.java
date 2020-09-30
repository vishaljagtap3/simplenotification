package in.bitcode.notifications;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JobDetailsActivity extends AppCompatActivity {

    private TextView mTxtInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTxtInfo = new TextView(this);
        mTxtInfo.setText(getIntent().getStringExtra("name"));
        setContentView(mTxtInfo);

    }
}
