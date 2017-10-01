package com.scriptpoin.guiagestacional;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        ImageView sobre_logo = (ImageView) findViewById(R.id.sobre_logo);
        sobre_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SobreActivity.this.deleteDatabase("cadernetaDB");
            }
        });
    }
}
