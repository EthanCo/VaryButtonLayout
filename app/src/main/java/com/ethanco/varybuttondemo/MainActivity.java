package com.ethanco.varybuttondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ethanco.varybuttonlayout.VaryButtonLayout;

public class MainActivity extends AppCompatActivity {

    private VaryButtonLayout varyButton1;
    private VaryButtonLayout varyButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varyButton1 = (VaryButtonLayout) findViewById(R.id.varyButton1);
        varyButton1.setOnVaryClickListener(new VaryButtonLayout.OnVaryClickListener() {
            @Override
            public void onClick(View v, int currIndex, int nextIndex) {
                Toast.makeText(getApplicationContext(), "currIndex:" + currIndex + " nextIndex" + nextIndex, Toast.LENGTH_SHORT).show();
            }
        });
        //varyButton1.addStatusView(R.layout.status_item); //使用Java代码添加状态

        varyButton2 = (VaryButtonLayout) findViewById(R.id.varyButton2);
        varyButton2.setCurrSatus(2); //设置当前所处状态
    }
}
