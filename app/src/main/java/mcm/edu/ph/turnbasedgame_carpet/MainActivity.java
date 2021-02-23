package mcm.edu.ph.turnbasedgame_carpet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int heroHP = 2000;
    int heroMinDPT = 80;
    int heroMaxDPT = 150;
    String heroName = "Rainbow Knight";

    int monsHP = 1500;
    int monsMinDPT = 100;
    int monsMaxDpt = 175;
    String monsName = "Home of Phobia";

    int turnNumber = 1;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        Button nextTurn = findViewById(R.id.btnNxtTrn);
        TextView txtHeroName = findViewById(R.id.txtHeroName);
        TextView txtMonsName = findViewById(R.id.txtMonsName);
        TextView txtHeroHP = findViewById(R.id.txtHeroHP);
        TextView txtMonsHP = findViewById(R.id.txtMonsHP);
        TextView txtHeroDPT = findViewById(R.id.txtHeroDPT);
        TextView txtMonsDPT = findViewById(R.id.txtMonsDPT);

        txtHeroName.setText(heroName);
        txtMonsName.setText(monsName);

        txtHeroDPT.setText(String.valueOf(heroHP));
        txtMonsHP.setText(String.valueOf(monsHP));

        txtHeroDPT.setText(heroMinDPT + " ~ " + heroMaxDPT);
        txtMonsDPT.setText(monsMinDPT + " ~ " + monsMaxDpt);

        nextTurn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Random randomizer = new Random();

        Button nextTurn = findViewById(R.id.btnNxtTrn);
        TextView txtMonsHP = findViewById(R.id.txtMonsHP);
        TextView txtHeroHP = findViewById(R.id.txtHeroHP);
        TextView txtCombatLog = findViewById(R.id.txtCombatLog);

        txtMonsHP.setText(String.valueOf(monsHP));
        txtHeroHP.setText(String.valueOf(heroHP));

        int heroDPT = randomizer.nextInt(heroMaxDPT - heroMinDPT) + heroMinDPT;
        int monsDPT = randomizer.nextInt(monsMaxDpt - monsMinDPT) + monsMinDPT;

        switch (v.getId()) {
            case R.id.btnNxtTrn:

                if (turnNumber % 2 == 1) {
                    monsHP = monsHP - heroDPT;
                    turnNumber++;
                    txtCombatLog.setText("The Player dealt " + heroDPT + " damage to the Enemy");
                    txtMonsHP.setText(String.valueOf(monsHP));
                    nextTurn.setText("Enemy's Turn (" + turnNumber + ")");

                    if (monsHP < 0) {
                        txtCombatLog.setText("The Player dealt " + heroDPT + " damage to the Enemy. \nThe Player was Victorious!");
                        heroHP = 2000;
                        monsHP = 1500;
                        turnNumber = 1;
                        nextTurn.setText("Reset Game");
                    }
                } else if (turnNumber % 2 != 1) {
                    heroHP = heroHP - monsDPT;
                    turnNumber++;
                    txtCombatLog.setText("The Enemy dealt " + monsDPT + " damage to the Player");
                    txtHeroHP.setText(String.valueOf(heroHP));
                    nextTurn.setText("Player's Turn (" + turnNumber + ")");
                    if (heroHP < 0) {
                        heroHP = 2000;
                        monsHP = 1500;
                        turnNumber = 1;
                        nextTurn.setText("Reset Game");

                    }
                }
                break;
        }
    }
}