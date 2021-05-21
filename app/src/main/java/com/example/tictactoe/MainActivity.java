package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int user = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};

    private Button reset;

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset (view);
        }
        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = user;
            img.setTranslationY(-1000f);
            if(user == 0){
                img.setImageResource(R.drawable.x);
                user = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Player 2 Turn");
            }
            else{
                img.setImageResource(R.drawable.o);
                user = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Player 1 Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for (int[] winPosition: winPositions){
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0){
                    winnerStr = " Player 1 has Won The Game.";
                }
                else {
                    winnerStr = " Player 2 has Won The Game.";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }

        }

    }

    private void gameReset(View view) {
        gameActive = true;
        user = 0;
        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ImageResource();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button reset = findViewById(R.id.clear);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameActive = true;
                user = 0;
                for (int i=0; i<gameState.length; i++){
                    gameState[i] = 2;
                }
                ImageResource();
            }
        });
    }

    public void ImageResource(){
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Player 1 Turn");
    }
}