package com.example.android.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //0 for yellow and 1 for red and 2 for empty

    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winingPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive=true;


    public void dropIn(View view){

        ImageView counter=(ImageView)view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        //check already click or not click
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winingPosition : winingPositions) {
                if (gameState[winingPosition[0]] == gameState[winingPosition[1]] && gameState[winingPosition[1]] == gameState[winingPosition[2]] && gameState[winingPosition[0]] != 2) {
                    //someone has won
                    gameActive=false;
                    String winner;
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button restartGame=(Button)findViewById(R.id.restartButton);
                    TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!");
                    restartGame.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    public void restartGame(View view){

        Button restartGame=(Button)findViewById(R.id.restartButton);
        TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
        restartGame.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);

        //using loop for through all objects in layout
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        //set default value in gameSate array using loop
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
         activePlayer=0;
         gameActive=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
