package br.com.andersonmatte.calculafreela.atividade;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.andersonmatte.calculafreela.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        //Controla o tempo de exibição da Splash Screen.
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarHome();
            }
        }, 2000);
    }

    //Chama a HomeActivity.
    private void mostrarHome() {
        Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

}
