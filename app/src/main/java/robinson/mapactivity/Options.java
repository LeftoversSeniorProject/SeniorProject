package robinson.mapactivity;

/**
 * Class for the Options Interface that did not make the final project
 * @author Chris Curreri
 */

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;


public class Options extends MapsActivity implements View.OnClickListener {
    private RadioGroup radioThemeGroup;
    private RadioButton radioThemeButton;

    private LinearLayout myLinearLayout;

    /**
     * Override onCreate to Draw User Interface
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        // Initialize myLinearLayout and radioThemeGroup
        // myLinearLayout was made to test changing themes
        myLinearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
        radioThemeGroup = (RadioGroup) findViewById(R.id.radioThemeGroup);

        // Planned to create a switch case for the different buttons.
        radioThemeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch(checkedId){
                   case 0:
                       myLinearLayout.setBackgroundColor(Color.WHITE);
                       break;
                   case 1:
                       myLinearLayout.setBackgroundColor(Color.BLUE);
                       break;
                   case 2:
                       myLinearLayout.setBackgroundColor(Color.YELLOW);
                       break;
                   case 3:
                       myLinearLayout.setBackgroundColor(Color.GREEN);
                       break;
                   case 4:
                       myLinearLayout.setBackgroundColor(Color.RED);
                       break;
                   default :
                       //checkedId = 0;
                       myLinearLayout.setBackgroundColor(Color.WHITE);
                       break;
               }
            }
        });

    }



}
