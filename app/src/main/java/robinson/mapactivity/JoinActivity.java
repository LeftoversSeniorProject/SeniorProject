package robinson.mapactivity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    public Button btnStart;
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        btnStart = (Button) findViewById(R.id.start_button);
        editText = (EditText) findViewById(R.id.edit_text);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        //startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        Intent intent = new Intent(JoinActivity.this, MapsActivity.class);
                        String id_string = "";
                        id_string = editText.getText().toString();
                        intent.putExtra("hider_id", id_string);
                        startActivity(intent);

            }
        });

        Toast.makeText(this, "button set", Toast.LENGTH_LONG).show();
    }


}
