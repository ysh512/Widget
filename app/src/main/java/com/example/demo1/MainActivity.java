package com.example.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CustomView mCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomView = (CustomView)findViewById(R.id.cv_my);

        mCustomView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        mCustomView.randomText();
       v.postInvalidate();
    }
}
