package com.connectedworld.zerokata20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int activePlayer = 1;
    int totalMatch = 0,p1Score=0,p2Score=0;
    int moves = 0;

    public void reset(View view){
        reset();
    }
    void reset(){
        gameState = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        int totalMatch = 0,p1Score=0,p2Score=0;
        TextView score;
        score = findViewById(R.id.Match);
        score.setText(String.valueOf(totalMatch));
        score = findViewById(R.id.P2Score);
        score.setText(String.valueOf(p2Score));
        score = findViewById(R.id.P1Score);
        score.setText(String.valueOf(p1Score));
        clear();
    }
    public void restart(View view){
        clear();
    }

    public void place(View view){
        ImageView image = (ImageView) view;
        int pos = Integer.parseInt(image.getTag().toString());
        if(moves<9){
            if(gameState[pos] == -1){
                if(activePlayer%2 != 0){    //  activePlayer == 1
                    image.setImageResource(R.drawable.zero);
                }else{  //activePlayer == 2
                    image.setImageResource(R.drawable.cross);
                }
                activePlayer = ((activePlayer)%2)+1;
                gameState[pos] = activePlayer;
            }else{
                Toast.makeText(this, "Place somewhere else.", Toast.LENGTH_LONG).show();
            }
            check();
            moves++;
        }else{
            Toast.makeText(this, "Match Draw", Toast.LENGTH_LONG).show();
            setBoard(-1);
            clear();
        }
    }

    void check(){
        byte winner = -1;
                //      Checking Rows
        if(gameState[0] == gameState[1] && gameState[0] == gameState[2] && gameState[0] != -1){
            if(gameState[0] == 0){
                winner = 0;
            }else{
                winner = 1;
            }
        }else if(gameState[3] == gameState[4] && gameState[4] == gameState[5] && gameState[4] != -1){
            if(gameState[4] == 0){
                winner = 0;
            }else{
                winner = 1;
            }
        }else if(gameState[6] == gameState[7] && gameState[7] == gameState[8] && gameState[7] != -1){
            if(gameState[7] == 0){
                winner = 0;
            }else{
                winner = 1;
            }
        }
                //      Checking Columns
        else if(gameState[0] == gameState[3] && gameState[3] == gameState[6] && gameState[3] != -1){
            if(gameState[3] == 0){
                winner = 0;
            }else{
                winner = 1;
            }
        }else if(gameState[1] == gameState[4] && gameState[4] == gameState[7] && gameState[4] != -1){
            if(gameState[4] == 0){
                winner = 0;
            }else{
                winner = 1;
            }
        }else if(gameState[2] == gameState[5] && gameState[5] == gameState[8] && gameState[5] != -1){
            if(gameState[5] == 0){
                winner = 0;
            }else{
                winner = 1;
            }
        }
                //      Checking Diagonals
        else if(gameState[0] == gameState[4] && gameState[4] == gameState[8] && gameState[4] !=-1){
            if(gameState[4] == 0){
                winner = 0;
            }else{
                winner = 1;
            }
        }else if(gameState[2] == gameState[4] && gameState[4] == gameState[6] && gameState[4] !=-1){
            if(gameState[4] == 0){
                winner = 0;
            }else{
                winner = 1;
            }
        }
        if(winner == 0){
            System.out.println("P1 Wins");
            setBoard(winner);
            Toast.makeText(this, "Winner : Player 1", Toast.LENGTH_LONG).show();
            clear();
        }else if(winner == 1){
            System.out.println("P2 Wins");
            setBoard(winner);
            Toast.makeText(this, "Winner : Player 2", Toast.LENGTH_LONG).show();
            clear();
        }
    }

    void setBoard(int winner){
        TextView score;
        totalMatch++;
        score = findViewById(R.id.Match);
        score.setText(String.valueOf(totalMatch));
        if(winner == 0){
            p2Score++;
            score = findViewById(R.id.P2Score);
            score.setText(String.valueOf(p2Score));
        }else if(winner == 1){
            p1Score++;
            score = findViewById(R.id.P1Score);
            score.setText(String.valueOf(p1Score));
        }
    }
    void clear(){
        moves = 0;
        gameState = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
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
