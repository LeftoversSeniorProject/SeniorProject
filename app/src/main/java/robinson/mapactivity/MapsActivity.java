package robinson.mapactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static robinson.mapactivity.R.id.map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,

        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnClickListener {


    //declare button variables
    private Button btnEndGame;
    private Button btnMarco;
    private Button tagButton;
    private Button btnTag;

    private Button  btnNewGame;
    private Button btnHelp;

    private Button btnStart;


    public static final String API_URL = "http://10.35.18.176:4567";


    //Google declarations
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;


    public String getData;
    private GetTask g1;
    private PostTask p1;
    private boolean seeker;
    Gson GSON = new GsonBuilder().create();
    User hider = new User(null, 0.00, 0.00);

    /**
     * onCreate method - sets up map and google API
     * sets button listener variables
     * @param savedInstanceState
     */
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toast.makeText(this,"layout set",Toast.LENGTH_SHORT).show();


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        //Test line


        //set Button Listeners
        btnMarco = (Button) findViewById(R.id.marco_button);
        btnMarco.setOnClickListener(this);
        tagButton = (Button) findViewById(R.id.tag_button);
        tagButton.setOnClickListener(this);
        btnEndGame = (Button) findViewById(R.id.end_game_button);
        btnEndGame.setOnClickListener(this);

        btnStart = (Button) findViewById(R.id.start_button);
        btnStart.setOnClickListener(this);



        Toast.makeText(this,"Set Button Listeners",Toast.LENGTH_SHORT).show();




    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady (GoogleMap googleMap){
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        Toast.makeText(this,"setMapType",Toast.LENGTH_SHORT).show();


        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                Toast.makeText(this,"buildGoogleApiClient",Toast.LENGTH_SHORT).show();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    /**
     * onClick checks which button has been clicked and
     processes button instruction
     * @param v
     */
    public void onClick(View v){

        switch (v.getId()) {


            case R.id.marco_button:
                //gets the hider's location from the server
                //marks the hider's location on the map
                getHiderLocation();
                break;

            case R.id.tag_button:
                //to do write tage button code
                break;

            case R.id.end_game_button:
                //to do write end game code
                break;

            case R.id.start_button:
                Intent intent = new Intent(MapsActivity.this, JoinActivity.class);
                //intent.putExtra("latitute", 34.8098080980);
                // intent.putExtra("longitude", 67.09098898);
                startActivity(intent);
                break;

        }

    }

    /**
     * onLocationChanged method- updates user's location
     * when their location has been changed and
     * updates pin marker
     * @param location
     */
    @Override
    public void onLocationChanged(Location location)
    {
            mLastLocation = location;
        Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //updates hider's location if it has not yet been updated and they are not seeker
        if(hider.getId() == null && !isSeeker()){
            updateHiderLocation();
        }


        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    /**
     * checkLocationPermisson - checks permission from
     * phone to get location
     */
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission is denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            //You can add here other case statements according to your requirement.
        }
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * sets marker for hider's location
     */
    public void getHiderLocation(){
        g1 = new GetTask(hider);
        g1.execute();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                hider = g1.getUser();
                System.out.println(hider.getLatitude());
                MarkerOptions hiderMarker = new MarkerOptions();
                LatLng hiderLocation = new LatLng(hider.getLatitude(), hider.getLongitude());
                hiderMarker.position(hiderLocation);
                hiderMarker.title("Opponent");
                mMap.addMarker(hiderMarker);
            }
        }, 3000);
        Toast.makeText(this,hider.getLatitude() + "",Toast.LENGTH_SHORT).show();
        
    }

/**
    //Gets the distance between two points of latitude and longitude
    //The return value is in meters.
    public double getDistance(double lat1, double long1, double lat2, double long2){
        double radius = 6371;//Earth's radius in km
        double latDiff = toRadians(lat2 - lat1);
        double longDiff = toRadians(long2 - long1);
        double a =
                Math.sin(latDiff/2) * Math.sin(latDiff/2) +
                Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) *
                Math.sin(longDiff/2) * Math.sin(longDiff/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return radius * c * 1000;
    }

    private double toRadians(double degrees){
        return degrees * (Math.PI/180);
    }
**/


    /**
     * Posts hider's current location to the server
     * @param
     */
    public void postHiderLocation(){
        p1 = new PostTask(hider);
        p1.execute();
        hider = p1.getUser();

    }

    /**
     * Update hider's current location with most recent location update
     *
     */
    public void updateHiderLocation(){
            hider.setLongitude(mLastLocation.getLongitude());
            hider.setLatitude(mLastLocation.getLatitude());
            postHiderLocation();
    }

    /**
     * checks if user is seeker
     * @return
     */
    public boolean isSeeker(){
        return seeker;
    }

}
