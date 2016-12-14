package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;


public class WeatherResponse {

    /* Instance Variables */
    private String darkSkyURL = "https://api.darksky.net/forecast/213578fa0288e37fe9050340441d3979/";
    private Location local;

    /* Maps for each type of weather response */
    private HashMap<String, Object> currently = new HashMap();
    private HashMap<String, Object> minutely = new HashMap();

    /* The following maps exist, but aren't implemented

    private HashMap<String, Object> hourly = new HashMap();
    private HashMap<String, Object> daily = new HashMap();
    private HashMap<String, Object> alerts = new HashMap();

    */

    /* CONSTRUCTOR */
    public WeatherResponse(Location loc) {
        this.local = loc;
        setDarkSkyURL();
        populateFields();
    }


    /* SETTERS */

    public void setDarkSkyURL() {
        darkSkyURL = getDarkSkyURL() + local.getLat() + "," + local.getLng();
    }

    /* GETTERS */
    public double getLatitude() { return this.getLocal().getLat(); }
    public double getLongitude() { return this.getLocal().getLng(); }
    public String getDarkSkyURL() { return this.darkSkyURL; }

    /* GETTERS FOR HASHMAP "CURRENTLY" */
    public Object getCurrentTime() { return this.currently.get("time"); }
    public Object getCurrentSummary() { return this.currently.get("summary"); }
    public Object getCurrentIcon() { return this.currently.get("icon"); }
    public Object getCurrentNearestStormDistance() { return this.currently.get("nearestStormDistance"); }
    public Object getCurrentPrecipIntensity() { return this.currently.get("precipIntensity"); }
    public Object getCurrentlyPrecipProbability() { return this.currently.get("precipProbability"); }
    public Object getCurrentTemp() { return this.currently.get("temperature"); }
    public Object getCurrentApparentTemp() { return this.currently.get("apparentTemperature"); }
    public Object getCurrentDewPoint() { return this.currently.get("dewPoint"); }
    public Object getCurrentHumidity() { return this.currently.get("humidity"); }
    public Object getCurrentWindSpeed() { return this.currently.get("windSpeed"); }
    public Object getCurrentWindBearing() { return this.currently.get("windBearing"); }
    public Object getCurrentVisibility() { return this.currently.get("visibility"); }
    public Object getCurrentCloudCover() { return this.currently.get("cloudCover"); }
    public Object getCurrentPressure() { return this.currently.get("pressure"); }
    public Object getCurrentOzone() { return this.currently.get("ozone"); }

    /* GETTERS FOR HASHMAP "MINUTELY" */
    public Object getMinutelySummary() { return this.minutely.get("summary"); }
    public Object getMinutelyIcon() { return this.minutely.get("icon"); }
    public Object getMinutelyTime() { return this.minutely.get("time"); }
    public Object getMinutelyPrecipIntensity() { return this.minutely.get("precipIntensity"); }
    public Object getMinutelyPrecipProbability() { return this.minutely.get("precipProbability"); }
    public Object getMinutelyPrecipType() { return this.minutely.get("precipType"); }

    /* STANDARD GETTERS */
    public Location getLocal() {
        return this.local;
    }

    /* DARK SKY FORECAST RETRIEVAL */
    public String getDarkSkyResponse() {
        String fullResponse = "";
        try {
            URL darkSkyTarget = new URL(getDarkSkyURL());
            URLConnection dsConnection = darkSkyTarget.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(dsConnection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                fullResponse += inputLine;
            in.close();
        } catch (MalformedURLException e) {
            System.out.println("new URL failed");
        } catch (IOException e) {
            System.out.println("openConnection failed");
        }

        return fullResponse;
    }

    private void populateFields() {
        populateCurrently();
        populateMinutely();

    }

    public void populateCurrently() {
        currently.put("time", 0);
        currently.put("summary", "");
        currently.put("icon", "");
        currently.put("nearestStormDistance", 0);
        currently.put("precipIntensity", 0.0);
        currently.put("precipProbability", 0);
        currently.put("precipType", "");
        currently.put("temperature", 0.0);
        currently.put("apparentTemperature", 0.0);
        currently.put("dewPoint", 0.0);
        currently.put("humidity", 0.0);
        currently.put("windSpeed", 0.0);
        currently.put("windBearing", 0);
        currently.put("visibility", 0.0);
        currently.put("cloudCover", 0.0);
        currently.put("pressure", 0.0);
        currently.put("ozone", 0.0);
    }

    public void populateMinutely() {
        minutely.put("summary", "");
        minutely.put("icon", "");
        minutely.put("time", 0);
        minutely.put("precipIntensity", 0.0);
        minutely.put("precipProbability", 0);
        minutely.put("precipType", "");
    }


}
