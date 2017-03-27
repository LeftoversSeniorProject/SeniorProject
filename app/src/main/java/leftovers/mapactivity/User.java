package leftovers.mapactivity;

/**
 * Created by gibl3t on 3/20/17.
 */

public class User {
    private String id;
    private Double latitude;
    private Double longitude;

    public User(String id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Double getLatitude(){
        return latitude;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public Double getLongitude(){
        return longitude;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }


}