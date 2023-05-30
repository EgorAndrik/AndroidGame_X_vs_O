package com.example.androidgame_x_vs_o;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    TextView score_X, messeg, score_O;

    Button planFirst, planSecond, planTherd,
            planFourth, planFifth, planSixth,
            planSeventh, planEightth, planNinth;

    Button start, next;

    private int x_count = 0;
    private int o_count = 0;

    private String curr_user;
    private String start_user = "";
    private String winner;
    private boolean game_state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Texts
        score_X = (TextView) findViewById(R.id.score_X);
        messeg = (TextView) findViewById(R.id.meseg);
        score_O = (TextView) findViewById(R.id.score_O);

        // Gamer plane
        planFirst = (Button) findViewById(R.id.planFirst);
        planSecond = (Button) findViewById(R.id.planSecond);
        planTherd = (Button) findViewById(R.id.planTherd);
        planFourth = (Button) findViewById(R.id.planFourth);
        planFifth = (Button) findViewById(R.id.planFifth);
        planSixth = (Button) findViewById(R.id.planSixth);
        planSeventh = (Button) findViewById(R.id.planSeventh);
        planEightth = (Button) findViewById(R.id.planEightth);
        planNinth = (Button) findViewById(R.id.planNinth);

        // Menu buttoms
        start = (Button) findViewById(R.id.STARTbutton);
        next = (Button) findViewById(R.id.NEXTbutton);

        // Liseners
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
                if (randomNum == 1)
                    curr_user = "X";
                else
                    curr_user = "O";
                messeg.setText("Ходит - " + curr_user);
                start_user = curr_user;

                planFirst.setText("");
                planSecond.setText("");
                planTherd.setText("");
                planFourth.setText("");
                planFifth.setText("");
                planSixth.setText("");
                planSeventh.setText("");
                planEightth.setText("");
                planNinth.setText("");

                x_count = 0;
                o_count = 0;

                score_X.setText("X: " + x_count);
                score_O.setText("O: " + o_count);

                game_state = true;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!start_user.equals("")) {
                    if (start_user.equals("O"))
                        curr_user = "X";
                    else
                        curr_user = "O";
                    messeg.setText("Ходит - " + curr_user);
                    start_user = curr_user;

                    planFirst.setText("");
                    planSecond.setText("");
                    planTherd.setText("");
                    planFourth.setText("");
                    planFifth.setText("");
                    planSixth.setText("");
                    planSeventh.setText("");
                    planEightth.setText("");
                    planNinth.setText("");

                    game_state = true;
                }
            }
        });
    }
    public void onClickPlan(View v){
        Button b = (Button) findViewById(v.getId());
        if (game_state && b.getText().equals("")) {
            b.setText(curr_user);
            if (curr_user.equals("O"))
                curr_user = "X";
            else
                curr_user = "O";
            messeg.setText("Ходит - " + curr_user);
            if (check_horiz_and_vertical() || check_diogon())
                setWinner();
            else if (check_end()) {
                messeg.setText("Ничья");
            }
        }
    }
    private void setWinner() {
        messeg.setText("Победил - " + winner);
        if (winner.equals("X"))
            x_count += 1;
        else
            o_count += 1;
        score_X.setText("X: " + x_count);
        score_O.setText("O: " + o_count);
        game_state = false;
    }
    private boolean check_horiz_and_vertical(){
        // horizontal
        if (planFirst.getText().equals("X") && planSecond.getText().equals("X")
                && planTherd.getText().equals("X")){
            winner = "X";
            return true;
        }
        else if (planFourth.getText().equals("X") && planFifth.getText().equals("X")
                && planSixth.getText().equals("X")){
            winner = "X";
            return true;
        }
        else if (planSeventh.getText().equals("X") && planEightth.getText().equals("X")
                && planNinth.getText().equals("X")){
            winner = "X";
            return true;
        }
        else if (planFirst.getText().equals("O") && planSecond.getText().equals("O")
                && planTherd.getText().equals("O")){
            winner = "O";
            return true;
        }
        else if (planFourth.getText().equals("O") && planFifth.getText().equals("O")
                && planSixth.getText().equals("O")){
            winner = "O";
            return true;
        }
        else if (planSeventh.getText().equals("O") && planEightth.getText().equals("O")
                && planNinth.getText().equals("O")){
            winner = "O";
            return true;
        }
        //verticale
        else if (planFirst.getText().equals("X") && planFourth.getText().equals("X")
                && planSeventh.getText().equals("X")){
            winner = "X";
            return true;
        }
        else if (planSecond.getText().equals("X") && planFifth.getText().equals("X")
                && planEightth.getText().equals("X")){
            winner = "X";
            return true;
        }
        else if (planTherd.getText().equals("X") && planSixth.getText().equals("X")
                && planNinth.getText().equals("X")){
            winner = "X";
            return true;
        }

        else if (planFirst.getText().equals("O") && planFourth.getText().equals("O")
                && planSeventh.getText().equals("O")){
            winner = "O";
            return true;
        }
        else if (planSecond.getText().equals("O") && planFifth.getText().equals("O")
                && planEightth.getText().equals("O")){
            winner = "O";
            return true;
        }
        else if (planTherd.getText().equals("O") && planSixth.getText().equals("O")
                && planNinth.getText().equals("O")){
            winner = "O";
            return true;
        }
        return false;
    }
    private boolean check_diogon(){
        if (planFirst.getText().equals("X") && planFifth.getText().equals("X")
                && planNinth.getText().equals("X")){
            winner = "X";
            return true;
        }
        else if (planTherd.getText().equals("X") && planFifth.getText().equals("X")
                && planSeventh.getText().equals("X")){
            winner = "X";
            return true;
        }
        else if (planFirst.getText().equals("O") && planFifth.getText().equals("O")
                && planNinth.getText().equals("O")){
            winner = "O";
            return true;
        }
        else if (planTherd.getText().equals("O") && planFifth.getText().equals("O")
                && planSeventh.getText().equals("O")){
            winner = "O";
            return true;
        }
        return false;
    }
    private boolean check_end(){
        int f = 0;

        if(!(planFirst.getText() + "").equals("")) f++;
        if(!(planSecond.getText() + "").equals("")) f++;
        if(!(planTherd.getText() + "").equals("")) f++;
        if(!(planFourth.getText() + "").equals("")) f++;
        if(!(planFifth.getText() + "").equals("")) f++;
        if(!(planSixth.getText() + "").equals("")) f++;
        if(!(planSeventh.getText() + "").equals("")) f++;
        if(!(planEightth.getText() + "").equals("")) f++;
        if(!(planNinth.getText() + "").equals("")) f++;

        if (f == 9)
            return true;
        return false;
    }
}