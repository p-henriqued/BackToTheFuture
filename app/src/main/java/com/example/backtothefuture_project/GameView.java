package com.example.backtothefuture_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {
    private Bitmap bmSpace, bmSpace2, bmSnake;
    public static int sizeOfMapa = 75*Constantes.SCREEN_WIDTH/1080;
    private int h = 21;
    private int w = 12;
    private ArrayList<SpaceBackground> spaceList = new ArrayList<>();
    private Snake snake;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bmSpace = BitmapFactory.decodeResource(this.getResources(),R.drawable.backgroundgame);
        bmSpace = Bitmap.createScaledBitmap(bmSpace,sizeOfMapa,sizeOfMapa, true );
        bmSpace2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.backgroundgame2);
        bmSpace2 = Bitmap.createScaledBitmap(bmSpace,sizeOfMapa,sizeOfMapa, true );

        bmSnake = BitmapFactory.decodeResource(this.getResources(),R.drawable.snake);
        bmSnake = Bitmap.createScaledBitmap(bmSnake,14*sizeOfMapa,sizeOfMapa, true );

        for(int i=0; i<h; i++){
            for(int j=0; j<w;j++){
                if( (i+j)%2 == 0 ){
                    spaceList.add(new SpaceBackground(bmSpace, j*sizeOfMapa + Constantes.SCREEN_WIDTH/2 - (w/2) * sizeOfMapa,
                            i*sizeOfMapa+100*Constantes.SCREEN_HEIGHT/1920, sizeOfMapa, sizeOfMapa));
                }else{
                    spaceList.add(new SpaceBackground(bmSpace2, j*sizeOfMapa + Constantes.SCREEN_WIDTH/2 - (w/2) * sizeOfMapa,
                            i*sizeOfMapa+100*Constantes.SCREEN_HEIGHT/1920, sizeOfMapa, sizeOfMapa));
                }
            }
        }

        snake = new Snake(bmSnake, spaceList.get(126).getX(), spaceList.get(126).getY(),4);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFF040953);

        for(int i=0; i<spaceList.size();i++){
            canvas.drawBitmap(spaceList.get(i).getBm(), spaceList.get(i).getX(), spaceList.get(i).getY(), null);
        }
        snake.draw(canvas);
    }
}
