package sample;

import javax.json.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class GeoCoder {

    public GeoCoder(String city, String state, Location location) {
        setLoc(location);
        setCityName(city);
        setStateInitials(state);
        buildJsonURL(getCityName(), getStateInitials());
        System.out.println(getJsonURL());
        parseJsonURL();
        System.out.println(getLat());
        System.out.println(getLng());

    }
    private Location loc;
    private double lat;
    private double lng;
    private String cityName;
    private String stateInitials;
    private String JsonURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    //private Gson gson = new Gson();


    public void parseJsonURL() {
        URL url = null;
        try {
            url = new URL(getJsonURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream is = null;
        try {
            is = url.openStream();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonReader rdr = Json.createReader(is);

        JsonObject obj = rdr.readObject();
        JsonArray results = obj.getJsonArray("results");
        JsonObject resultsObj = results.getJsonObject(0);
        JsonObject geometry = resultsObj.getJsonObject("geometry");
        JsonObject location = geometry.getJsonObject("location");
        JsonNumber lat = location.getJsonNumber("lat");
        JsonNumber lng = location.getJsonNumber("lng");
        double latitude = Double.parseDouble(lat.toString());
        double longitude = Double.parseDouble(lng.toString());
        setLat(latitude);
        setLng(longitude);
    }


    //GETTERS

    public Location getLoc() {
        return this.loc;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getStateInitials() {
        return this.stateInitials;
    }

    public String getJsonURL() {
        return this.JsonURL;
    }

    // SETTERS

    public void setLoc(Location location) {
        this.loc = location;
    }

    public void setLat(double latitude) {
        this.lat = latitude;
    }

    public void setLng(double longitude) {
        this.lng = longitude;
    }

    public void setCityName(String name) {
        this.cityName = name.replace(" ", "_");
    }

    public void setStateInitials(String state) {
        this.stateInitials = state;
    }

    // OTHER METHODS
    private void buildJsonURL(String cityName, String stateInitials) {
        this.JsonURL += getCityName() + "," + getStateInitials();
    }
}
