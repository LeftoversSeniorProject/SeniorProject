package robinson.mapactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class TitleActivity extends AppCompatActivity {
    //create button
    private Button btnCreate;
    private Button btnJoin;
    private Button btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        btnCreate = (Button) findViewById(R.id.create_button);
        //btnCreate.setOnClickListener(this);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                Intent intent = new Intent(TitleActivity.this, MapsActivity.class);

                startActivity(intent);

            }
        });
        btnJoin = (Button) findViewById(R.id.join_button);
        //btnJoin.setOnClickListener(this);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                Intent intent = new Intent(TitleActivity.this, JoinActivity.class);

                startActivity(intent);


            }
        });


        Toast.makeText(this, "button set", Toast.LENGTH_LONG).show();

    }
}
