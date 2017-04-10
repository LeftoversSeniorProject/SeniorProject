package robinson.mapactivity;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by gibl3t on 3/27/17.
 */

class PostTask extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private String id;
    private User user;

    private Gson GSON = new GsonBuilder().create();

    public PostTask(User user){
        this.user = user;
        id = user.getId();
    }

    protected void onPreExecute(){

    }

    protected String doInBackground(Void... urls){

        try{
            URL url = new URL(MapsActivity.API_URL + "/users");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try{
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
                outputStreamWriter.write(GSON.toJson(user));
                outputStreamWriter.flush();

                int responseCode = urlConnection.getResponseCode();
                System.out.println("Sending POST");
                System.out.println("Response code: " + responseCode);
                System.out.println("GSON USER: " + GSON.toJson(user));

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
        System.out.println("Post Execute User ID = " + user.getId());
    }

    public User getUser(){
        return user;
    }
}
