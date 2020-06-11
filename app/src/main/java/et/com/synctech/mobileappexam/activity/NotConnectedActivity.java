package et.com.synctech.mobileappexam.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import et.com.synctech.mobileappexam.R;

public class NotConnectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_connected);
    }

    public void getBackToPrevious(View view) {
        super.onBackPressed();
    }
}
