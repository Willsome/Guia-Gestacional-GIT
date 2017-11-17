package com.scriptpoin.guiagestacional;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class BoasVindasSplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    private RelativeLayout rlIvSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas_splash_screen);

        rlIvSplashScreen = (RelativeLayout) findViewById(R.id.rlIvSplashScreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BoasVindasSplashScreenActivity.this, MenuLateralPrincipalActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }

        }, SPLASH_TIME_OUT);

    }
}
