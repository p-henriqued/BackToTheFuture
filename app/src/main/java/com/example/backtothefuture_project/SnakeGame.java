package com.example.backtothefuture_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class SnakeGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Variáveis receberem a informações sobre a tela de exibição.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constantes.SCREEN_WIDTH = displayMetrics.widthPixels;
        Constantes.SCREEN_HEIGHT = displayMetrics.heightPixels;

        setContentView(R.layout.activity_snake_game);

    }

}