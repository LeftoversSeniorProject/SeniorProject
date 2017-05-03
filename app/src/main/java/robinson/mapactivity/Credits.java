package robinson.mapactivity;
/**
 * Class for the Credits Interface.
 * I thought it would be neat to add a screen with all of our names.
 * @author Chris Curreri
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Credits extends AppCompatActivity implements View.OnClickListener {

    //button made to get back to the title screen
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

