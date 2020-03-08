package com.example.android.crossgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 is Right
    // 1 is wrong

    int step=0;
   int gameOverCount=0;
   TextView mtxt;
   int playAgain=1;
   int  activePlayer=0;
   int[] gamingState={2,2,2,2,2,2,2,2,2};
   int[][] winningPos= { {0,1,2},{3,4,5,},{6,7,8},
           {0,3,6},{1,4,6},{2,5,8},
           {0,4,8},{2,4,6}
   };


  LinearLayout mLinear;
    android.support.v7.widget.GridLayout  gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mtxt=findViewById(R.id.txt);
         mLinear=findViewById(R.id.Linear);


    }

public void playAgain(View view)
{
       mLinear.setVisibility(View.INVISIBLE);
       step=0;
       playAgain=1;
       gameOverCount=0;
     gl =findViewById(R.id.grid);

     for(int i=0;i<9;i++)
    {
          gamingState[i]=2;
        ImageView img=(ImageView)  gl.getChildAt(i);
        img.setImageResource(0);

    }


}




    public void click(View view) {


        ImageView img = (ImageView) view;




        Log.e("Tag is on Right", "" + img.getTag().toString());

        int tag = Integer.parseInt(img.getTag().toString());


        Log.e("gameState", "" + gamingState[tag]);

        if (gamingState[tag] == 2 && playAgain==1)
        {

            gamingState[tag] = activePlayer;
            step=step+1;


             if (activePlayer == 0) {


                img.setImageResource(R.drawable.right);

                activePlayer = 1;


            } else {
                img.setImageResource(R.drawable.unnamed);

                activePlayer = 0;


            }


            for (int[] b : winningPos) {


                if (gamingState[b[0]] == gamingState[b[1]] && gamingState[b[1]] == gamingState[b[2]] && gamingState[b[0]] != 2) {

                    Log.e("is in win=",""+gamingState[b[0]]+gamingState[b[1]]+gamingState[b[2]]);
                     gameOverCount=1;
                      mLinear.setVisibility(View.VISIBLE);

                    if (activePlayer == 0) {
                    mtxt.setText("Wrong Win The Game");
                    playAgain=0;

                    } else
                        {
                          mtxt.setText("Right Win The Game");
                          playAgain=0;

                        }




                }

            }
           if(step==9 && gameOverCount==0)
           {
               mLinear.setVisibility(View.VISIBLE);

               mtxt.setText("Game Over,Better Luck Next Time");


           }

        }


    }

}
