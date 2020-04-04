package ir.atitec.testeverythingmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ir.atitec.everythingmanager.utility.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("mainActivity", Util.getPersianDate(System.currentTimeMillis(), true));

    }
}
