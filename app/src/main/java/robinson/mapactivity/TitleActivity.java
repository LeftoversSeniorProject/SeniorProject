package robinson.mapactivity;
/**
 * Class for Title Interface
 * @author Chris Curreri
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class TitleActivity extends AppCompatActivity {

    // Activity Buttons
    private Button btnStartGame;
    private Button btnHelp;
    private Button btnCredits;

    /**
     * Override onCreate to Draw User Interface
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        //Start Button
        btnStartGame = (Button) findViewById(R.id.start_game_button);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        //Help Button
        btnHelp = (Button) findViewById(R.id.help_button);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleActivity.this, Help.class);
                startActivity(intent);
            }
        });

        //Credits Button
        btnCredits = (Button) findViewById(R.id.credits_button);
        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleActivity.this, Credits.class);
                startActivity(intent);
            }
        });
    }
}