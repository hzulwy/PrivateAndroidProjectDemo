package com.example.materialdesignimmerse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends BaseTranslucentActivity {
    private Toolbar toolbar;
    private View nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        setOrChangeTranslucentColor(toolbar, nav, getResources().getColor(R.color.colorPrimary_pink));
    }
}