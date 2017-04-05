package robinson.mapactivity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    private Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        btnJoin = (Button) findViewById(R.id.join_button);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.join_button:
                        //startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        Intent intent = new Intent(JoinActivity.this, MapsActivity.class);
                        intent.putExtra("latitute", 34.8098080980);
                        intent.putExtra("longitude", 67.09098898);
                        startActivity(intent);

                        break;
                }
            }
        });

        Toast.makeText(this, "button set", Toast.LENGTH_LONG).show();
    }


}
