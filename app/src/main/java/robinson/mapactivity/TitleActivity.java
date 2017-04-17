package robinson.mapactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
//pushing
public class TitleActivity extends AppCompatActivity {

    private Button btnStartGame;
    //private Button btnStart;
    private Button btnHelp;
    private Button btnCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);



        btnStartGame = (Button) findViewById(R.id.start_game_button);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        btnHelp = (Button) findViewById(R.id.help_button);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleActivity.this, Help.class);
                startActivity(intent);
            }
        });


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