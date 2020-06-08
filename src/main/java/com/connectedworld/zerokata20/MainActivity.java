package com.connectedworld.zerokata20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[][] gameState = new int[][]{{-1,-1,-1},
                                    {-1,-1,-1},
                                    {-1,-1,-1}};
    int activePlayer = 1;
    int totalMatch = 0,p1Score=0,p2Score=0;
    int moves = 0;
    int lastWinner=-1;

    public void reset(View view){
        reset();
    }
    void reset(){
        gameState = new int[][]{{-1,-1,-1},
                                {-1,-1,-1},
                                {-1,-1,-1}};
        totalMatch=0;p1Score=0;p2Score=0;
        TextView score;
        score = findViewById(R.id.Match);
        score.setText(String.valueOf(0));
        score = findViewById(R.id.P2Score);
        score.setText(String.valueOf(0));
        score = findViewById(R.id.P1Score);
        score.setText(String.valueOf(0));
        clearBoard();
    }
    public void restart(View view){
        clearBoard();
    }

    public void place(View view){
        ImageView image = (ImageView) view;
        int pos = Integer.parseInt(image.getTag().toString());
        int col=pos%10,row=pos/10;
        if(lastWinner != -1){
            activePlayer = lastWinner;
            lastWinner = -1;
        }
        if(moves<9){
            if(gameState[row][col] == -1){
                if(activePlayer%2 != 0){    //  activePlayer == 1
                    image.setImageResource(R.drawable.zero);
                    gameState[row][col] = 1;
                }else{  //activePlayer == 2
                    image.setImageResource(R.drawable.cross);
                    gameState[row][col] = 2;
                }
                // Winner gets to start first??
                activePlayer = ((activePlayer)%2)+1;
                check();
                moves++;
            }else{
                Toast.makeText(this, "Place somewhere else.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Match Draw", Toast.LENGTH_LONG).show();
            setScore(-1);
            clearBoard();
        }
    }

    void check(){
        int winner = -1;
                //      Checking Rows
        if(gameState[0][0] == gameState[0][1] && gameState[0][0] == gameState[0][2] && gameState[0][0] != -1){
            if(gameState[0][0] == 2){
                winner = 2;
            }else{
                winner = 1;
            }
        }else if(gameState[1][0] == gameState[1][1] && gameState[1][0] == gameState[1][2] && gameState[1][0] != -1){
            if(gameState[1][0] == 2){
                winner = 2;
            }else{
                winner = 1;
            }
        }else if(gameState[2][0] == gameState[2][1] && gameState[2][0] == gameState[2][2] && gameState[2][0] != -1){
            if(gameState[2][0] == 2){
                winner = 2;
            }else{
                winner = 1;
            }
        }
                //      Checking Columns
        else if(gameState[0][0] == gameState[1][0] && gameState[0][0] == gameState[2][0] && gameState[0][0] != -1){
            if(gameState[0][0] == 2){
                winner = 2;
            }else{
                winner = 1;
            }
        }else if(gameState[0][1] == gameState[1][1] && gameState[0][1] == gameState[2][1] && gameState[0][1] != -1){
            if(gameState[0][1] == 2){
                winner = 2;
            }else{
                winner = 1;
            }
        }else if(gameState[0][2] == gameState[1][2] && gameState[0][2] == gameState[2][2] && gameState[0][2] != -1){
            if(gameState[0][2] == 2){
                winner = 2;
            }else{
                winner = 1;
            }
        }
                //      Checking Diagonals
        else if(gameState[0][0] == gameState[1][1] && gameState[0][0] == gameState[2][2] && gameState[0][0] !=-1){
            if(gameState[1][1] == 2){
                winner = 2;
            }else{
                winner = 1;
            }
        }else if(gameState[0][2] == gameState[1][1] && gameState[2][0] == gameState[0][2] && gameState[0][2] !=-1){
            if(gameState[1][1] == 2){
                winner = 2;
            }else{
                winner = 1;
            }
        }

        if(winner != -1){
            EditText playerName = null;
            if(winner == 1){
                playerName = findViewById(R.id.P1);
            }else if(winner == 2){
                playerName = findViewById(R.id.P2);
            }
//            System.out.println(playerName.getText()+" Won");
            setScore(winner);
            Toast.makeText(this, "Winner : "+playerName.getText(), Toast.LENGTH_LONG).show();
            clearBoard();
            lastWinner = winner;
        }
    }

    void setScore(int winner){
        TextView score;
        totalMatch++;
        score = findViewById(R.id.Match);
        score.setText(String.valueOf(totalMatch));
        if(winner == 1){
            p1Score++;
            score = findViewById(R.id.P1Score);
            score.setText(String.valueOf(p1Score));
        }else if(winner == 2){
            p2Score++;
            score = findViewById(R.id.P2Score);
            score.setText(String.valueOf(p2Score));
        }
    }
    void clearBoard(){
        moves = 0;
        gameState = new int[][]{{-1,-1,-1},
                                {-1,-1,-1},
                                {-1,-1,-1}};
        ImageView image;
        image = findViewById(R.id.i0);
        image.setImageResource(R.drawable.white_back);
        image = findViewById(R.id.i1);
        image.setImageResource(R.drawable.white_back);
        image = findViewById(R.id.i2);
        image.setImageResource(R.drawable.white_back);
        image = findViewById(R.id.i3);
        image.setImageResource(R.drawable.white_back);
        image = findViewById(R.id.i4);
        image.setImageResource(R.drawable.white_back);
        image = findViewById(R.id.i5);
        image.setImageResource(R.drawable.white_back);
        image = findViewById(R.id.i6);
        image.setImageResource(R.drawable.white_back);
        image = findViewById(R.id.i7);
        image.setImageResource(R.drawable.white_back);
        image = findViewById(R.id.i8);
        image.setImageResource(R.drawable.white_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset();
    }
}
