package com.example.backtothefuture_project;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Snake {
    private boolean move_left, move_rigth, move_top, move_botom;
    private Bitmap bm, bm_head_up, bm_head_down, bm_head_left, bm_head_right, bm_body_vertical, bm_body_horizontal, bm_body_top_right, bm_body_top_left, bm_body_bottom_right, bm_body_bottom_left,
            bm_tail_right, bm_tail_left, bm_tail_up, bm_tail_down;

    private int x, y, length;
    private ArrayList<SnakePart> snakePartList = new ArrayList<>();
    // ideia da criação: https://github.com/mdibaiee/super-snake
    public Snake(Bitmap bm, int x, int y, int length){
        this.bm = bm;
        this.x = x;
        this.y = y;
        this.length = length;
        bm_body_bottom_left = Bitmap.createBitmap(bm, 0, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_body_bottom_right = Bitmap.createBitmap(bm, GameView.sizeOfMapa, 0, GameView.sizeOfMapa,GameView.sizeOfMapa);
        bm_body_horizontal = Bitmap.createBitmap(bm, 2*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_body_top_left = Bitmap.createBitmap(bm, 3*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_body_top_right = Bitmap.createBitmap(bm, 4*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_body_vertical = Bitmap.createBitmap(bm, 5*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_head_down = Bitmap.createBitmap(bm, 6*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_head_left = Bitmap.createBitmap(bm, 7*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_head_right = Bitmap.createBitmap(bm, 8*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_head_up = Bitmap.createBitmap(bm, 9*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_tail_up = Bitmap.createBitmap(bm, 10*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_tail_right = Bitmap.createBitmap(bm, 11*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_tail_left = Bitmap.createBitmap(bm, 12*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        bm_tail_down = Bitmap.createBitmap(bm, 13*GameView.sizeOfMapa, 0, GameView.sizeOfMapa, GameView.sizeOfMapa);
        snakePartList.add(new SnakePart(bm_head_right,x, y));

        for(int i =1; i< length-1; i++){
            snakePartList.add(new SnakePart(bm_body_horizontal, snakePartList.get(i-1).getX() - GameView.sizeOfMapa, y));
        }
        snakePartList.add(new SnakePart(bm_tail_right, snakePartList.get(length-2).getX() - GameView.sizeOfMapa, y) );
        //inicializar a Snake  movendo para direita
        setMove_rigth(true);
    }
    // ideia de uptade: https://github.com/GiovanniColonni/Snake-AndroidStudio
    //após cada uptade, a posição da parte da cobra e o BITMAP atrás serão iguais aos da frente
    public void update(){
        for(int i= length -1; i> 0; i--){
            snakePartList.get(i).setX(snakePartList.get(i-1).getX());
            snakePartList.get(i).setY(snakePartList.get(i-1).getY());
        }
        //ajuste para o corpo traseiro move ao longo
        if(move_rigth){
            snakePartList.get(0).setX(snakePartList.get(0).getX()+GameView.sizeOfMapa);
            snakePartList.get(0).setBm(bm_head_right);
        }else if(move_left){
            snakePartList.get(0).setX(snakePartList.get(0).getX()-GameView.sizeOfMapa);
            snakePartList.get(0).setBm(bm_head_left);
        }else if(move_top){
            snakePartList.get(0).setY(snakePartList.get(0).getY()-GameView.sizeOfMapa);
            snakePartList.get(0).setBm(bm_head_up);
        }else if(move_botom){
            snakePartList.get(0).setY(snakePartList.get(0).getY()+GameView.sizeOfMapa);
            snakePartList.get(0).setBm(bm_head_down);
        }
        //Quando a cobra girar, para o corpo acompanhar o movimento.
        for(int i=1; i < length-1;i++){

            if(snakePartList.get(i).getrLeft().intersect(snakePartList.get(i+1).getrBody()) && snakePartList.get(i).getrBottom().intersect(snakePartList.get(i-1).getrBody())
            || snakePartList.get(i).getrLeft().intersect(snakePartList.get(i-1).getrBody()) && snakePartList.get(i).getrBottom().intersect(snakePartList.get(i+1).getrBody()) ){
                //Sudeste
                snakePartList.get(i).setBm(bm_body_bottom_left);
            }else if(snakePartList.get(i).getrRight().intersect(snakePartList.get(i+1).getrBody()) && snakePartList.get(i).getrBottom().intersect(snakePartList.get(i-1).getrBody())
                    || snakePartList.get(i).getrRight().intersect(snakePartList.get(i-1).getrBody()) && snakePartList.get(i).getrBottom().intersect(snakePartList.get(i+1).getrBody())){
                //Sudoeste
                snakePartList.get(i).setBm(bm_body_bottom_right);
            }else if(snakePartList.get(i).getrLeft().intersect(snakePartList.get(i+1).getrBody()) && snakePartList.get(i).getrTop().intersect(snakePartList.get(i-1).getrBody())
                    || snakePartList.get(i).getrLeft().intersect(snakePartList.get(i-1).getrBody()) && snakePartList.get(i).getrTop().intersect(snakePartList.get(i+1).getrBody())){
                //Noroeste
                snakePartList.get(i).setBm(bm_body_top_left);
            }else if(snakePartList.get(i).getrRight().intersect(snakePartList.get(i+1).getrBody()) && snakePartList.get(i).getrTop().intersect(snakePartList.get(i-1).getrBody())
                    || snakePartList.get(i).getrRight().intersect(snakePartList.get(i-1).getrBody()) && snakePartList.get(i).getrTop().intersect(snakePartList.get(i+1).getrBody())){
                //Nordeste
                snakePartList.get(i).setBm(bm_body_top_right);
            }else if(snakePartList.get(i).getrTop().intersect(snakePartList.get(i+1).getrBody()) && snakePartList.get(i).getrBottom().intersect(snakePartList.get(i-1).getrBody())
                    || snakePartList.get(i).getrTop().intersect(snakePartList.get(i-1).getrBody()) && snakePartList.get(i).getrBottom().intersect(snakePartList.get(i+1).getrBody())){
                //
                snakePartList.get(i).setBm(bm_body_vertical);
            }else if(snakePartList.get(i).getrLeft().intersect(snakePartList.get(i+1).getrBody()) && snakePartList.get(i).getrRight().intersect(snakePartList.get(i-1).getrBody())
                    || snakePartList.get(i).getrLeft().intersect(snakePartList.get(i-1).getrBody()) && snakePartList.get(i).getrRight().intersect(snakePartList.get(i+1).getrBody())){
                //
                snakePartList.get(i).setBm(bm_body_horizontal);
            }
        }

        if(snakePartList.get(length-1).getrRight().intersect(snakePartList.get(length-2).getrBody()) ){
            snakePartList.get(length-1).setBm(bm_tail_right);
        }else if(snakePartList.get(length-1).getrLeft().intersect(snakePartList.get(length-2).getrBody()) ) {
            snakePartList.get(length - 1).setBm(bm_tail_left);
        }else if(snakePartList.get(length-1).getrTop().intersect(snakePartList.get(length-2).getrBody()) ) {
            snakePartList.get(length - 1).setBm(bm_tail_up);
        }else if(snakePartList.get(length-1).getrBottom().intersect(snakePartList.get(length-2).getrBody()) ) {
            snakePartList.get(length - 1).setBm(bm_tail_down);
        }

    }

    public void draw(Canvas canvas){
        for(int i=0; i<length;i++){
            canvas.drawBitmap(snakePartList.get(i).getBm(), snakePartList.get(i).getX(), snakePartList.get(i).getY(), null);
        }
    }

    public Bitmap getBm() {
        return bm;
    }
    public void setBm(Bitmap bm) {
        this.bm = bm;
    }
    public Bitmap getBm_head_up() {
        return bm_head_up;
    }
    public void setBm_head_up(Bitmap bm_head_up) {
        this.bm_head_up = bm_head_up;
    }
    public Bitmap getBm_head_down() {
        return bm_head_down;
    }
    public void setBm_head_down(Bitmap bm_head_down) {
        this.bm_head_down = bm_head_down;
    }
    public Bitmap getBm_head_left() {
        return bm_head_left;
    }

    public void setBm_head_left(Bitmap bm_head_left) {
        this.bm_head_left = bm_head_left;
    }

    public Bitmap getBm_head_right() {
        return bm_head_right;
    }

    public void setBm_head_right(Bitmap bm_head_right) {
        this.bm_head_right = bm_head_right;
    }

    public Bitmap getBm_body_vertical() {
        return bm_body_vertical;
    }

    public void setBm_body_vertical(Bitmap bm_body_vertical) {
        this.bm_body_vertical = bm_body_vertical;
    }

    public Bitmap getBm_body_horizontal() {
        return bm_body_horizontal;
    }

    public void setBm_body_horizontal(Bitmap bm_body_horizontal) {
        this.bm_body_horizontal = bm_body_horizontal;
    }

    public Bitmap getBm_body_top_right() {
        return bm_body_top_right;
    }

    public void setBm_body_top_right(Bitmap bm_body_top_right) {
        this.bm_body_top_right = bm_body_top_right;
    }

    public Bitmap getBm_body_top_left() {
        return bm_body_top_left;
    }

    public void setBm_body_top_left(Bitmap bm_body_top_left) {
        this.bm_body_top_left = bm_body_top_left;
    }

    public Bitmap getBm_body_bottom_right() {
        return bm_body_bottom_right;
    }

    public void setBm_body_bottom_right(Bitmap bm_body_bottom_right) {
        this.bm_body_bottom_right = bm_body_bottom_right;
    }

    public Bitmap getBm_body_bottom_left() {
        return bm_body_bottom_left;
    }

    public void setBm_body_bottom_left(Bitmap bm_body_bottom_left) {
        this.bm_body_bottom_left = bm_body_bottom_left;
    }

    public Bitmap getBm_tail_right() {
        return bm_tail_right;
    }

    public void setBm_tail_right(Bitmap bm_tail_right) {
        this.bm_tail_right = bm_tail_right;
    }

    public Bitmap getBm_tail_left() {
        return bm_tail_left;
    }

    public void setBm_tail_left(Bitmap bm_tail_left) {
        this.bm_tail_left = bm_tail_left;
    }

    public Bitmap getBm_tail_up() {
        return bm_tail_up;
    }

    public void setBm_tail_up(Bitmap bm_tail_up) {
        this.bm_tail_up = bm_tail_up;
    }

    public Bitmap getBm_tail_down() {
        return bm_tail_down;
    }

    public void setBm_tail_down(Bitmap bm_tail_down) {
        this.bm_tail_down = bm_tail_down;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public ArrayList<SnakePart> getSnakePartList() {
        return snakePartList;
    }
    public void setSnakePartList(ArrayList<SnakePart> snakePartList) {
        this.snakePartList = snakePartList;
    }

    public boolean isMove_left() {
        return move_left;
    }
    public void setMove_left(boolean move_left) {
        move_s();
        this.move_left = move_left;
    }
    public boolean isMove_rigth() {
        return move_rigth;
    }
    public void setMove_rigth(boolean move_rigth) {
        move_s();
        this.move_rigth = move_rigth;
    }
    public boolean isMove_top() {
        return move_top;
    }
    public void setMove_top(boolean move_top) {
        move_s();
        this.move_top = move_top;
    }
    public boolean isMove_botom() {
        return move_botom;
    }
    public void setMove_botom(boolean move_botom) {
        move_s();
        this.move_botom = move_botom;
    }
    public void move_s(){
        this.move_left = false;
        this.move_rigth = false;
        this.move_botom = false;
        this.move_top = false;
    }

    public void addPart() {
        SnakePart sn = this.snakePartList.get(length-1);
        this.length += 1;
        if(sn.getBm() == bm_tail_right){
            this.snakePartList.add(new SnakePart(bm_tail_right, sn.getX() - GameView.sizeOfMapa, sn.getY() ) );
        } else if(sn.getBm() == bm_tail_left){
            this.snakePartList.add(new SnakePart(bm_tail_left, sn.getX() + GameView.sizeOfMapa, sn.getY() ) );
        } else if(sn.getBm() == bm_tail_up){
            this.snakePartList.add(new SnakePart(bm_tail_up, sn.getX(), sn.getY() + GameView.sizeOfMapa ) );
        } else if(sn.getBm() == bm_tail_down){
            this.snakePartList.add(new SnakePart(bm_tail_down, sn.getX() , sn.getY() - GameView.sizeOfMapa ) );
        }
    }
}
