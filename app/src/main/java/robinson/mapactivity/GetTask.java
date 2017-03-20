package robinson.mapactivity;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gibl3t on 3/14/17.
 */

class GetTask extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private TextView responseView;
    private String id;

    public GetTask(TextView responseView, String id){
        this.responseView = responseView;
        this.id = id;
    }

    protected void onPreExecute(){
        responseView.setText("");
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
        responseView.setText(response);
    }
}
