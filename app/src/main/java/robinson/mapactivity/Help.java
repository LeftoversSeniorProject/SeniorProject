package robinson.mapactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Michael on 3/8/2017.
 */
public class Help extends MapsActivity implements View.OnClickListener {

    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.helpwindow);

        btnClose = (Button) findViewById(R.id.close_button);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //get button clicked
        switch(v.getId()) {

            case R.id.close_button:
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            break;
        }
    }
}
