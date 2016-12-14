package sample;

import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherController {

   @FXML public void initialize() {

       stateOptions = FXCollections.observableArrayList(loc.getStates());
       statesBox.setItems(stateOptions);
    }

    //Instanced Class Objects
    private Location loc = new Location();
    private WeatherResponse localWeather;
    private ObservableList<String> stateOptions;
    private GeoCoder geoCoder;
    private Gson gson = new Gson();

    //Instanced Objects relating to the WeatherController JavaFX GUI
    @FXML TextField cityField = new TextField();
    @FXML private ComboBox statesBox = new ComboBox<String>();
    @FXML private ImageView weatherIcon;
    @FXML private TextField temperatureField;
    @FXML private Button weatherSearchButton = new Button();

    //Image objects used by the WeatherController JavaFX GUI
    private Image sunny = new Image("images/sunny.png");
    private Image rainy = new Image("images/rain.png");
    private Image snowy = new Image("images/snow.png");
    private Image cloudy = new Image("images/cloudy.png");
    private Image partlyCloudy = new Image("images/partly_cloudy.png");

    /* Images provided for unused weather conditions */
    private Image chanceOfRain = new Image("images/chance_of_rain.png");
    private Image chanceOfSnow = new Image("images/chance_of_snow.png");
    private Image stormy = new Image("images/storm.png");
    private Image thunderStorm = new Image("images/thunderstorm.png");

    //Sets the contents of the weatherIcon ImageView Object
    public void setWeatherIcon() {
        String currentWeather = localWeather.getCurrentIcon().toString();
        if (currentWeather.equals("clear-day") || currentWeather.equals("clear-night") || currentWeather.equals("wind") || currentWeather.equals("fog")) {
            getWeatherIcon().setImage(this.sunny);
        }

        else if (currentWeather.equals("rain")) {
            getWeatherIcon().setImage(this.rainy);
        }

        else if (currentWeather.equals("snow") || currentWeather.equals("sleet")) {
            getWeatherIcon().setImage(this.snowy);
        }

        else if (currentWeather.equals("cloudy")) {
            getWeatherIcon().setImage(this.cloudy);
        }

        else if (currentWeather.equals("partly-cloudy-day") || currentWeather.equals("partly-cloudy-night")) {
            getWeatherIcon().setImage(this.partlyCloudy);
        }

        else {
            getWeatherIcon().setImage(null);
        }
    }

    /** GETTERS */
    public Location getLoc() {
        return this.loc;
    }
    //Retrieve the contents of the weatherIcon ImageView Object
    public ImageView getWeatherIcon() {
        return this.weatherIcon;
    }
    //Retrieve the contents of the temperatureField TextField Object
    public TextField getTemperatureField() {
        return this.temperatureField;
    }

    /** SETTERS */

    public void setTemperature() {
        getTemperatureField().setText(localWeather.getCurrentTemp() + "° F");
    }

    public void setLocationCity(String city) {
        getLoc().setCity(city);
    }

    public void setLocationState(String state) {
        getLoc().setState(state);
    }

    public void setLocationLat(double lat) {
        this.loc.setLat(lat);
    }

    public void setLocationLng(double lng) {
        this.loc.setLng(lng);
    }

    /** ACTION METHODS */

    public void weatherSearchButtonClick() {
        setLocationCity(cityField.getText());
        setLocationState(statesBox.getValue().toString());
        geoCoder = new GeoCoder(getLoc().getCity(), getLoc().getState(), getLoc());
        getLoc().setLat(geoCoder.getLat());
        getLoc().setLng(geoCoder.getLng());
        localWeather = new WeatherResponse(getLoc());
        localWeather = gson.fromJson(localWeather.getDarkSkyResponse(), WeatherResponse.class);
        setWeatherIcon();
        setTemperature();
        System.out.println(localWeather.getCurrentTemp());
    }

    public void openSettingsWindow() {
        //opens a new window where the user can alter settings
        //this window should take priority and should be handled
        //before the user can access any other windows.
    }

}
