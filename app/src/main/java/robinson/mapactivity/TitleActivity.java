package robinson.mapactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
//pushing
public class TitleActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btnHelp;
    private Button btnCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        btnStart = (Button) findViewById(R.id.start_game_button);
        btnStart.setOnClickListener(new View.OnClickListener() {
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

        Toast.makeText(this, "button set", Toast.LENGTH_LONG).show();
    }
}
