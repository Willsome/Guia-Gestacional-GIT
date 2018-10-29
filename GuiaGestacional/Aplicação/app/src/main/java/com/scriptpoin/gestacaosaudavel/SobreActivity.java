package com.scriptpoin.gestacaosaudavel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        /*ImageView sobre_logo = (ImageView) findViewById(R.id.sobre_logo);
        sobre_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SobreActivity.this.deleteDatabase("cadernetaDB");
                SobreActivity.this.deleteDatabase("consultasDB");
                SobreActivity.this.deleteDatabase("duvidasDB");
            }
        });*/
    }
}
