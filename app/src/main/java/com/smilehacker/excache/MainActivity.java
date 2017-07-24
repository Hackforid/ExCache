package com.smilehacker.excache;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test("aaa", new Rect(1, 1, 2, 2));
        test("aaa", new Rect(1, 1, 2, 2));
    }

    @ExCache
    private int test(String a, Rect rect) {
        Log.i(TAG, "do test");
        return 1;
    }
}
