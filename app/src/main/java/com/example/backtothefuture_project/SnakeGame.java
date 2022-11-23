package com.example.backtothefuture_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SnakeGame extends AppCompatActivity {
    private GameView gameView;
    public static Dialog dialogScore;
    public static ImageView img_touch;
    public static TextView txtscore, txt_bestscore, txt_startScore, txt_startBestScore;
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

        img_touch = findViewById(R.id.img_touch);
        gameView = findViewById(R.id.gameView);
        txtscore = findViewById(R.id.txtscore);
        txt_bestscore = findViewById(R.id.txt_bestscore);
        scores();
    }
    private void scores(){
        int bestScore = 0;
        SharedPreferences sp = this.getSharedPreferences("gameconfigs", Context.MODE_PRIVATE);
        if(sp != null){
            bestScore = sp.getInt("BestScore",0);
        }
        SnakeGame.txt_bestscore.setText(bestScore+"");
        dialogScore = new Dialog(this);
        dialogScore.setContentView(R.layout.dialog_start);
        txt_startScore = dialogScore.findViewById(R.id.txt_dialog_score);
        txt_startBestScore = dialogScore.findViewById(R.id.txt_dialog_Bestscore);
        txt_startBestScore.setText(bestScore+"");
        dialogScore.setCanceledOnTouchOutside(false);
        RelativeLayout rl_start = dialogScore.findViewById(R.id.rl_start);
        rl_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_touch.setVisibility(View.VISIBLE);
                gameView.reset();
                dialogScore.dismiss();
            }
        });
        dialogScore.show();
    }

}