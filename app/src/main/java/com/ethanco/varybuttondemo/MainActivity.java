package com.ethanco.varybuttondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private VaryButtonLayout varyButton1;
    private VaryButtonLayout varyButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varyButton1 = (VaryButtonLayout) findViewById(R.id.varyButton1);
        varyButton1.setOnVarayClickListener(new VaryButtonLayout.OnVaryClickListener() { // 回调监听
            @Override
            public void onClick(View v, int currIndex) {
                Toast.makeText(getApplicationContext(), "currIndex:" + currIndex, Toast.LENGTH_SHORT).show();
            }
        });

        varyButton2 = (VaryButtonLayout) findViewById(R.id.varyButton2);
        varyButton2.setCurrSatus(2); //设置当前所处状态
    }
}
