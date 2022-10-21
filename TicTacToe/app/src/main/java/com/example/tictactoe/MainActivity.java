package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String PLAYER_1 = "X";
    static final String PLAYER_2 = "O";
    boolean player1Turn = true;
    byte[][] board = new byte[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout table=findViewById(R.id.board);

        for(int i=0;i<3;i++)
        {
            TableRow row=(TableRow) table.getChildAt(i);
            for(int g=0;g<3;g++)
            {
                Button btn=(Button) row.getChildAt(g);
                btn.setOnClickListener(new CellListener(i,g));
            }
        }
    }

    class CellListener implements View.OnClickListener{
        int row,col;
        public CellListener(int row,int col)
        {
            this.row=row;
            this.col=col;
        }
        @Override
        public void onClick(View v) {
            if(!isvalidmove(row,col)){
                Toast.makeText(MainActivity.this, "Cell is already occupied", Toast.LENGTH_LONG).show();
                return;
            }
              if(player1Turn)
              {
                  ((Button)v).setText(PLAYER_1);
                  board[row][col]=1;
                  player1Turn=false;
              }
              else {
                  ((Button) v).setText(PLAYER_2);
                  board[row][col] = 2;
                  player1Turn = true;
              }
              if(GameEnded(row,col)==-1)
              {

              }
              else if(GameEnded(row,col)==0){
                Toast.makeText(MainActivity.this, "This is draw!", Toast.LENGTH_LONG).show();
            }
              else if(GameEnded(row,col)==1){
                  Toast.makeText(MainActivity.this, "Player1 wins!", Toast.LENGTH_LONG).show();
              }
              else
              {
                  Toast.makeText(MainActivity.this, "Player2 wins!", Toast.LENGTH_LONG).show();
              }
        }


        public boolean isvalidmove(int row,int col)
        {
            return board[row][col]==0;
        }
    }
    public int GameEnded(int row,int col)
    {
        int symbol=board[row][col];
        boolean win=true;
        for(int i=0;i<3;i++)
        {
            if(board[i][col]!=symbol)
            {
                win=false;
                break;
            }
        }
        if(win)
        {
            return symbol;
        }
        return -1;
    }
}