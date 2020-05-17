package com.sjq.fix3;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class TesstActivity extends AppCompatActivity {
    private TextView tvShow;
    private Button btUse;
    private Button btFix;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = (TextView) findViewById(R.id.sample_text);
        btUse = (Button) findViewById(R.id.bt_use_error);
        btFix = (Button) findViewById(R.id.bt_fix_error);
        btUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Caculator caculator = new Caculator();
                int sum = caculator.caculator();
                tvShow.setText(sum+"");
            }
        });
        btFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fix();
            }
        });

    }

    public void fix() {
        File file = new File(Environment.getExternalStorageDirectory(),"fix.dex");
        DexManager dexManager = new DexManager(TesstActivity.this);
        dexManager.loadFile(file);

    }
}
