package robinson.mapactivity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**˚
 * Created by gibl3t on 3/14/17.
 */

class GetTask extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private String id;
    private User user;
    private MarkerOptions marker;
    private GoogleMap map;

    private Gson GSON = new GsonBuilder().create();

    public GetTask(User user, MarkerOptions marker, GoogleMap map){
        this.user = user;
        this.marker = marker;
        this.map = map;
        id = user.getId();
    }

    protected void onPreExecute(){
    }

    protected String doInBackground(Void... urls){
        //do validation here

        try{
            URL url = new URL("http://10.35.18.240:4567/users/" + id);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        } catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response){
        if(response == null){
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        user = GSON.fromJson(response, User.class);
        LatLng hiderLocation = new LatLng(user.getLatitude(), user.getLongitude());
        MarkerOptions testMarker = new MarkerOptions();
        testMarker.position(hiderLocation);
        testMarker.title("Opponent");
        map.addMarker(testMarker);

    }
}
