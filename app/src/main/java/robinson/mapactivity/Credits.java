package robinson.mapactivity;

/**
 * Created by chriscurreri on 4/9/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Credits extends AppCompatActivity implements View.OnClickListener {

    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.credits);
        btnClose = (Button) findViewById(R.id.close_button);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.close_button:
                startActivity(new Intent(getApplicationContext(), TitleActivity.class));
                break;
        }

    }
}

